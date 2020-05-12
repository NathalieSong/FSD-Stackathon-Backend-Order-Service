package com.emart.order.service.orderservice.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.emart.order.service.orderservice.entity.Order;
import com.emart.order.service.orderservice.entity.OrderItem;
import com.emart.order.service.orderservice.entity.PurchaseHistory;
import com.emart.order.service.orderservice.entity.Transaction;
import com.emart.order.service.orderservice.FeignClient.ItemServiceClient;
import com.emart.order.service.orderservice.repository.OrderItemRepository;
import com.emart.order.service.orderservice.repository.OrderRepository;
import com.emart.order.service.orderservice.repository.PurchaseHistoryRepository;
import com.emart.order.service.orderservice.repository.TransactionRepository;
import com.emart.order.service.orderservice.dto.CheckoutDto;
import com.emart.order.service.orderservice.dto.DiscountDto;
import com.emart.order.service.orderservice.dto.TaxDto;
import com.emart.order.service.orderservice.vo.CartItem;
import com.emart.order.service.orderservice.vo.CheckoutReqBody;
import com.emart.order.service.orderservice.vo.Item;
import com.emart.order.service.orderservice.vo.TaxReqBody;
import com.emart.order.service.orderservice.vo.TransactionType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinancialService {
    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private OrderItemRepository orderItemRepo;

    @Autowired
    private TransactionRepository transactionRepo;

    @Autowired
    private PurchaseHistoryRepository phRepo;

    @Autowired
	private ItemServiceClient itemClient;

    public TaxDto getTax(TaxReqBody taxReq) {
        return new TaxDto(calTaxByItems(taxReq.getItems()));
    }

    public CheckoutDto checkout(CheckoutReqBody checkoutReq) {
        BigDecimal tax = calTaxByItems(checkoutReq.getItems());
        Order order = newOrder(checkoutReq.getBuyerId(), checkoutReq.getDiscount(), tax);
        BigDecimal total = newOrderItems(checkoutReq.getItems(), order);
        newTransaction(checkoutReq.getBuyerId(), checkoutReq.getType(), total, order);
        newPurchaseHistory(order.getId());
        return new CheckoutDto(order.getId());
    }

    private BigDecimal calTaxByItems(List<CartItem> items) {
        BigDecimal tax = new BigDecimal(0);
        for (int i = 0; i < items.size(); i++) {
            tax.add(
                calTax(items.get(i).getItemPrice(),
                new BigDecimal(items.get(i).getQuantity().intValue()),
                items.get(i).getGst())
            );
        }
        return tax;
    }

    private BigDecimal calTax(BigDecimal price, BigDecimal quantity, BigDecimal gst) {
        return price.multiply(quantity).multiply(gst).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private Order newOrder(String buyerId, DiscountDto discount, BigDecimal tax) {
        Order order = new Order();
        order.setBuyerId(buyerId);
        order.setTax(tax);
        order.setDiscountCode(discount.getCode());
        order.setDiscountPercentage(discount.getPercentage());
        order.setCreatedDate(new Date());
        return orderRepo.save(order);
    }

    private BigDecimal newOrderItems(List<CartItem> items, Order order) {
        BigDecimal total = new BigDecimal(0);
        for (int i = 0; i < items.size(); i++) {
            OrderItem oItem = new OrderItem();
            CartItem cItem = items.get(i);
            oItem.setItemId(cItem.getId());
            oItem.setOrder(order);
            oItem.setQuantity(cItem.getQuantity());
            oItem.setTotal(cItem.getItemPrice().multiply(
                new BigDecimal(cItem.getQuantity().intValue())
            ));
            OrderItem newItem = orderItemRepo.save(oItem);
            total.add(newItem.getTotal());
        }
        return total;
    }

    private void newTransaction(String buyerId, String type, BigDecimal total, Order order) {
        Transaction trans = new Transaction();
        trans.setBuyerId(buyerId);
        trans.setTotal(total);
        trans.setOrder(order);
        trans.setCreatedDate(new Date());
        trans.setType(TransactionType.of(type));
        transactionRepo.save(trans);
    }

    private void newPurchaseHistory(String orderId) {
        Order order = orderRepo.findById(orderId).orElse(null);
        List<OrderItem> orderItems = orderItemRepo.findByOrder(order);
        Transaction trans = transactionRepo.findByOrder(order).get(0);
        for (int i = 0; i < orderItems.size(); i++) {
            PurchaseHistory pHist = new PurchaseHistory();
            Item item = itemClient.getById(orderItems.get(i).getItemId());
            pHist.setBuyerId(order.getBuyerId());
            pHist.setSellerId(item.getSellerId());
            pHist.setTransactionId(trans.getId());
            pHist.setItemId(item.getId());
            pHist.setCategoryId(item.getCategoryId());
            pHist.setQuantity(orderItems.get(i).getQuantity());
            pHist.setDiscountCode(order.getDiscountCode());
            pHist.setDiscountPercentage(order.getDiscountPercentage());
            pHist.setTotalBeforeDiscount(orderItems.get(i).getTotal());
            pHist.setCreatedDate(new Date());
        }
    }
}
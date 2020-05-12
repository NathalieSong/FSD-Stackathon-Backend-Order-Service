package com.emart.order.service.orderservice.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.emart.order.service.orderservice.entity.PurchaseHistory;
import com.emart.order.service.orderservice.FeignClient.ItemServiceClient;
import com.emart.order.service.orderservice.repository.PurchaseHistoryRepository;
import com.emart.order.service.orderservice.dto.PurchaseHistoryDto;
import com.emart.order.service.orderservice.dto.SellingReportDto;
import com.emart.order.service.orderservice.dto.StockItemDto;
import com.emart.order.service.orderservice.vo.Item;
import com.emart.order.service.orderservice.vo.SellingReportFilter;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
	@PersistenceContext
	private EntityManager entityManager;

    @Autowired
	private PurchaseHistoryRepository phRepo;
	
	@Autowired
	private ItemServiceClient itemClient;

	public List<PurchaseHistoryDto> getByBuyer(String buyerId) {
		List<PurchaseHistoryDto> result = new ArrayList<>();
		List<PurchaseHistory> phList = phRepo.findByBuyerId(buyerId);
		for (int i = 0; i < phList.size(); i++) {
			PurchaseHistory ph = phList.get(i);
			Item item = itemClient.getById(ph.getItemId());
			result.add(toPurchaseHistoryDto(ph, item));
		}
		return result;
	}


	public List<SellingReportDto> getSellingReportByFilter(SellingReportFilter filter) {
		return getSellingReportByQueryResult(
			queryItemsSoldByPeriod(filter.getSellerId(), filter.getStartPeriod(), filter.getEndPeriod())
		);
	}

	public List<StockItemDto> getAllSellingStock(String sellerId) {
		return null;
	}

	private PurchaseHistoryDto toPurchaseHistoryDto(PurchaseHistory ph, Item item) {
		PurchaseHistoryDto phDto = new PurchaseHistoryDto();
		phDto.setId(ph.getId());
		phDto.setItemId(item.getId());
		phDto.setItemName(item.getName());
		phDto.setItemDesc(item.getDescription());
		phDto.setDiscountCode(ph.getDiscountCode());
		phDto.setDiscountPercentage(ph.getDiscountPercentage());
		phDto.setCreatedDate(ph.getCreatedDate());
		phDto.setQuantity(ph.getQuantity());
		phDto.setTotalAfterDiscount(
			ph.getTotalBeforeDiscount().multiply(
				new BigDecimal(1).subtract(ph.getDiscountPercentage())
			));
		phDto.setPicture(item.getPictures().get(0).toString());
		return phDto;
	}

	private List<Object[]> queryItemsSoldByPeriod(String sellerId, Date startPeriod, Date endPeriod) {
		String sql = "select item_id, sum(total_before_discount), count(1)"
		  + " from purchase_history where seller_id = :sellerId and created_date between :startPeriod and :endPeriod"
		  + " group by item_id";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("sellerId", sellerId);
		query.setParameter("startPeriod", startPeriod);
		query.setParameter("endPeriod", endPeriod);
		return query.getResultList();
	}

	private List<SellingReportDto> getSellingReportByQueryResult(List<Object[]> content) {
		List<SellingReportDto> result = new ArrayList<>();
		if (!content.isEmpty()) {
			for (int i = 0; i < content.size(); i++) {
				Object[] obj = content.get(i);
				SellingReportDto dto = new SellingReportDto();
				dto.setItemId((String) obj[0]);
				dto.setTotal((BigDecimal) obj[1]);
				dto.setNoSold((BigDecimal) obj[2]);
				Item item = itemClient.getById(dto.getItemId());
				dto.setItemName(item.getName());
				result.add(dto);
			}
		}
		return result;
	}

	private List<Object[]> queryItemsSold(String sellerId) {
		String sql = "select item_id, sum(total_before_discount), count(1)"
		  + " from purchase_history where seller_id = :sellerId"
		  + " group by item_id";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("sellerId", sellerId);
		return query.getResultList();
	}

	private List<StockItemDto> getSellingStockByQueryResult(List<Object[]> content) {
		List<StockItemDto> result = new ArrayList<>();
		if (!content.isEmpty()) {
			for (int i = 0; i < content.size(); i++) {
				Object[] obj = content.get(i);
				StockItemDto dto = new StockItemDto();
				dto.setId((String) obj[0]);
				Item item = itemClient.getById(dto.getId());
				dto.setName(item.getName());
				dto.setDescription(item.getDescription());
				dto.setNoInStock(item.getStockNumber());
				dto.setNoSold((BigDecimal) obj[2]);
				dto.setPrice(item.getPrice());
				dto.setPicture(item.getPictures().get(0).toString());
				result.add(dto);
			}
		}
		return result;
	}
}
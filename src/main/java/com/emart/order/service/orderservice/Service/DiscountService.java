package com.emart.order.service.orderservice.service;

import java.util.Date;

import javax.transaction.Transactional;

import com.emart.order.service.orderservice.entity.Discount;
import com.emart.order.service.orderservice.repository.DiscountRepository;
import com.emart.order.service.orderservice.dto.DiscountDto;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {
    @Autowired
    private DiscountRepository discountRepo;

	public DiscountDto getActiveByCode(String code) {
		return toDto(
            discountRepo.findActiveByCode(code, new Date())
        );
    }

	public DiscountDto addDiscount(DiscountDto dto) {
        Discount discount = fromDto(dto);
        discount.setId(null);
		return toDto(
            discountRepo.save(discount)
        );
    }
    
    @Transactional
    public void rmDiscountByCode(String code) {
        discountRepo.deleteByCode(code);
    }
    
    private DiscountDto toDto(Discount discount) {
        if (discount == null) {
            return null;
        }
        DiscountDto dDto = new DiscountDto();
        BeanUtils.copyProperties(discount, dDto);
        return dDto;
    }

    private Discount fromDto(DiscountDto dto) {
        if (dto == null) {
            return null;
        }
        Discount discount = new Discount();
        BeanUtils.copyProperties(dto, discount);
        return discount;
    }
}
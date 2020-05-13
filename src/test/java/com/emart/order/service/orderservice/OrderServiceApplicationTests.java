package com.emart.order.service.orderservice;

import com.emart.order.service.orderservice.service.DiscountService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import com.emart.order.service.orderservice.dto.DiscountDto;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class OrderServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
    private DiscountService discountService;

	private static DiscountDto dDto;

	@BeforeAll
	void initDiscount() {
		dDto = new DiscountDto();
		dDto.setCode("test-discount");
		dDto.setDescription("This is for discount test");
		dDto.setPercentage(new BigDecimal(0.3));
		Calendar calendar = Calendar.getInstance();
		calendar.set(2020, 4, 12);
		dDto.setStartDate(calendar.getTime());
		calendar.set(2020, 4, 31);
		dDto.setEndDate(calendar.getTime());
	}

	@Test
	void discountTest() {
		DiscountDto dtoAdded = discountService.addDiscount(dDto);
		DiscountDto dtoGot = discountService.getActiveByCode(dDto.getCode());
		assertEquals(dtoAdded.getId(), dtoGot.getId());
		assertEquals(dtoAdded.getCode(), dtoGot.getCode());
		assertEquals(dtoAdded.getPercentage().setScale(2, BigDecimal.ROUND_HALF_UP), dtoGot.getPercentage());
		assertEquals(dtoAdded.getDescription(), dtoGot.getDescription());
		assertEquals(dtoAdded.getStartDate(), dtoGot.getStartDate());
		assertEquals(dtoAdded.getEndDate(), dtoGot.getEndDate());
	}

	@AfterAll
	void rmDiscountAdded() {
		discountService.rmDiscountByCode(dDto.getCode());
	}

}

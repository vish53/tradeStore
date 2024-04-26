package com.assignment.store.service;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.assignment.store.controller.TradeStoreController;
import com.assignment.store.model.TradeStore;


@SpringBootTest
public class TradeStoreServiceImplTest {
	
	@Test
	void contextLoads() {
	}
	
	@Autowired
	private TradeStoreServiceImpl tradeStoreServiceImpl;
	
	@Test
	void checkTradeVersionTest() {
		boolean value = tradeStoreServiceImpl.checkTradeVersion(getTrade("T1",1,LocalDate.now()), getTrade("T1", 2, LocalDate.now()));
		Assertions.assertEquals(false, value);
	}
	
	@Test
	void checkTradeMaturityDateTest() {
		boolean value = tradeStoreServiceImpl.checkTradeMaturity(getTrade("T1", 1, LocalDate.now()));
		Assertions.assertEquals(true, value);
	}
	
	public TradeStore getTrade(String tradeId, int version,LocalDate  maturityDate){
		TradeStore trade = new TradeStore();
		trade.setTradeId(tradeId);
		trade.setBookId("B1");
		trade.setVersion(version);
		trade.setCounterPartyId("CP-1");
		trade.setMaturityDate(maturityDate);
		trade.setExpiredFlag("Y");
		return trade;
	}
}

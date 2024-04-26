package com.assignment.store;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.assignment.store.controller.TradeStoreController;
import com.assignment.store.exception.MaturityDateException;
import com.assignment.store.model.TradeStore;
import com.assignment.store.repository.TradeStoreRepository;

@SpringBootTest
class TradestoreApplicationTests {
		@Test
	void contextLoads() {
	}
	@Autowired
	private TradeStoreController tradeStoreController;
	
	@MockBean
	private TradeStoreRepository tradeStoreRepository;
	
	@Test
	void insertTradeTestWhenSavedSuccessfully() {
		//Mockito.when(tradeStoreRepository.findById(Mockito.anyString())).thenReturn(Optional.of(getTrade("T1", 1, LocalDate.now())));
		ResponseEntity<TradeStore> responseEntity = tradeStoreController.insertTrade(getTrade("T1",1,LocalDate.now()));
		Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	}
	
	@Test
	void insertTradeTestWhenMaturityDateIsLessThanCurrentDate() {
		LocalDate localDate = LocalDate.now();
		localDate = localDate.minusDays(2);
		try
		{
		ResponseEntity<TradeStore> responseEntity = tradeStoreController.insertTrade(getTrade("T2",1,localDate));
		}catch(MaturityDateException e)
		{
		Assertions.assertEquals("Maturity date is less than today's date", e.getMessage());
		}
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

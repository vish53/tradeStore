package com.assignment.store.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.assignment.store.service.TradeStoreService;

@Component
public class TradeScheduler {

	@Autowired
	TradeStoreService tradeStoreService;

	// cron job to check every minute in the database
	@Scheduled(cron = "0 * * * * ?")
	public void getCurrentTime() {
		tradeStoreService.checkAndUpdateExpiryFlag();
	}
}

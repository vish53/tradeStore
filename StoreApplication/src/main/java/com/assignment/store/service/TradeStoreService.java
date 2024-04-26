package com.assignment.store.service;

import com.assignment.store.model.TradeStore;

public interface TradeStoreService {

	TradeStore saveTrade(TradeStore tradeStore);

	void checkAndUpdateExpiryFlag();

}

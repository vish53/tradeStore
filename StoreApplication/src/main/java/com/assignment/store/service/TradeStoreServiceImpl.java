package com.assignment.store.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.store.exception.InvalidTradeVersionException;
import com.assignment.store.exception.MaturityDateException;
import com.assignment.store.model.TradeStore;
import com.assignment.store.repository.TradeStoreRepository;

@Service
public class TradeStoreServiceImpl implements TradeStoreService {

	@Autowired
	TradeStoreRepository tradeStoreRepository;

	@Override
	public TradeStore saveTrade(TradeStore tradeStore) {
		TradeStore trade = null;
		if (checkTradeMaturity(tradeStore)) {
			Optional<TradeStore> existingTrade = tradeStoreRepository.findById(tradeStore.getTradeId());
			if (existingTrade.isPresent()) {
				if (checkTradeVersion(tradeStore, existingTrade.get())) {
					tradeStore.setCreatedDate(LocalDate.now());
					trade = tradeStoreRepository.save(tradeStore);
				} else
					throw new InvalidTradeVersionException("Lower version of Trade is received");
			} else {
				tradeStore.setCreatedDate(LocalDate.now());
				trade = tradeStoreRepository.save(tradeStore);
			}
		} else {
			throw new MaturityDateException("Maturity date is less than today's date");
		}
		return trade;
	}

	public boolean checkTradeVersion(TradeStore tradeStore, TradeStore tradeStorePrevious) {
		if (tradeStore.getVersion() >= tradeStorePrevious.getVersion()) {
			return true;
		}
		return false;
	}

	public boolean checkTradeMaturity(TradeStore tradeStore) {
		if (tradeStore.getMaturityDate().isBefore(LocalDate.now())) {
			return false;
		}
		return true;
	}

	@Override
	public void checkAndUpdateExpiryFlag() {
		List<TradeStore> list = tradeStoreRepository.findAll();
		for (TradeStore trade : list) {
			if (!checkTradeMaturity(trade)) {
				trade.setExpiredFlag("Y");
				tradeStoreRepository.save(trade);
			}
		}

	}

}

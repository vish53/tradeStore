package com.assignment.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.store.model.TradeStore;
import com.assignment.store.service.TradeStoreService;

@RestController
public class TradeStoreController {

	@Autowired
	TradeStoreService tradeStoreService;

	@PostMapping("/saveTrade")
	public ResponseEntity<TradeStore> insertTrade(@RequestBody TradeStore tradeStore) {
		TradeStore savedTrade = tradeStoreService.saveTrade(tradeStore);
		return new ResponseEntity<TradeStore>(savedTrade, HttpStatus.CREATED);
	}
}

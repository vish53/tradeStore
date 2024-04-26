package com.assignment.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.store.model.TradeStore;

@Repository
public interface TradeStoreRepository extends JpaRepository<TradeStore,String> {

}

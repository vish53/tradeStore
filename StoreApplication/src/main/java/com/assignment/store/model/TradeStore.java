package com.assignment.store.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TradeStore")
public class TradeStore {

	@Id
	private String tradeId;
	private int version;
	private String counterPartyId;
	private String bookId;
	private LocalDate maturityDate;
	private LocalDate createdDate;
	private String expired;

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getCounterPartyId() {
		return counterPartyId;
	}

	public void setCounterPartyId(String counterPartyId) {
		this.counterPartyId = counterPartyId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public LocalDate getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(LocalDate maturityDate) {
		this.maturityDate = maturityDate;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public String getExpired() {
		return expired;
	}

	public void setExpiredFlag(String expired) {
		this.expired = expired;
	}

	@Override
	public String toString() {
		return "Trade{" + "tradeId='" + tradeId + '\'' + ", version=" + version + ", counterPartyId='" + counterPartyId
				+ '\'' + ", bookId='" + bookId + '\'' + ", maturityDate=" + maturityDate + ", createdDate="
				+ createdDate + ", expired='" + expired + '\'' + '}';
	}

}

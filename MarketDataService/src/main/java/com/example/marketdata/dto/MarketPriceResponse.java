package com.example.marketdata.dto;

import java.math.BigDecimal;

public class MarketPriceResponse {
	String symbol;
	BigDecimal bid;
	BigDecimal ask;
	BigDecimal last;

	public MarketPriceResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	private MarketPriceResponse(MarketPriceResponse obj) {
		this.symbol = obj.symbol;
		this.bid = obj.bid;
		this.ask = obj.ask;
		this.last = obj.last;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public BigDecimal getBid() {
		return bid;
	}

	public void setBid(BigDecimal bid) {
		this.bid = bid;
	}

	public BigDecimal getAsk() {
		return ask;
	}

	public void setAsk(BigDecimal ask) {
		this.ask = ask;
	}

	public BigDecimal getLast() {
		return last;
	}

	public void setLast(BigDecimal last) {
		this.last = last;
	}

}

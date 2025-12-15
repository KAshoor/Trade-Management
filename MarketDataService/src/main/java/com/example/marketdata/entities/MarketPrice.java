package com.example.marketdata.entities;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "market_prices")
@Data
public class MarketPrice {
	@Id
	private String symbol;

	private BigDecimal bid;
	private BigDecimal ask;
	private BigDecimal last;

	public MarketPrice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MarketPrice(String symbol, BigDecimal bid, BigDecimal ask, BigDecimal last) {
		super();
		this.symbol = symbol;
		this.bid = bid;
		this.ask = ask;
		this.last = last;
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

	@Override
	public String toString() {
		return "MarketPrice [symbol=" + symbol + ", bid=" + bid + ", ask=" + ask + ", last=" + last + "]";
	}

}

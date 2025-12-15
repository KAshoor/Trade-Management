package com.example.marketdata.services;

import com.example.marketdata.exception.MarketClosedException;

public interface MarketStatusService {
	public void validateMarketOpen() throws MarketClosedException;
}

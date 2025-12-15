package com.example.marketdata.services;

import com.example.marketdata.dto.MarketPriceResponse;
import com.example.marketdata.exception.MarketClosedException;
import com.example.marketdata.exception.SymbolNotFoundException;

public interface MarketDataService {
	public MarketPriceResponse getPrice(String symbol) throws MarketClosedException, SymbolNotFoundException;
}

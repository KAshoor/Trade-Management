package com.example.marketdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.marketdata.dto.MarketPriceResponse;
import com.example.marketdata.exception.MarketClosedException;
import com.example.marketdata.exception.SymbolNotFoundException;
import com.example.marketdata.services.MarketDataService;

@RestController
@RequestMapping("/api/v1/market")
public class MarketDataController {
	@Autowired
	private  MarketDataService marketDataService;

	@GetMapping("/price/{symbol}")
	public MarketPriceResponse getPrice(@PathVariable("symbol") String symbol) throws MarketClosedException, SymbolNotFoundException {
		return marketDataService.getPrice(symbol.toUpperCase());
	}
}

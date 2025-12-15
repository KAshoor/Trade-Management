package com.example.marketdata.servicesipm;

import org.springframework.stereotype.Service;

import com.example.marketdata.dto.MarketPriceResponse;
import com.example.marketdata.entities.MarketPrice;
import com.example.marketdata.exception.MarketClosedException;
import com.example.marketdata.exception.SymbolNotFoundException;
import com.example.marketdata.repositories.MarketPriceRepository;
import com.example.marketdata.services.MarketDataService;
import com.example.marketdata.services.MarketStatusService;

@Service
public class MarketDataServiceImpl implements MarketDataService {

	private final MarketPriceRepository marketPriceRepository;
	private final MarketStatusService marketStatusService;

	public MarketDataServiceImpl(MarketPriceRepository marketPriceRepository, MarketStatusService marketStatusService) {
		super();
		this.marketPriceRepository = marketPriceRepository;
		this.marketStatusService = marketStatusService;
	}

	public MarketPriceResponse getPrice(String symbol) throws MarketClosedException, SymbolNotFoundException {
		marketStatusService.validateMarketOpen();
		MarketPrice price = marketPriceRepository.findById(symbol)
				.orElseThrow(() -> new SymbolNotFoundException("Invalid symbol: " + symbol));
		MarketPriceResponse resp = new MarketPriceResponse();
		resp.setSymbol(price.getSymbol());
		resp.setBid(price.getBid());
		resp.setAsk(price.getAsk());
		resp.setLast(price.getLast());
		return resp;
	}
}

package com.example.marketdata.servicesipm;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

import org.springframework.stereotype.Service;

import com.example.marketdata.exception.MarketClosedException;
import com.example.marketdata.services.MarketStatusService;

@Service
public class MarketStatusServiceImpl implements MarketStatusService {

	@Override
	public void validateMarketOpen() throws MarketClosedException {
		LocalDateTime nowUae = LocalDateTime.now(ZoneId.of("Asia/Dubai"));
	    LocalTime currentTime = nowUae.toLocalTime();

	    LocalTime open = LocalTime.of(9, 30);
	    LocalTime close = LocalTime.of(16, 0);

	    if (currentTime.isBefore(open) || currentTime.isAfter(close)) {
	        throw new MarketClosedException("Market is closed");
	    }

	}

}

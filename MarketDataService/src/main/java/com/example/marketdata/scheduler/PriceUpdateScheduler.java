package com.example.marketdata.scheduler;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.example.marketdata.repositories.MarketPriceRepository;

import jakarta.annotation.PostConstruct;

@Component
public class PriceUpdateScheduler {
	private final MarketPriceRepository marketPriceRepository;
	private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
	private final Random random = new Random();

	public PriceUpdateScheduler(MarketPriceRepository marketPriceRepository) {
		this.marketPriceRepository = marketPriceRepository;
	}

	@PostConstruct
	public void start() {
		scheduler.scheduleAtFixedRate(this::updatePrices, 5, 5, TimeUnit.SECONDS);
	}

	private void updatePrices() {
		marketPriceRepository.findAll().forEach(price -> {
			BigDecimal delta = BigDecimal.valueOf((random.nextDouble() - 0.5) / 10);
			price.setLast(price.getLast().add(delta));
			price.setBid(price.getLast().subtract(BigDecimal.valueOf(0.05)));
			price.setAsk(price.getLast().add(BigDecimal.valueOf(0.05)));
			marketPriceRepository.save(price);
		});
	}
}

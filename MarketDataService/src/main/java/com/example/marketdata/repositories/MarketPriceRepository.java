package com.example.marketdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.marketdata.entities.MarketPrice;

@Repository
public interface MarketPriceRepository extends JpaRepository<MarketPrice, String> {

}

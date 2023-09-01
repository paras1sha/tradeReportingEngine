package com.example.tradereportingengine.repository;

import com.example.tradereportingengine.model.TradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends JpaRepository<TradeEntity, Long> {
    // Add custom query methods if needed
}

package com.trade.tradereportingengine.repository;

import com.trade.tradereportingengine.model.TradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends JpaRepository<TradeEntity, Long> {
    // Add custom query methods if needed
}

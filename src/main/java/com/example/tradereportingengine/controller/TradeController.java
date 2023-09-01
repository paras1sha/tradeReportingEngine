package com.example.tradereportingengine.controller;

import com.example.tradereportingengine.model.TradeEntity;
import com.example.tradereportingengine.service.TradeFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trade")
public class TradeController {
    private final TradeFilterService tradeFilterService;

    @Autowired
    public TradeController(TradeFilterService tradeFilterService) {
        this.tradeFilterService = tradeFilterService;
    }

    @GetMapping("reportingEngine/filtered")
    public List<TradeEntity> getFilteredEntiteis() {
        return tradeFilterService.filterEntities();
    }
}

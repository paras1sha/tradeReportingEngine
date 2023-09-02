package com.trade.tradereportingengine.controller;

import com.trade.tradereportingengine.model.TradeEntity;
import com.trade.tradereportingengine.service.TradeDataRunner;
import com.trade.tradereportingengine.service.TradeFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RestController
@RequestMapping("/trade")
public class TradeController {
    private final TradeFilterService tradeFilterService;
    private final TradeDataRunner tradeDataRunner;

    @Autowired
    public TradeController(TradeFilterService tradeFilterService, TradeDataRunner tradeDataRunner) {
        this.tradeFilterService = tradeFilterService;
        this.tradeDataRunner = tradeDataRunner;
    }
    @GetMapping("reportingEngine/fetchAll")
    public List<TradeEntity> getAllEntities() {
        return tradeFilterService.findAll();
    }
    @GetMapping("reportingEngine/filter/fetchAll")
    public List<TradeEntity> getFilteredEntities() {
        return tradeFilterService.filterEntities();
    }
    @PostMapping ("reportingEngine/saveAll")
    public ResponseEntity<?> saveAllEntities(@RequestBody List<String> fileNames) {
        try {
            tradeDataRunner.fetchDataFromSpecificFiles(fileNames);
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error Parsing Xml Files");
        }

        return ResponseEntity.ok().body("All Entries Saved");
    }
}

package com.example.tradereportingengine.service;

import com.example.tradereportingengine.Utils.LoggerInstance;
import com.example.tradereportingengine.model.TradeEntity;
import com.example.tradereportingengine.repository.TradeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TradeFilterService {

    private final TradeRepository tradeRepository;

    public TradeFilterService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public List<TradeEntity> filterEntities() {
        List<TradeEntity> allEntities = tradeRepository.findAll();
        List<TradeEntity> filteredEntities = new ArrayList<>();
        for (TradeEntity entity : allEntities) {
            if (isCriteriaMet(entity)) {
                filteredEntities.add(entity);
                LoggerInstance.logInfo("Retrieved filtered entities with id: " + entity.getId());
            }
        }
        return filteredEntities;
    }

    private boolean isCriteriaMet(TradeEntity tradeEntity) {
        if (!areAnagrams(tradeEntity.getSellerParty(), tradeEntity.getBuyerParty())) {
            return (tradeEntity.getBuyerParty().equals("EMU_BANK") && tradeEntity.getPremiumCurrency().equals("AUD"))
                    || (tradeEntity.getSellerParty().equals("BISON_BANK") && tradeEntity.getPremiumCurrency().equals("USD"));
        }
        return false;
    }
    private boolean areAnagrams(String str1, String str2) {
        // Remove spaces and convert both strings to lowercase
        str1 = str1.replaceAll("\\s", "").toLowerCase();
        str2 = str2.replaceAll("\\s", "").toLowerCase();

        // Check if the sorted strings are equal
        return Arrays.equals(str1.chars().sorted().toArray(), str2.chars().sorted().toArray());
    }


}

package com.trade.tradereportingengine.service;

import com.trade.tradereportingengine.Utils.LoggerInstance;
import com.trade.tradereportingengine.model.TradeEntity;
import com.trade.tradereportingengine.repository.TradeRepository;
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

    public List<TradeEntity> findAll() {
        return tradeRepository.findAll();
    }

    /**
     * Filter the entries to fetch specific data
     * @return
     */
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

    /**
     * Check the specific criteria
     * (Are not anagrams)
     * BuyerParty = EMU_BANK && Currency = AUD ||
     * SellerParty = Bison_Bank && Currency = USD
     * @param tradeEntity
     * @return
     */
    private boolean isCriteriaMet(TradeEntity tradeEntity) {
        if (!areAnagrams(tradeEntity.getSellerParty(), tradeEntity.getBuyerParty())) {
            return (tradeEntity.getBuyerParty().equals("EMU_BANK") && tradeEntity.getPremiumCurrency().equals("AUD"))
                    || (tradeEntity.getSellerParty().equals("BISON_BANK") && tradeEntity.getPremiumCurrency().equals("USD"));
        }
        return false;
    }

    /**
     * Sort the two strings and match if they are equal in order to check anagrams
     * @param str1
     * @param str2
     * @return
     */
    private boolean areAnagrams(String str1, String str2) {
        // Remove spaces and convert both strings to lowercase
        str1 = str1.replaceAll("\\s", "").toLowerCase();
        str2 = str2.replaceAll("\\s", "").toLowerCase();

        // Check if the sorted strings are equal
        return Arrays.equals(str1.chars().sorted().toArray(), str2.chars().sorted().toArray());
    }


}

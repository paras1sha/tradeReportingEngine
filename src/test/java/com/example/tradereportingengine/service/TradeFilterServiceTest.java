package com.example.tradereportingengine.service;


import com.example.tradereportingengine.model.TradeEntity;
import com.example.tradereportingengine.repository.TradeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TradeFilterServiceTest {
    @Mock
    private TradeRepository tradeRepository;

    @InjectMocks
    private TradeFilterService tradeFilterService;

    @Test
    public void testFilteredEntities_MatchSome() {
        // Mock data
        TradeEntity tradeEntity1 = new TradeEntity("EMU_BANK", "EMU_BANK", BigDecimal.valueOf(100.0), "AUD");
        TradeEntity tradeEntity2 = new TradeEntity("buyer2", "BISON_BANK", BigDecimal.valueOf(100.0), "USD");
        TradeEntity tradeEntity3 = new TradeEntity("buyer3", "EMU_BANK", BigDecimal.valueOf(100.0), "USD");
        TradeEntity tradeEntity4 = new TradeEntity("EMU_BANK", "seller1", BigDecimal.valueOf(100.0), "AUD");


        List<TradeEntity> allTradeEntities = Arrays.asList(tradeEntity1, tradeEntity2, tradeEntity3, tradeEntity4);

        // Mock the repository to return the test data
        when(tradeRepository.findAll()).thenReturn(allTradeEntities);

        // Test the filteredEntities method
        List<TradeEntity> filteredTradeEntities = tradeFilterService.filterEntities();

        // Assertions
        assertEquals(2, filteredTradeEntities.size()); // Two entities meet the criteria

        // Verify that the repository's findAll method was called
        verify(tradeRepository, times(1)).findAll();
    }

    @Test
    public void testEmptyEntities() {
        // Mock the repository to return an empty entity list
        when(tradeRepository.findAll()).thenReturn(List.of());

        List<TradeEntity> filterEntities = tradeFilterService.filterEntities();

        assertEquals(0, filterEntities.size()); // No events to filter
    }

    @Test
    public void testNoMatchEventEntities() {
        // Mock data
        TradeEntity tradeEntity1 = new TradeEntity("buyer1", "EMU_BANK", BigDecimal.valueOf(100.0), "USD");
        TradeEntity tradeEntity2 = new TradeEntity("buyer2", "BISON_BANK", BigDecimal.valueOf(100.0), "EUR");
        TradeEntity tradeEntity3 = new TradeEntity("buyer3", "EMU_BANK", BigDecimal.valueOf(50.0), "AUD");

        // Mock the repository to return the test data
        when(tradeRepository.findAll()).thenReturn(Arrays.asList(tradeEntity1, tradeEntity2, tradeEntity3));

        List<TradeEntity> filterEntities = tradeFilterService.filterEntities();

        assertEquals(0, filterEntities.size()); // No entity meet the criteria
    }

    @Test
    public void testFilteredEntities_AnagramCheck() {
        // Mock data
        TradeEntity tradeEntity1 = new TradeEntity("angel ", "glean", BigDecimal.valueOf(100.0), "USD");
        TradeEntity tradeEntity2 = new TradeEntity("peach  ", "cheap", BigDecimal.valueOf(100.0), "USD");

        // Mock the repository to return the test data
        when(tradeRepository.findAll()).thenReturn(Arrays.asList(tradeEntity1, tradeEntity2));

        List<TradeEntity> filterEntities = tradeFilterService.filterEntities();

        assertEquals(0, filterEntities.size()); // No entity meet the criteria
    }

}
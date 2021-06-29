package com.mitchmele.optionsking;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

import com.mitchmele.optionsking.option.CallOptionRepository;
import com.mitchmele.optionsking.option.PutOptionRepository;
import com.mitchmele.optionsking.stock.StockRepository;
import com.mitchmele.optionsking.stockmetadata.StockMetadataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StockDataProducerTest {

    @Mock
    private StockRepository stockRepository;

    @Mock
    private CallOptionRepository callRepository;

    @Mock
    private PutOptionRepository putRepository;

    @Mock
    private StockMetadataService stockMetadataService;

    @InjectMocks
    private StockDataProducer stockDataProducer;


    @Test
    void run_callsStockMetadataServiceToGetSymbolAndPrice() {

        List<String> symbolsToGet = asList("AAMC", "AAIT", "AAME", "AAN");



    }
}
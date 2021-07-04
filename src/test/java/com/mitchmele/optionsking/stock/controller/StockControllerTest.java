package com.mitchmele.optionsking.stock.controller;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitchmele.optionsking.option.CallOption;
import com.mitchmele.optionsking.stock.Stock;
import com.mitchmele.optionsking.stock.service.StockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class StockControllerTest {

    @Mock
    private StockService stockService;

    @InjectMocks
    private StockController stockController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        stockController = new StockController(stockService);
        mockMvc = MockMvcBuilders.standaloneSetup(stockController).build();
    }

    @Test
    void getCalls_returnsAllCallOptionsForProvidedSymbol() throws Exception {

        Date dec = new GregorianCalendar(2021, GregorianCalendar.DECEMBER, 15).getTime();

        Stock mmmStock = Stock.builder().symbol("MMM").lastPrice(35.00).build();

        CallOption call1 = CallOption.builder().stock(mmmStock).month("DEC").expirationDate(dec).strikePrice(34.00).build();
        CallOption call2 = CallOption.builder().stock(mmmStock).month("DEC").expirationDate(dec).strikePrice(36.00).build();

        Set<CallOption> calls = new HashSet<>(asList(call1, call2));

        when(stockService.getCallsForSymbol(anyString())).thenReturn(calls);

        mockMvc.perform(get("/api/v1/options/calls?symbol=ABC"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(calls)));

        verify(stockService).getCallsForSymbol("ABC");
    }
}
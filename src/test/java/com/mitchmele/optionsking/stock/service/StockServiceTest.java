package com.mitchmele.optionsking.stock.service;


import com.mitchmele.optionsking.option.CallOption;
import com.mitchmele.optionsking.stock.Stock;
import com.mitchmele.optionsking.stock.StockRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StockServiceTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockService stockService;

    @Test
    void getCallsForSymbol_returnsCallOptionsForAGivenStockSymbol() {

        Date dec = new GregorianCalendar(2021, GregorianCalendar.DECEMBER, 15).getTime();

        Stock mmm = Stock.builder().symbol("MMM").lastPrice(35.00).build();

        CallOption call1 = CallOption.builder().stock(mmm).month("DEC").expirationDate(dec).strikePrice(34.00).build();
        CallOption call2 = CallOption.builder().stock(mmm).month("DEC").expirationDate(dec).strikePrice(36.00).build();

        List<CallOption> callOptions = asList(call1, call2);
        mmm.setCalls(new HashSet<>(callOptions));

        when(stockRepository.findBySymbol(anyString())).thenReturn(Optional.of(mmm));

        Set<CallOption> actual = stockService.getCallsForSymbol("MMM");

        assertTrue(actual.contains(call1));

        verify(stockRepository).findBySymbol("MMM");
    }
}
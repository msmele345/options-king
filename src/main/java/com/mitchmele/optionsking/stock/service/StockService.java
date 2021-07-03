package com.mitchmele.optionsking.stock.service;

import com.mitchmele.optionsking.option.CallOption;
import com.mitchmele.optionsking.option.Option;
import com.mitchmele.optionsking.stock.Stock;
import com.mitchmele.optionsking.stock.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import static java.util.Collections.emptyList;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    public List<Option> getAllOptions() {
        return emptyList();
    }

    public Set<CallOption> getCallsForSymbol(String symbol) {
        return stockRepository.findBySymbol(symbol)
                .map(Stock::getCalls)
                .orElse(null);
    }
}

package com.mitchmele.optionsking.stock.controller;

import com.mitchmele.optionsking.option.CallOption;
import com.mitchmele.optionsking.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Set;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping("/options/calls")
    public Set<CallOption> getCalls(@RequestParam String symbol) {
        return stockService.getCallsForSymbol(symbol);
    }
}

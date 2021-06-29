package com.mitchmele.optionsking.stockmetadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LiveQuote {

    private BigDecimal bid;
    private BigDecimal ask;
    private String symbol;
}

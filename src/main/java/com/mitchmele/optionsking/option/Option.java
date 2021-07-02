package com.mitchmele.optionsking.option;

import com.mitchmele.optionsking.stock.Stock;

public interface Option {

    double getStrikePrice();
    Stock getStock();
    String getMonth();
}

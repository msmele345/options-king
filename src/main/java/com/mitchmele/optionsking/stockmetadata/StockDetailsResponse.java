package com.mitchmele.optionsking.stockmetadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class StockDetailsResponse {

    private LiveQuote liveQuote;
    private StockMetadata stockMetadata;
}

package com.mitchmele.optionsking.stockmetadata;

import com.mitchmele.optionsking.stockmetadata.helper.UriHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class MetadataService {

    private final UriHelper uriHelper;
    private final RestTemplate restTemplate;

    public StockDetailsResponse getStockMetadata(String symbol) {
        return restTemplate.getForObject(uriHelper.buildUrl(symbol), StockDetailsResponse.class);
    }
}

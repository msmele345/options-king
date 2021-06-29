package com.mitchmele.optionsking.stockmetadata;


import com.mitchmele.optionsking.stockmetadata.config.OptionsLoungeProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class StockMetadataService {

    private final RestTemplate restTemplate;
    private final OptionsLoungeProperties optionsLoungeProperties;

    public StockDetailsResponse getStockMetadata(String symbol) {
        return restTemplate.getForObject(optionsLoungeProperties.getUrl(), StockDetailsResponse.class);
    }
}

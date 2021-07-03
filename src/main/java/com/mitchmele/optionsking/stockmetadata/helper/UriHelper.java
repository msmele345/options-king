package com.mitchmele.optionsking.stockmetadata.helper;

import com.mitchmele.optionsking.stockmetadata.config.OptionsLoungeProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class UriHelper {

    private final OptionsLoungeProperties properties;

    public String buildUrl(String symbol) {
        return UriComponentsBuilder
                .fromHttpUrl(properties.getUrl())
                .queryParam("symbol", symbol)
                .toUriString();
    }
}

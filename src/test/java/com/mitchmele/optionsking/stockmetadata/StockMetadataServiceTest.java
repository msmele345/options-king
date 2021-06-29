package com.mitchmele.optionsking.stockmetadata;

import static com.mongodb.client.model.Filters.eq;

import com.mitchmele.optionsking.stockmetadata.config.OptionsLoungeProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StockMetadataServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private OptionsLoungeProperties properties;

    @InjectMocks
    private StockMetadataService stockMetadataService;

    @Test
    void getStockDetails_callsInterstellarToGetStockMetaData() {

        StockMetadata expectedMetadata = StockMetadata.builder()
                .ticker("ABC")
                .sector("Financial")
                .industry("Exchange Traded Fund")
                .company("Mellon Focused Growth ADR ETF")
                .averageVolume(10.07)
                .build();

        LiveQuote expectedLiveQuote = LiveQuote.builder()
                .symbol("ABC")
                .bid(BigDecimal.valueOf(20.10))
                .ask(BigDecimal.valueOf(20.13))
                .build();

        StockDetailsResponse expected = StockDetailsResponse.builder()
                .liveQuote(expectedLiveQuote)
                .stockMetadata(expectedMetadata)
                .build();

        when(properties.getUrl()).thenReturn("www.url.com");

        when(restTemplate.getForObject(anyString(), any(), (Object) any())).thenReturn(expected);

        StockDetailsResponse actual = stockMetadataService.getStockMetadata("ABC");

        assertThat(actual).isEqualTo(expected);
        verify(properties).getUrl();
        verify(restTemplate).getForObject("www.url.com", StockDetailsResponse.class);
    }
}
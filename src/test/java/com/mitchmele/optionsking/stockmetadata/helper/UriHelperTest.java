package com.mitchmele.optionsking.stockmetadata.helper;

import com.mitchmele.optionsking.stockmetadata.config.OptionsLoungeProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UriHelperTest {

    @Mock
    private OptionsLoungeProperties properties;

    @InjectMocks
    private UriHelper uriHelper;

    @ParameterizedTest
    @MethodSource("provideSymbolsForBuildUrl")
    void buildUrl_buildsUrlWithQueryParamForSymbol(String symbol, String expected) {

        when(properties.getUrl()).thenReturn("http://localhost:8081/api/vi/stocks");

        assertThat(uriHelper.buildUrl(symbol)).isEqualTo(expected);

        verify(properties).getUrl();
    }

    private static Stream<Arguments> provideSymbolsForBuildUrl() {
        return Stream.of(
                Arguments.of("ABC", "http://localhost:8081/api/vi/stocks?symbol=ABC"),
                Arguments.of("ZZZ", "http://localhost:8081/api/vi/stocks?symbol=ZZZ")
        );
    }
}
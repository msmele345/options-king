package com.mitchmele.optionsking.stockmetadata.config;

import lombok.RequiredArgsConstructor;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import java.security.cert.X509Certificate;

@Configuration
@RequiredArgsConstructor
public class SslConfig {

    @Bean
    public SSLContext trustingSslContext() {
        try {
            TrustStrategy trustStrategy = (X509Certificate[] chain, String authType) -> true;
            return SSLContexts
                    .custom()
                    .loadTrustMaterial(null, trustStrategy)
                    .build();
        } catch (Exception e) {
            return null;
        }
    }
}
package com.mitchmele.optionsking.stock;

import com.mitchmele.optionsking.option.CallOptionRepository;
import com.mitchmele.optionsking.option.PutOptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StockRepositoryTest {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private CallOptionRepository callOptionRepository;

    @Autowired
    private PutOptionRepository putOptionRepository;

    @BeforeEach
    void setUp() {

    }
}
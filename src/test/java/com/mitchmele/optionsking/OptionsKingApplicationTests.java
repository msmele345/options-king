package com.mitchmele.optionsking;

import com.mitchmele.optionsking.option.CallOption;
import com.mitchmele.optionsking.option.CallOptionRepository;
import com.mitchmele.optionsking.stock.Stock;
import com.mitchmele.optionsking.stock.StockRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.HashSet;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class OptionsKingApplicationTests {

    @Autowired
    StockRepository stockRepository;

    @Autowired
    CallOptionRepository callOptionRepository;

    @Test
    void contextLoads() {
    }

    @AfterEach
    void tearDown() {
//        stockRepository.deleteAll();
    }

    @BeforeEach
    void setUp() {
//        callOptionRepository.deleteAll();
//        stockRepository.deleteAll();
    }

    @Test
    void setCalls() {

        Stock stock = Stock.builder().symbol("XXX").lastPrice(15.00).build();

        Stock actualStock = stockRepository.saveAndFlush(stock);

        CallOption call1 = CallOption.builder().stock(actualStock).month("DEC").strikePrice(14.00).build();
        callOptionRepository.saveAndFlush(call1);

        HashSet<CallOption> calls = new HashSet<>();
        calls.add(call1);

        actualStock.setCalls(calls);

        Stock actualStock2 = stockRepository.save(actualStock);

        System.out.println(actualStock2);
    }

    @Test
    void setMoreCalls() {

        Optional<Stock> stock = stockRepository.findById(70L);

        assertThat(stock).isPresent();

        CallOption call2 = CallOption.builder().stock(stock.get()).strikePrice(16.00).month("DEC").build();

        callOptionRepository.saveAndFlush(call2);

        System.out.println("CHECK");
    }
}

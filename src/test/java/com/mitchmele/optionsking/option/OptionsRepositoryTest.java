package com.mitchmele.optionsking.option;

import com.mitchmele.optionsking.stock.Stock;
import com.mitchmele.optionsking.stock.StockRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;
import static java.util.Arrays.asList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class OptionsRepositoryTest {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private CallOptionRepository callOptionRepository;

    private Stock actualMmmStock;
    private Stock actualHhhStock;

    @BeforeEach
    void setUp() {

        Date march = new GregorianCalendar(2021, GregorianCalendar.MARCH, 15).getTime();
        Date dec = new GregorianCalendar(2021, GregorianCalendar.DECEMBER, 15).getTime();

        Stock mmm = Stock.builder().symbol("MMM").lastPrice(35.00).build();
        Stock hhh = Stock.builder().symbol("HHH").lastPrice(90.00).build();

        Stock mmmStock = stockRepository.saveAndFlush(mmm);
        Stock hhhStock = stockRepository.saveAndFlush(hhh);

        Stock mmmFinal = stockRepository.save(mmmStock);
        Stock hhhFinal = stockRepository.save(hhhStock);

        CallOption call1 = CallOption.builder().stock(mmmStock).month("DEC").expirationDate(dec).strikePrice(34.00).build();
        CallOption call2 = CallOption.builder().stock(mmmStock).month("DEC").expirationDate(dec).strikePrice(36.00).build();
        CallOption call3 = CallOption.builder().stock(hhhStock).month("MAR").expirationDate(march).strikePrice(94.00).build();
        CallOption call4 = CallOption.builder().stock(hhhStock).month("MAR").expirationDate(march).strikePrice(92.00).build();

        callOptionRepository.saveAllAndFlush(asList(call1, call2, call3, call4));

        HashSet<CallOption> marchCalls = new HashSet<>();
        marchCalls.add(call3);
        marchCalls.add(call4);

        HashSet<CallOption> decCalls = new HashSet<>();
        marchCalls.add(call1);
        marchCalls.add(call2);

        mmmFinal.setCalls(decCalls);
        hhhFinal.setCalls(marchCalls);

        actualMmmStock = stockRepository.save(mmmFinal);
        actualHhhStock = stockRepository.save(hhhFinal);
    }

    @AfterEach
    void tearDown() {
        callOptionRepository.deleteAll();
        stockRepository.deleteAll();
    }

    @Test
    void findAllByStockId() {

        Long mmmId = actualMmmStock.getId();

        List<CallOption> actual = callOptionRepository.findAllByStockId(mmmId);

        assertThat(actual.size()).isEqualTo(2);

        actual.forEach((CallOption call) -> {
            assertThat(call.getStock().getSymbol()).isEqualTo("MMM");
        });
    }

    @Test
    void findAllByMonth() {

        List<CallOption> actual = callOptionRepository.findAllByMonth("DEC");

        assertThat(actual.size()).isEqualTo(2);
    }

    @Test
    void findAllByStrikePrice() {

        List<CallOption> actual = callOptionRepository.findCallOptionByStrikePrice(36.00);

        assertThat(actual.size()).isEqualTo(1);
        assertThat(actual.get(0).getStrikePrice()).isEqualTo(36.00);
        assertThat(actual.get(0).getMonth()).isEqualTo("DEC");
    }

    @Test
    void stockCalls() {

        Optional<Stock> actual = stockRepository.findBySymbol("MMM");

        assertThat(actual).isPresent();
    }

    private static HashSet<Option> setOptions(List<Option> options) {
        return new HashSet<>(options);
    }
}
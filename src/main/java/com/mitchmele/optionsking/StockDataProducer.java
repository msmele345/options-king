package com.mitchmele.optionsking;

import com.mitchmele.optionsking.option.CallOption;
import com.mitchmele.optionsking.option.CallOptionRepository;
import com.mitchmele.optionsking.option.PutOption;
import com.mitchmele.optionsking.option.PutOptionRepository;
import com.mitchmele.optionsking.stock.Stock;
import com.mitchmele.optionsking.stock.StockRepository;
import com.mitchmele.optionsking.stockmetadata.MetadataService;
import com.mitchmele.optionsking.stockmetadata.StockDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

import static java.util.Arrays.asList;

@Component
@RequiredArgsConstructor
public class StockDataProducer implements ApplicationRunner {

    private final StockRepository stockRepository;
    private final MetadataService metadataService;
    private final CallOptionRepository callOptionRepository;
    private final PutOptionRepository putOptionRepository;

    public static final List<String> symbolsToLoad = asList("AAMC", "AAIT", "AAME", "AAN", "AAOI", "AAON", "AAPL", "AAP", "AA", "AAU", "AAT", "AAXJ", "AAWW", "AAV", "ABAX", "ABBV", "AB", "ABB", "ABC");

    private static final Date MARCH = new GregorianCalendar(2021, GregorianCalendar.MARCH, 15).getTime();
    private static final Date DEC = new GregorianCalendar(2021, GregorianCalendar.DECEMBER, 15).getTime();

    @Override
    public void run(ApplicationArguments args) throws Exception {

        for (String s : symbolsToLoad) {

            StockDetailsResponse response = metadataService.getStockMetadata(s);
            double price = response.getStockMetadata().getPrice();

            Stock stock = Stock.builder().symbol(s).lastPrice(price).build();

            Stock stockEntity = stockRepository.saveAndFlush(stock);

            Stock finalStock = stockRepository.save(stockEntity);

            CallOption call1 = CallOption.builder().stock(stockEntity).month("DEC").strikePrice(price + 1).build();
            CallOption call2 = CallOption.builder().stock(stockEntity).month("DEC").strikePrice(price + 2).build();
            CallOption call3 = CallOption.builder().stock(stockEntity).month("DEC").strikePrice(price + 5).build();
            CallOption call4 = CallOption.builder().stock(stockEntity).month("DEC").strikePrice(price - 2).build();

            PutOption put1 = PutOption.builder().stock(stockEntity).month("SEPT").strikePrice(price + 1).build();
            PutOption put2 = PutOption.builder().stock(stockEntity).month("SEPT").strikePrice(price + 2).build();
            PutOption put3 = PutOption.builder().stock(stockEntity).month("SEPT").strikePrice(price + 5).build();
            PutOption put4 = PutOption.builder().stock(stockEntity).month("SEPT").strikePrice(price - 2).build();

            List<CallOption> callList = asList(call1, call2, call3, call4);
            callOptionRepository.saveAllAndFlush(callList);
            List<PutOption> putList = asList(put1, put2, put3, put4);
            putOptionRepository.saveAllAndFlush(putList);

            Set<CallOption> calls = new HashSet<>(callList);
            Set<PutOption> puts = new HashSet<>(putList);

            finalStock.setCalls(calls);
            finalStock.setPuts(puts);

            stockRepository.saveAndFlush(finalStock);
            System.out.println("MADE IT");
        }
    }
}


/*
*
*         Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });

        Thread t2 = new Thread(() -> System.out.println("Hi"));
*
* */
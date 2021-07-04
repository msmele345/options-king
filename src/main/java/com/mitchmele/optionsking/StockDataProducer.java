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
    public void run(ApplicationArguments args) {

        for (String s : symbolsToLoad) {

            StockDetailsResponse response = metadataService.getStockMetadata(s);
            double price = response.getStockMetadata().getPrice();

            Stock stock = Stock.builder().symbol(s).lastPrice(price).build();

            Stock stockEntity = stockRepository.saveAndFlush(stock);

            Stock finalStock = stockRepository.save(stockEntity);

            CallOption call1 = CallOption.builder().stock(stockEntity).month("DEC").expirationDate(DEC).strikePrice(Math.floor(price) + 1).build();
            CallOption call2 = CallOption.builder().stock(stockEntity).month("DEC").expirationDate(DEC).strikePrice(Math.floor(price) + 2).build();
            CallOption call3 = CallOption.builder().stock(stockEntity).month("DEC").expirationDate(DEC).strikePrice(Math.floor(price) + 5).build();
            CallOption call4 = CallOption.builder().stock(stockEntity).month("DEC").expirationDate(DEC).strikePrice(Math.floor(price) - 2).build();
            CallOption call5 = CallOption.builder().stock(stockEntity).month("DEC").expirationDate(DEC).strikePrice(Math.floor(price) - 3).build();

            PutOption put1 = PutOption.builder().stock(stockEntity).month("SEPT").expirationDate(MARCH).strikePrice(Math.floor(price) + 1).build();
            PutOption put2 = PutOption.builder().stock(stockEntity).month("SEPT").expirationDate(MARCH).strikePrice(Math.floor(price) + 2).build();
            PutOption put3 = PutOption.builder().stock(stockEntity).month("SEPT").expirationDate(MARCH).strikePrice(Math.floor(price) + 5).build();
            PutOption put4 = PutOption.builder().stock(stockEntity).month("SEPT").expirationDate(MARCH).strikePrice(Math.floor(price) - 2).build();
            PutOption put5 = PutOption.builder().stock(stockEntity).month("SEPT").expirationDate(MARCH).strikePrice(Math.floor(price) - 3).build();

            List<CallOption> callList = asList(call1, call2, call3, call4, call5);
            callOptionRepository.saveAllAndFlush(callList);
            List<PutOption> putList = asList(put1, put2, put3, put4, put5);
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
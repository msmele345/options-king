package com.mitchmele.optionsking;

import com.mitchmele.optionsking.option.CallOptionRepository;
import com.mitchmele.optionsking.option.PutOptionRepository;
import com.mitchmele.optionsking.stock.StockRepository;
import com.mitchmele.optionsking.stockmetadata.MetadataService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import static java.util.Arrays.asList;

@Component
@RequiredArgsConstructor
public class StockDataProducer implements ApplicationRunner {

    private final StockRepository stockRepository;
    private final RestTemplate restTemplate;
    private final MetadataService metadataService;
    private final CallOptionRepository callOptionRepository;
    private final PutOptionRepository putOptionRepository;

    public static final List<String> symbolsToLoad = asList("AAMC", "AAIT", "AAME", "AAN", "AAOI", "AAON", "AAPL", "AAP", "AA", "AAU", "AAT", "AAXJ", "AAWW", "AAV", "ABAX", "ABBV", "AB", "ABB", "ABC");

    //create stock with symbol
    //create service to scaffold call and put entities
    //save
    //setup repos to query with one to many relationships for front end calls to here

    @Override
    public void run(ApplicationArguments args) throws Exception {

//        for (int i = 0; i < symbolsToLoad.size(); i++) {
//            String url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/api/v1/stocks")
//                    .queryParam("symbol", symbolsToLoad.get(i))
//                    .toUriString();
//
//            StockDetailsResponse response = restTemplate.getForObject(url, StockDetailsResponse.class);
//            double price = response.getStockMetadata().getPrice();
//
//            Stock stock = Stock.builder().id((long) i).symbol(symbolsToLoad.get(i)).lastPrice(price).build();
//
//            stockRepository.saveAndFlush(stock);
//
//            CallOption call1 = CallOption.builder().stock(stock).currentPrice(price).strikePrice(price + 1).build();
//            CallOption call2 = CallOption.builder().stock(stock).currentPrice(price).strikePrice(price + 2).build();
//            CallOption call3 = CallOption.builder().stock(stock).currentPrice(price).strikePrice(price + 5).build();
//            CallOption call4 = CallOption.builder().stock(stock).currentPrice(price).strikePrice(price -2).build();
//
//            PutOption put1 = PutOption.builder().stock(stock).currentPrice(price).strikePrice(price + 1).build();
//            PutOption put2 = PutOption.builder().stock(stock).currentPrice(price).strikePrice(price + 2).build();
//            PutOption put3 = PutOption.builder().stock(stock).currentPrice(price).strikePrice(price + 5).build();
//            PutOption put4 = PutOption.builder().stock(stock).currentPrice(price).strikePrice(price -2).build();
//
//            List<CallOption> callList = asList(call1, call2, call3, call4);
//            callOptionRepository.saveAllAndFlush(callList);
//            List<PutOption> putList = asList(put1, put2, put3, put4);
//            putOptionRepository.saveAllAndFlush(putList);
//
//            Set<CallOption> calls = new HashSet<>(callList);
//            Set<PutOption> puts = new HashSet<>(putList);
//
//            stock.setCalls(calls);
//            stock.setPuts(puts);
//
//            stockRepository.saveAndFlush(stock);
//        }

//        symbolsToLoad.forEach(symbol -> {
//            String url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/stocks")
//                    .queryParam("symbol", symbol)
//                    .toUriString();
//
//            StockDetailsResponse response = restTemplate.getForObject(url, StockDetailsResponse.class);
//
//            Stock stock = Stock.builder().id().build();
//            callOptionRepository.saveAndFlush(CallOption.builder().stock().build())
//        });
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
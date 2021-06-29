package com.mitchmele.optionsking;

import com.mitchmele.optionsking.option.CallOptionRepository;
import com.mitchmele.optionsking.option.PutOptionRepository;
import com.mitchmele.optionsking.stock.StockRepository;
import com.mitchmele.optionsking.stockmetadata.StockMetadataService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.asList;

@Component
@RequiredArgsConstructor
public class StockDataProducer implements ApplicationRunner {

    private final StockRepository stockRepository;
    private final StockMetadataService stockMetadataService;
    private final CallOptionRepository callOptionRepository;
    private final PutOptionRepository putOptionRepository;

    public static final List<String> symbolsToLoad = asList("AAMC", "AAIT", "AAME", "AAN", "AAOI", "AAON", "AAPL", "AAP", "AA", "AAU", "AAT", "AAXJ", "AAWW", "AAV", "ABAX", "ABBV", "AB", "ABB", "ABC");

    //create stock with symbol
    //create service to scaffold call and put entities
    //save
    //setup repos to query with one to many relationships for front end calls to here

    @Override
    public void run(ApplicationArguments args) throws Exception {
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
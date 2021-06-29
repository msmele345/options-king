package com.mitchmele.optionsking;

import com.mitchmele.optionsking.option.CallOptionRepository;
import com.mitchmele.optionsking.option.PutOptionRepository;
import com.mitchmele.optionsking.stock.StockRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class OptionsKingApplication {

	public static void main(String[] args) {
		SpringApplication.run(OptionsKingApplication.class, args);
	}
}

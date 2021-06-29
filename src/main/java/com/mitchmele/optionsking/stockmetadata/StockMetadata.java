package com.mitchmele.optionsking.stockmetadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "stocks")
public class StockMetadata {

	private String ticker;
	private double price;
	private String sector;
	private String company;
	private double fiftyDaySimpleMovingAverage;
	private double performanceYTD;
	private double relativeStrengthIndex14;
	private double averageTrueRange;
	private double performanceHalfYear;
	private String industry;
	private double averageVolume;
	private double performanceYear;
	private double twoHundredDaySimpleMovingAverage;
	private double volatilityMonth;
	private double performanceWeek;
	private double yearlyHigh;
	private double volatilityWeek;
	private double changeFromOpen;
	private double performanceMonth;
	private double dividendYield;
	private int volume;
	private double yearlyLow;
}
package com.mitchmele.optionsking.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findBySymbol(String symbol);


//    @Query("select * from stock s left join calls c on s.id=c.stock_id where c.month = :month")
//    List<Stock> stocksWithCalls(@Param("month") String month);
}
//"select u from User u where u.emailAddress = ?1"
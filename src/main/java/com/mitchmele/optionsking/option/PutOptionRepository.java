package com.mitchmele.optionsking.option;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PutOptionRepository extends JpaRepository<PutOption, Long> {

    List<PutOption> findAllByMonth(String month);
    List<PutOption> findAllByStockId(Long stock_id);
    List<PutOption> findPutOptionByStrikePrice(double strikePrice);
}

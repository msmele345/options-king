package com.mitchmele.optionsking.option;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CallOptionRepository extends JpaRepository<CallOption, Long> {

    List<CallOption> findAllByMonth(String month);
    List<CallOption> findAllByStockId(Long stock_id);
    List<CallOption> findCallOptionByStrikePrice(double strikePrice);
}

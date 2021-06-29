package com.mitchmele.optionsking.option;

import com.mitchmele.optionsking.stock.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CallOptionRepository extends JpaRepository<CallOption, Long> {
}

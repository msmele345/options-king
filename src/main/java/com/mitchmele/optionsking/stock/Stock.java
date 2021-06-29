package com.mitchmele.optionsking.stock;

import com.mitchmele.optionsking.option.CallOption;
import com.mitchmele.optionsking.option.PutOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "STOCK")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String symbol;

    @OneToMany(mappedBy = "stock")
    private Set<CallOption> calls;

    @OneToMany(mappedBy = "stock")
    private Set<PutOption> puts;

    @Column(name = "last_price")
    private double lastPrice;
}


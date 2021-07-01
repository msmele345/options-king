package com.mitchmele.optionsking.option;

import com.mitchmele.optionsking.stock.Stock;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PUTS")
public class PutOption {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;

    private String month;

    @Column(name = "strike_price")
    private double strikePrice;

    @Column(name = "expiration_date")
    private Date expirationDate;

    @CreationTimestamp
    @Column(name = "CREATED_TS", updatable = false)
    private Date createdAt;
}


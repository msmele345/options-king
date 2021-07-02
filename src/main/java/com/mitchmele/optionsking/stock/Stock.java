package com.mitchmele.optionsking.stock;

import com.mitchmele.optionsking.option.CallOption;
import com.mitchmele.optionsking.option.PutOption;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Builder(toBuilder = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "STOCK")
public class Stock implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String symbol;

    @OneToMany(mappedBy = "stock",fetch = FetchType.EAGER)
    private Set<CallOption> calls;

    @OneToMany(mappedBy = "stock",fetch = FetchType.EAGER)
    private Set<PutOption> puts;

    @Column(name = "last_price")
    private double lastPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Set<CallOption> getCalls() {
        return calls;
    }

    public void setCalls(Set<CallOption> calls) {
        this.calls = calls;
    }

    public Set<PutOption> getPuts() {
        return puts;
    }

    public void setPuts(Set<PutOption> puts) {
        this.puts = puts;
    }

    public double getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(double lastPrice) {
        this.lastPrice = lastPrice;
    }
}


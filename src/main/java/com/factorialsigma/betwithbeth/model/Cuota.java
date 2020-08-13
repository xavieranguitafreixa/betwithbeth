package com.factorialsigma.betwithbeth.model;

import com.factorialsigma.betwithbeth.model.MarketType.MarketSide;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author
 * @version 1.0
 */
@Entity
@Getter
@Setter
public class Cuota {

    @Id
    @GeneratedValue
    private int id;

    private Date date;

    private float one;

    private float x;

    private float two;

    private String url;

    @ManyToOne
    private Market market;

    @ManyToOne
    private BettingHouse bettingHouse;

    @Transient
    private float _index;
}

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
public class Runner {

    @Id
    @GeneratedValue
    private int id;

    @Enumerated(EnumType.STRING)
    private MarketSide side;

    private Date date;

    private float value;

    private String url;

    @ManyToOne
    private Market market;

    @ManyToOne
    private BettingHouse bettingHouse;
}

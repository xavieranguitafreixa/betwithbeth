package com.factorialsigma.betwithbeth.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.factorialsigma.betwithbeth.model.MarketType.MarketSide;

import lombok.Getter;
import lombok.Setter;

/**
 * @author
 * @version 1.0
 */
@Entity
@Getter
@Setter
public class Market {

    @Id
    @GeneratedValue
    private int id;

    private Date lastUpdate;

    private Boolean currentInPlay;

    private Integer currentHomeScore;

    private Integer currentVisitorScore;

    @Enumerated(EnumType.STRING)
    private MarketSide currentResult;

    @Enumerated(EnumType.STRING)
    private MarketType marketType;


    @OneToMany(mappedBy = "market")
    private List<Cuota> cuotas;

    @ManyToOne
    private Event event;

    @Transient
    private Cuota _sureBet;

    @Transient
    private Cuota _maxBet;

}

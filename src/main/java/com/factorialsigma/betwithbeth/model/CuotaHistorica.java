package com.factorialsigma.betwithbeth.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * @author
 * @version 1.0
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CuotaHistorica {
    @Id
//    @GeneratedValue
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
}

package com.factorialsigma.betwithbeth.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @author
 * @version 1.0
 */
@Entity
@Getter
@Setter
public class BettingHouse {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String url;

    private String iconUrl;

    @OneToMany(mappedBy = "bettingHouse")
    private List<Cuota> cuotas;

}

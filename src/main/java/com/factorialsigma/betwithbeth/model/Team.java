package com.factorialsigma.betwithbeth.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author
 * @version 1.0
 */
@Entity
@Getter
@Setter
public class Team {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private Date creationDate = new Date();

    private String country;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private Sport sport;

    @OneToMany(mappedBy = "homeTeam")
    private List<Event> eventosHome;

    @OneToMany(mappedBy = "visitingTeam")
    private List<Event> eventosVisiting;

}

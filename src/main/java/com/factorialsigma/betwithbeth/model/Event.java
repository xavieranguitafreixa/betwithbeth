package com.factorialsigma.betwithbeth.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author
 * @version 1.0
 */
@Entity
@Getter
@Setter
public class Event {

    @Id
    @GeneratedValue
    private int eventId;

    private Date creationDate;

    private Date startDate;

    private Date endDate;

    private String fullName;

    @ManyToOne
    private Team homeTeam;

    @ManyToOne
    private Team visitingTeam;

    @Enumerated(EnumType.STRING)
    private Sport sport;

    @OneToMany(mappedBy = "event")
    private List<Market> markets = new ArrayList<>();
}

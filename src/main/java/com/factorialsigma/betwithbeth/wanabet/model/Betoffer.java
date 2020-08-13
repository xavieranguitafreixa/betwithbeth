
package com.factorialsigma.betwithbeth.wanabet.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "live",
    "closed",
    "criterion",
    "betOfferType",
    "eventId",
    "outcomes",
    "main",
    "prematch"
})
public class Betoffer {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("live")
    private Boolean live;
    @JsonProperty("closed")
    private String closed;
    @JsonProperty("criterion")
    private Criterion criterion;
    @JsonProperty("betOfferType")
    private BetOfferType betOfferType;
    @JsonProperty("eventId")
    private Long eventId;
    @JsonProperty("outcomes")
    private List<Outcome> outcomes = new ArrayList<Outcome>();
    @JsonProperty("main")
    private Boolean main;
    @JsonProperty("prematch")
    private Boolean prematch;

    /**
     * 
     * @return
     *     The id
     */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The live
     */
    @JsonProperty("live")
    public Boolean getLive() {
        return live;
    }

    /**
     * 
     * @param live
     *     The live
     */
    @JsonProperty("live")
    public void setLive(Boolean live) {
        this.live = live;
    }

    /**
     * 
     * @return
     *     The closed
     */
    @JsonProperty("closed")
    public String getClosed() {
        return closed;
    }

    /**
     * 
     * @param closed
     *     The closed
     */
    @JsonProperty("closed")
    public void setClosed(String closed) {
        this.closed = closed;
    }

    /**
     * 
     * @return
     *     The criterion
     */
    @JsonProperty("criterion")
    public Criterion getCriterion() {
        return criterion;
    }

    /**
     * 
     * @param criterion
     *     The criterion
     */
    @JsonProperty("criterion")
    public void setCriterion(Criterion criterion) {
        this.criterion = criterion;
    }

    /**
     * 
     * @return
     *     The betOfferType
     */
    @JsonProperty("betOfferType")
    public BetOfferType getBetOfferType() {
        return betOfferType;
    }

    /**
     * 
     * @param betOfferType
     *     The betOfferType
     */
    @JsonProperty("betOfferType")
    public void setBetOfferType(BetOfferType betOfferType) {
        this.betOfferType = betOfferType;
    }

    /**
     * 
     * @return
     *     The eventId
     */
    @JsonProperty("eventId")
    public Long getEventId() {
        return eventId;
    }

    /**
     * 
     * @param eventId
     *     The eventId
     */
    @JsonProperty("eventId")
    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    /**
     * 
     * @return
     *     The outcomes
     */
    @JsonProperty("outcomes")
    public List<Outcome> getOutcomes() {
        return outcomes;
    }

    /**
     * 
     * @param outcomes
     *     The outcomes
     */
    @JsonProperty("outcomes")
    public void setOutcomes(List<Outcome> outcomes) {
        this.outcomes = outcomes;
    }

    /**
     * 
     * @return
     *     The main
     */
    @JsonProperty("main")
    public Boolean getMain() {
        return main;
    }

    /**
     * 
     * @param main
     *     The main
     */
    @JsonProperty("main")
    public void setMain(Boolean main) {
        this.main = main;
    }

    /**
     * 
     * @return
     *     The prematch
     */
    @JsonProperty("prematch")
    public Boolean getPrematch() {
        return prematch;
    }

    /**
     * 
     * @param prematch
     *     The prematch
     */
    @JsonProperty("prematch")
    public void setPrematch(Boolean prematch) {
        this.prematch = prematch;
    }

}

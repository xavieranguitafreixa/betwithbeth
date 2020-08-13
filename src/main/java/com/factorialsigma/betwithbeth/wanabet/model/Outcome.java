
package com.factorialsigma.betwithbeth.wanabet.model;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "label",
    "odds",
    "type",
    "betOfferId",
    "changedDate",
    "oddsFractional",
    "oddsAmerican"
})
public class Outcome {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("label")
    private String label;
    @JsonProperty("odds")
    private Integer odds;
    @JsonProperty("type")
    private String type;
    @JsonProperty("betOfferId")
    private Integer betOfferId;
    @JsonProperty("changedDate")
    private String changedDate;
    @JsonProperty("oddsFractional")
    private String oddsFractional;
    @JsonProperty("oddsAmerican")
    private String oddsAmerican;

    /**
     * 
     * @return
     *     The id
     */
    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The label
     */
    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    /**
     * 
     * @param label
     *     The label
     */
    @JsonProperty("label")
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 
     * @return
     *     The odds
     */
    @JsonProperty("odds")
    public Integer getOdds() {
        return odds;
    }

    /**
     * 
     * @param odds
     *     The odds
     */
    @JsonProperty("odds")
    public void setOdds(Integer odds) {
        this.odds = odds;
    }

    /**
     * 
     * @return
     *     The type
     */
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The betOfferId
     */
    @JsonProperty("betOfferId")
    public Integer getBetOfferId() {
        return betOfferId;
    }

    /**
     * 
     * @param betOfferId
     *     The betOfferId
     */
    @JsonProperty("betOfferId")
    public void setBetOfferId(Integer betOfferId) {
        this.betOfferId = betOfferId;
    }

    /**
     * 
     * @return
     *     The changedDate
     */
    @JsonProperty("changedDate")
    public String getChangedDate() {
        return changedDate;
    }

    /**
     * 
     * @param changedDate
     *     The changedDate
     */
    @JsonProperty("changedDate")
    public void setChangedDate(String changedDate) {
        this.changedDate = changedDate;
    }

    /**
     * 
     * @return
     *     The oddsFractional
     */
    @JsonProperty("oddsFractional")
    public String getOddsFractional() {
        return oddsFractional;
    }

    /**
     * 
     * @param oddsFractional
     *     The oddsFractional
     */
    @JsonProperty("oddsFractional")
    public void setOddsFractional(String oddsFractional) {
        this.oddsFractional = oddsFractional;
    }

    /**
     * 
     * @return
     *     The oddsAmerican
     */
    @JsonProperty("oddsAmerican")
    public String getOddsAmerican() {
        return oddsAmerican;
    }

    /**
     * 
     * @param oddsAmerican
     *     The oddsAmerican
     */
    @JsonProperty("oddsAmerican")
    public void setOddsAmerican(String oddsAmerican) {
        this.oddsAmerican = oddsAmerican;
    }

}

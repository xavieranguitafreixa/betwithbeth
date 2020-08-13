
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
    "betoffers",
    "events",
    "range"
})
public class BetofferResponse {

    @JsonProperty("betoffers")
    private List<Betoffer> betoffers = new ArrayList<Betoffer>();
    @JsonProperty("events")
    private List<Event> events = new ArrayList<Event>();
    @JsonProperty("range")
    private Range range;

    /**
     * 
     * @return
     *     The betoffers
     */
    @JsonProperty("betoffers")
    public List<Betoffer> getBetoffers() {
        return betoffers;
    }

    /**
     * 
     * @param betoffers
     *     The betoffers
     */
    @JsonProperty("betoffers")
    public void setBetoffers(List<Betoffer> betoffers) {
        this.betoffers = betoffers;
    }

    /**
     * 
     * @return
     *     The events
     */
    @JsonProperty("events")
    public List<Event> getEvents() {
        return events;
    }

    /**
     * 
     * @param events
     *     The events
     */
    @JsonProperty("events")
    public void setEvents(List<Event> events) {
        this.events = events;
    }

    /**
     * 
     * @return
     *     The range
     */
    @JsonProperty("range")
    public Range getRange() {
        return range;
    }

    /**
     * 
     * @param range
     *     The range
     */
    @JsonProperty("range")
    public void setRange(Range range) {
        this.range = range;
    }

}

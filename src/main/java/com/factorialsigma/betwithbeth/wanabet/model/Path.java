
package com.factorialsigma.betwithbeth.wanabet.model;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "name",
    "englishName",
    "termKey"
})
public class Path {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("englishName")
    private String englishName;
    @JsonProperty("termKey")
    private String termKey;

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
     *     The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The englishName
     */
    @JsonProperty("englishName")
    public String getEnglishName() {
        return englishName;
    }

    /**
     * 
     * @param englishName
     *     The englishName
     */
    @JsonProperty("englishName")
    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    /**
     * 
     * @return
     *     The termKey
     */
    @JsonProperty("termKey")
    public String getTermKey() {
        return termKey;
    }

    /**
     * 
     * @param termKey
     *     The termKey
     */
    @JsonProperty("termKey")
    public void setTermKey(String termKey) {
        this.termKey = termKey;
    }

}

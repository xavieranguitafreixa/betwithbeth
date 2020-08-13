
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
    "name",
    "homeName",
    "awayName",
    "start",
    "group",
    "type",
    "nonLiveBoCount",
    "liveBetOffers",
    "openForLiveBetting",
    "boUri",
    "groupId",
    "hideStartNo",
    "sport",
    "path",
    "englishName",
    "state",
    "groupSortOrder",
    "displayType",
    "hasPrematchStatistics"
})
public class Event {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("homeName")
    private String homeName;
    @JsonProperty("awayName")
    private String awayName;
    @JsonProperty("start")
    private String start;
    @JsonProperty("group")
    private String group;
    @JsonProperty("type")
    private String type;
    @JsonProperty("nonLiveBoCount")
    private Integer nonLiveBoCount;
    @JsonProperty("liveBetOffers")
    private Boolean liveBetOffers;
    @JsonProperty("openForLiveBetting")
    private Boolean openForLiveBetting;
    @JsonProperty("boUri")
    private String boUri;
    @JsonProperty("groupId")
    private Integer groupId;
    @JsonProperty("hideStartNo")
    private Boolean hideStartNo;
    @JsonProperty("sport")
    private String sport;
    @JsonProperty("path")
    private List<Path> path = new ArrayList<Path>();
    @JsonProperty("englishName")
    private String englishName;
    @JsonProperty("state")
    private String state;
    @JsonProperty("groupSortOrder")
    private Long groupSortOrder;
    @JsonProperty("displayType")
    private String displayType;
    @JsonProperty("hasPrematchStatistics")
    private Boolean hasPrematchStatistics;

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
     *     The homeName
     */
    @JsonProperty("homeName")
    public String getHomeName() {
        return homeName;
    }

    /**
     * 
     * @param homeName
     *     The homeName
     */
    @JsonProperty("homeName")
    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    /**
     * 
     * @return
     *     The awayName
     */
    @JsonProperty("awayName")
    public String getAwayName() {
        return awayName;
    }

    /**
     * 
     * @param awayName
     *     The awayName
     */
    @JsonProperty("awayName")
    public void setAwayName(String awayName) {
        this.awayName = awayName;
    }

    /**
     * 
     * @return
     *     The start
     */
    @JsonProperty("start")
    public String getStart() {
        return start;
    }

    /**
     * 
     * @param start
     *     The start
     */
    @JsonProperty("start")
    public void setStart(String start) {
        this.start = start;
    }

    /**
     * 
     * @return
     *     The group
     */
    @JsonProperty("group")
    public String getGroup() {
        return group;
    }

    /**
     * 
     * @param group
     *     The group
     */
    @JsonProperty("group")
    public void setGroup(String group) {
        this.group = group;
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
     *     The nonLiveBoCount
     */
    @JsonProperty("nonLiveBoCount")
    public Integer getNonLiveBoCount() {
        return nonLiveBoCount;
    }

    /**
     * 
     * @param nonLiveBoCount
     *     The nonLiveBoCount
     */
    @JsonProperty("nonLiveBoCount")
    public void setNonLiveBoCount(Integer nonLiveBoCount) {
        this.nonLiveBoCount = nonLiveBoCount;
    }

    /**
     * 
     * @return
     *     The liveBetOffers
     */
    @JsonProperty("liveBetOffers")
    public Boolean getLiveBetOffers() {
        return liveBetOffers;
    }

    /**
     * 
     * @param liveBetOffers
     *     The liveBetOffers
     */
    @JsonProperty("liveBetOffers")
    public void setLiveBetOffers(Boolean liveBetOffers) {
        this.liveBetOffers = liveBetOffers;
    }

    /**
     * 
     * @return
     *     The openForLiveBetting
     */
    @JsonProperty("openForLiveBetting")
    public Boolean getOpenForLiveBetting() {
        return openForLiveBetting;
    }

    /**
     * 
     * @param openForLiveBetting
     *     The openForLiveBetting
     */
    @JsonProperty("openForLiveBetting")
    public void setOpenForLiveBetting(Boolean openForLiveBetting) {
        this.openForLiveBetting = openForLiveBetting;
    }

    /**
     * 
     * @return
     *     The boUri
     */
    @JsonProperty("boUri")
    public String getBoUri() {
        return boUri;
    }

    /**
     * 
     * @param boUri
     *     The boUri
     */
    @JsonProperty("boUri")
    public void setBoUri(String boUri) {
        this.boUri = boUri;
    }

    /**
     * 
     * @return
     *     The groupId
     */
    @JsonProperty("groupId")
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * 
     * @param groupId
     *     The groupId
     */
    @JsonProperty("groupId")
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * 
     * @return
     *     The hideStartNo
     */
    @JsonProperty("hideStartNo")
    public Boolean getHideStartNo() {
        return hideStartNo;
    }

    /**
     * 
     * @param hideStartNo
     *     The hideStartNo
     */
    @JsonProperty("hideStartNo")
    public void setHideStartNo(Boolean hideStartNo) {
        this.hideStartNo = hideStartNo;
    }

    /**
     * 
     * @return
     *     The sport
     */
    @JsonProperty("sport")
    public String getSport() {
        return sport;
    }

    /**
     * 
     * @param sport
     *     The sport
     */
    @JsonProperty("sport")
    public void setSport(String sport) {
        this.sport = sport;
    }

    /**
     * 
     * @return
     *     The path
     */
    @JsonProperty("path")
    public List<Path> getPath() {
        return path;
    }

    /**
     * 
     * @param path
     *     The path
     */
    @JsonProperty("path")
    public void setPath(List<Path> path) {
        this.path = path;
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
     *     The state
     */
    @JsonProperty("state")
    public String getState() {
        return state;
    }

    /**
     * 
     * @param state
     *     The state
     */
    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 
     * @return
     *     The groupSortOrder
     */
    @JsonProperty("groupSortOrder")
    public Long getGroupSortOrder() {
        return groupSortOrder;
    }

    /**
     * 
     * @param groupSortOrder
     *     The groupSortOrder
     */
    @JsonProperty("groupSortOrder")
    public void setGroupSortOrder(Long groupSortOrder) {
        this.groupSortOrder = groupSortOrder;
    }

    /**
     * 
     * @return
     *     The displayType
     */
    @JsonProperty("displayType")
    public String getDisplayType() {
        return displayType;
    }

    /**
     * 
     * @param displayType
     *     The displayType
     */
    @JsonProperty("displayType")
    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    /**
     * 
     * @return
     *     The hasPrematchStatistics
     */
    @JsonProperty("hasPrematchStatistics")
    public Boolean getHasPrematchStatistics() {
        return hasPrematchStatistics;
    }

    /**
     * 
     * @param hasPrematchStatistics
     *     The hasPrematchStatistics
     */
    @JsonProperty("hasPrematchStatistics")
    public void setHasPrematchStatistics(Boolean hasPrematchStatistics) {
        this.hasPrematchStatistics = hasPrematchStatistics;
    }

}

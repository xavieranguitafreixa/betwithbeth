package com.factorialsigma.betwithbeth.image;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "imageId",
        "visibleUrl",
        "height",
        "width",
        "url",
        "content",
        "originalContextUrl",
        "title",
        "tbWidth",
        "tbHeight",
        "tbUrl"
})
public class ImageResponse {

    @JsonProperty("imageId")
    private String imageId;
    @JsonProperty("visibleUrl")
    private String visibleUrl;
    @JsonProperty("height")
    private Integer height;
    @JsonProperty("width")
    private Integer width;
    @JsonProperty("url")
    private String url;
    @JsonProperty("content")
    private String content;
    @JsonProperty("originalContextUrl")
    private String originalContextUrl;
    @JsonProperty("title")
    private String title;
    @JsonProperty("tbWidth")
    private Integer tbWidth;
    @JsonProperty("tbHeight")
    private Integer tbHeight;
    @JsonProperty("tbUrl")
    private String tbUrl;

    /**
     * @return The imageId
     */
    @JsonProperty("imageId")
    public String getImageId() {
        return imageId;
    }

    /**
     * @param imageId The imageId
     */
    @JsonProperty("imageId")
    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    /**
     * @return The visibleUrl
     */
    @JsonProperty("visibleUrl")
    public String getVisibleUrl() {
        return visibleUrl;
    }

    /**
     * @param visibleUrl The visibleUrl
     */
    @JsonProperty("visibleUrl")
    public void setVisibleUrl(String visibleUrl) {
        this.visibleUrl = visibleUrl;
    }

    /**
     * @return The height
     */
    @JsonProperty("height")
    public Integer getHeight() {
        return height;
    }

    /**
     * @param height The height
     */
    @JsonProperty("height")
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * @return The width
     */
    @JsonProperty("width")
    public Integer getWidth() {
        return width;
    }

    /**
     * @param width The width
     */
    @JsonProperty("width")
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * @return The url
     */
    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url
     */
    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return The content
     */
    @JsonProperty("content")
    public String getContent() {
        return content;
    }

    /**
     * @param content The content
     */
    @JsonProperty("content")
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return The originalContextUrl
     */
    @JsonProperty("originalContextUrl")
    public String getOriginalContextUrl() {
        return originalContextUrl;
    }

    /**
     * @param originalContextUrl The originalContextUrl
     */
    @JsonProperty("originalContextUrl")
    public void setOriginalContextUrl(String originalContextUrl) {
        this.originalContextUrl = originalContextUrl;
    }

    /**
     * @return The title
     */
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The tbWidth
     */
    @JsonProperty("tbWidth")
    public Integer getTbWidth() {
        return tbWidth;
    }

    /**
     * @param tbWidth The tbWidth
     */
    @JsonProperty("tbWidth")
    public void setTbWidth(Integer tbWidth) {
        this.tbWidth = tbWidth;
    }

    /**
     * @return The tbHeight
     */
    @JsonProperty("tbHeight")
    public Integer getTbHeight() {
        return tbHeight;
    }

    /**
     * @param tbHeight The tbHeight
     */
    @JsonProperty("tbHeight")
    public void setTbHeight(Integer tbHeight) {
        this.tbHeight = tbHeight;
    }

    /**
     * @return The tbUrl
     */
    @JsonProperty("tbUrl")
    public String getTbUrl() {
        return tbUrl;
    }

    /**
     * @param tbUrl The tbUrl
     */
    @JsonProperty("tbUrl")
    public void setTbUrl(String tbUrl) {
        this.tbUrl = tbUrl;
    }

}
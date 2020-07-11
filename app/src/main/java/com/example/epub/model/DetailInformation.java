package com.example.epub.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailInformation {
    @SerializedName("tag")
    @Expose
    private List<String> tag = null;

    @SerializedName("is_copyright")
    @Expose
    private Boolean isCopyright;

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("price_unit")
    @Expose
    private String priceUnit;

    @SerializedName("items_left")
    @Expose
    private Integer itemsLeft;

    @SerializedName("averageRating")
    @Expose
    private String averageRating;

    @SerializedName("ratingNumbers")
    @Expose
    private Integer ratingNumbers;

    @SerializedName("view_level")
    @Expose
    private Integer viewLevel;

    @SerializedName("download_level")
    @Expose
    private Integer downloadLevel;

    @SerializedName("anonymous_upload")
    @Expose
    private Boolean anonymousUpload;

    @SerializedName("enable")
    @Expose
    private Boolean enable;

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("author")
    @Expose
    private String author;

    @SerializedName("category")
    @Expose
    private String category;

    @SerializedName("language")
    @Expose
    private String language;

    @SerializedName("cover")
    @Expose
    private String cover;

    @SerializedName("normalized_name")
    @Expose
    private String normalizedName;

    @SerializedName("normalized_author")
    @Expose
    private String normalizedAuthor;

    @SerializedName("slug")
    @Expose
    private String slug;

    @SerializedName("create_by")
    @Expose
    private String createBy;

    @SerializedName("update_by")
    @Expose
    private String updateBy;

    @SerializedName("create_time")
    @Expose
    private String createTime;

    @SerializedName("update_time")
    @Expose
    private String updateTime;

    @SerializedName("__v")
    @Expose
    private Integer v;

    @SerializedName("view_count")
    @Expose
    private Integer viewCount;

    @SerializedName("download_count")
    @Expose
    private Integer downloadCount;

    @SerializedName("gdrive_link")
    @Expose
    private String gdriveLink;

    @SerializedName("original_cover")
    @Expose
    private String originalCover;

    public List<String> getTag() {
        return this.tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public Boolean getIsCopyright() {
        return this.isCopyright;
    }

    public void setIsCopyright(Boolean isCopyright) {
        this.isCopyright = isCopyright;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceUnit() {
        return this.priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public Integer getItemsLeft() {
        return this.itemsLeft;
    }

    public void setItemsLeft(Integer itemsLeft) {
        this.itemsLeft = itemsLeft;
    }

    public String getAverageRating() {
        return this.averageRating;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getRatingNumbers() {
        return this.ratingNumbers;
    }

    public void setRatingNumbers(Integer ratingNumbers) {
        this.ratingNumbers = ratingNumbers;
    }

    public Integer getViewLevel() {
        return this.viewLevel;
    }

    public void setViewLevel(Integer viewLevel) {
        this.viewLevel = viewLevel;
    }

    public Integer getDownloadLevel() {
        return this.downloadLevel;
    }

    public void setDownloadLevel(Integer downloadLevel) {
        this.downloadLevel = downloadLevel;
    }

    public Boolean getAnonymousUpload() {
        return this.anonymousUpload;
    }

    public void setAnonymousUpload(Boolean anonymousUpload) {
        this.anonymousUpload = anonymousUpload;
    }

    public Boolean getEnable() {
        return this.enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getNormalizedName() {
        return this.normalizedName;
    }

    public void setNormalizedName(String normalizedName) {
        this.normalizedName = normalizedName;
    }

    public String getNormalizedAuthor() {
        return this.normalizedAuthor;
    }

    public void setNormalizedAuthor(String normalizedAuthor) {
        this.normalizedAuthor = normalizedAuthor;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getDownloadCount() {
        return this.downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public String getGdriveLink() {
        return this.gdriveLink;
    }

    public void setGdriveLink(String gdriveLink) {
        this.gdriveLink = gdriveLink;
    }

    public String getOriginalCover() {
        return originalCover;
    }

    public void setOriginalCover(String originalCover) {
        this.originalCover = originalCover;
    }
}

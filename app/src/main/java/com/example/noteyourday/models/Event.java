
package com.example.noteyourday.models;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Event {

    @SerializedName("attending_count")
    @Expose
    private Integer attendingCount;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("cost")
    @Expose
    private double cost;
    @SerializedName("cost_max")
    @Expose
    private double costMax;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("event_site_url")
    @Expose
    private String eventSiteUrl;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("interested_count")
    @Expose
    private Integer interestedCount;
    @SerializedName("is_canceled")
    @Expose
    private Boolean isCanceled;
    @SerializedName("is_free")
    @Expose
    private Boolean isFree;
    @SerializedName("is_official")
    @Expose
    private Boolean isOfficial;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("tickets_url")
    @Expose
    private String ticketsUrl;
    @SerializedName("time_end")
    @Expose
    private String timeEnd;
    @SerializedName("time_start")
    @Expose
    private String timeStart;

    @SerializedName("business_id")
    @Expose
    private String businessId;
    private String pushId;
    /**
     * No args constructor for use in serialization
     * 
     */
    public Event() {
    }

    /**
     * 
     * @param eventSiteUrl
     * @param isCanceled
     * @param ticketsUrl

     * @param imageUrl
     * @param costMax
     * @param isOfficial
     * @param attendingCount
     * @param cost
     * @param id
     * @param interestedCount
     * @param category
     * @param timeStart
     * @param timeEnd
     * @param isFree
     * @param description
     * @param name
     * @param longitude
     * @param businessId
     * @param latitude
     */
    public Event(Integer attendingCount, String category, double cost, double costMax, String description, String eventSiteUrl, String id, String imageUrl, Integer interestedCount, Boolean isCanceled, Boolean isFree, Boolean isOfficial, Double latitude, Double longitude, String name, String ticketsUrl, String timeEnd, String timeStart,  String businessId) {
        super();
        this.attendingCount = attendingCount;
        this.category = category;
        this.cost = cost;
        this.costMax = costMax;
        this.description = description;
        this.eventSiteUrl = eventSiteUrl;
        this.imageUrl = imageUrl;
        this.interestedCount = interestedCount;
        this.isCanceled = isCanceled;
        this.isFree = isFree;
        this.isOfficial = isOfficial;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.ticketsUrl = ticketsUrl;
        this.timeEnd = timeEnd;
        this.timeStart = timeStart;

        this.businessId = businessId;
    }

    public Integer getAttendingCount() {
        return attendingCount;
    }

    public void setAttendingCount(Integer attendingCount) {
        this.attendingCount = attendingCount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Object getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Object getCostMax() {
        return costMax;
    }

    public void setCostMax(double costMax) {
        this.costMax = costMax;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEventSiteUrl() {
        return eventSiteUrl;
    }

    public void setEventSiteUrl(String eventSiteUrl) {
        this.eventSiteUrl = eventSiteUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getLargeImageUrl(String imageUrl) {
        String largeImageUrl = imageUrl.substring(0, imageUrl.length() - 6).concat("o.jpg");
        return largeImageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getInterestedCount() {
        return interestedCount;
    }

    public void setInterestedCount(Integer interestedCount) {
        this.interestedCount = interestedCount;
    }

    public Boolean getIsCanceled() {
        return isCanceled;
    }

    public void setIsCanceled(Boolean isCanceled) {
        this.isCanceled = isCanceled;
    }

    public Boolean getIsFree() {
        return isFree;
    }

    public void setIsFree(Boolean isFree) {
        this.isFree = isFree;
    }

    public Boolean getIsOfficial() {
        return isOfficial;
    }

    public void setIsOfficial(Boolean isOfficial) {
        this.isOfficial = isOfficial;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTicketsUrl() {
        return ticketsUrl;
    }

    public void setTicketsUrl(String ticketsUrl) {
        this.ticketsUrl = ticketsUrl;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

}

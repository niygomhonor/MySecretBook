
package com.example.noteyourday.models;





import org.parceler.Parcel;

@Parcel
public class Event {
String index;

    private Integer attendingCount;

    private String category;

    private double cost;

    private double costMax;

    private String description;

    private String eventSiteUrl;

    private String id;

    private String imageUrl;

    private Integer interestedCount;

    private Boolean isCanceled;

    private Boolean isFree;

    private Boolean isOfficial;

    private Double latitude;

    private Double longitude;

    private String name;

    private String ticketsUrl;

    private String timeEnd;

    private String timeStart;


    private String businessId;
    private String pushId;

    public Event() {
    }
    public Event(Integer attendingCount,String category,  String description, String eventSiteUrl, String id, String imageUrl, Integer interestedCount, Boolean isCanceled, Boolean isFree, Boolean isOfficial, Double latitude, Double longitude, String name, String ticketsUrl, String timeEnd, String timeStart,  String businessId) {
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
        this.index="Not there yet";
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

    public void setIndex(String index) {
        this.index=index;
    }
    private  String getIndex(){
        return  index;
    }
}

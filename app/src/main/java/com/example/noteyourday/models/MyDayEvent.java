//package com.example.noteyourday.models;
//
//import java.util.List;
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
//
//public class MyDayEvent {
//
//    @SerializedName("total")
//    @Expose
//    private Integer total;
//    @SerializedName("events")
//    @Expose
//    private List<Event> events = null;
//
//    /**
//     * No args constructor for use in serialization
//     *
//     */
//    public MyDayEvent() {
//    }
//
//    /**
//     *
//     * @param total
//     * @param events
//     */
//    public MyDayEvent(Integer total, List<Event> events) {
//        super();
//        this.total = total;
//        this.events = events;
//    }
//
//    public Integer getTotal() {
//        return total;
//    }
//
//    public void setTotal(Integer total) {
//        this.total = total;
//    }
//
//    public List<Event> getEvents() {
//        return events;
//    }
//
//    public void setEvents(List<Event> events) {
//        this.events = events;
//    }
//
//}
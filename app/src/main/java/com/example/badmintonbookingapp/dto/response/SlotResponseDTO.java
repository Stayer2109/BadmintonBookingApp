package com.example.badmintonbookingapp.dto.response;

import com.google.gson.annotations.SerializedName;

import java.time.LocalTime;

public class SlotResponseDTO {
    @SerializedName("slotId") // Ánh xạ với trường slotId trong JSON
    private Integer id;

    private Double price;
    private String isActive;

    @SerializedName("startTime")
    private String startTime;

    @SerializedName("endTime")
    private String endTime;

    @SerializedName("yardId") // Giữ tên như trong JSON nhưng chuyển đổi sang Integer
    private Integer yardId;

    public SlotResponseDTO() {
    }

    public SlotResponseDTO(Integer id, Double price, String isActive, String startTime, String endTime, Integer yardId) {
        this.id = id;
        this.price = price;
        this.isActive = isActive;
        this.startTime = startTime;
        this.endTime = endTime;
        this.yardId = yardId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getYardId() {
        return yardId;
    }

    public void setYardId(Integer yardId) {
        this.yardId = yardId;
    }

    @Override
    public String toString() {
        return "SlotResponseDTO{" +
                "id=" + id +
                ", price=" + price +
                ", isActive='" + isActive + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", yardId=" + yardId +
                '}';
    }
}
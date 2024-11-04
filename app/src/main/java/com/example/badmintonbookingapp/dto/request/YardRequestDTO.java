package com.example.badmintonbookingapp.dto.request;

import java.time.LocalTime;

public class YardRequestDTO {
    private String name;
    private String address;
    private Integer provinceId;
    private String description;
    private int status;
    private LocalTime openTime;
    private LocalTime closeTime;
    private Integer hostId;

    // Constructor
    public YardRequestDTO(String name, String address, Integer provinceId, String description, int status, LocalTime openTime, LocalTime closeTime, Integer hostId) {
        this.name = name;
        this.address = address;
        this.provinceId = provinceId;
        this.description = description;
        this.status = status;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.hostId = hostId;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalTime openTime) {
        this.openTime = openTime;
    }

    public LocalTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalTime closeTime) {
        this.closeTime = closeTime;
    }

    public Integer getHostId() {
        return hostId;
    }

    public void setHostId(Integer hostId) {
        this.hostId = hostId;
    }

    public boolean isStatus() {
        return status == 1;
    }
}

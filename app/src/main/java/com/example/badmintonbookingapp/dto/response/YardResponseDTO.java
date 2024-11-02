package com.example.badmintonbookingapp.dto.response;

import com.example.badmintonbookingapp.dto.TelephonesDTO;
import com.example.badmintonbookingapp.dto.YardImagesDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class YardResponseDTO {
    private Integer id;
    private String name;
    private String address;
    private Integer provinceId;
    private String description;
    private Boolean status;
    private LocalTime openTime;  // Time-only field
    private LocalTime closeTime; // Time-only field
    private LocalDate createDate; // Date-only field, or change to LocalDateTime if it includes time
    private LocalDate updateDate; // Date-only field, or change to LocalDateTime if it includes time
    private Integer createBy;
    private Integer updateBy;
    private Integer hostId;
    private List<TelephonesDTO> telephones;
    private List<SlotResponseDTO> slots;
    private List<YardImagesDTO> images;

    public YardResponseDTO(Integer id, String name, String address, Integer provinceId, String description, Boolean status, LocalTime openTime, LocalTime closeTime, LocalDate createDate, LocalDate updateDate, Integer createBy, Integer updateBy, Integer hostId, List<TelephonesDTO> telephones, List<SlotResponseDTO> slots, List<YardImagesDTO> images) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.provinceId = provinceId;
        this.description = description;
        this.status = status;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createBy = createBy;
        this.updateBy = updateBy;
        this.hostId = hostId;
        this.telephones = telephones;
        this.slots = slots;
        this.images = images;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
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

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getHostId() {
        return hostId;
    }

    public void setHostId(Integer hostId) {
        this.hostId = hostId;
    }

    public List<TelephonesDTO> getTelephones() {
        return telephones;
    }

    public void setTelephones(List<TelephonesDTO> telephones) {
        this.telephones = telephones;
    }

    public List<SlotResponseDTO> getSlots() {
        return slots;
    }

    public void setSlots(List<SlotResponseDTO> slots) {
        this.slots = slots;
    }

    public List<YardImagesDTO> getImages() {
        return images;
    }

    public void setImages(List<YardImagesDTO> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "YardResponseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", provinceId=" + provinceId +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", openTime=" + openTime +
                ", closeTime=" + closeTime +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", createBy=" + createBy +
                ", updateBy=" + updateBy +
                ", hostId=" + hostId +
                ", telephones=" + telephones +
                ", slots=" + slots +
                ", images=" + images +
                '}';
    }
}

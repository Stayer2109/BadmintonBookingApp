package com.example.badmintonbookingapp.dto.response;

import com.example.badmintonbookingapp.dto.YardImagesDTO;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class YardResponseDTO {

    @SerializedName("yardId")
    private Integer yardId;

    @SerializedName("name")
    private String name;

    @SerializedName("address")
    private String address;

    @SerializedName("provinceId")
    private Integer provinceId;

    @SerializedName("openTime")
    private String openTime;

    @SerializedName("closeTime")
    private String closeTime;

    @SerializedName("description")
    private String description;

    @SerializedName("ownerId")
    private Integer ownerId;

    @SerializedName("isActive")
    private Boolean isActive;

    @SerializedName("yardImages")
    private List<YardImagesDTO> yardImages;

    // Getters and Setters
    public Integer getYardId() {
        return yardId;
    }

    public void setYardId(Integer yardId) {
        this.yardId = yardId;
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

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public List<YardImagesDTO> getYardImages() {
        return yardImages;
    }

    public void setYardImages(List<YardImagesDTO> yardImages) {
        this.yardImages = yardImages;
    }

    @Override
    public String toString() {
        return "YardResponseDTO{" +
                "yardId=" + yardId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", provinceId=" + provinceId +
                ", openTime='" + openTime + '\'' +
                ", closeTime='" + closeTime + '\'' +
                ", description='" + description + '\'' +
                ", ownerId=" + ownerId +
                ", isActive=" + isActive +
                ", yardImages=" + yardImages +
                '}';
    }
}

package com.example.badmintonbookingapp.dto.response;

import com.google.gson.annotations.SerializedName;

public class YardImagesResponseDTO {
    @SerializedName("yardImageId")
    private Integer yardImageId;

    @SerializedName("image")
    private String image;

    @SerializedName("yardId")
    private Integer yardId;

    // Getters and Setters
    public Integer getYardImageId() {
        return yardImageId;
    }

    public void setYardImageId(Integer yardImageId) {
        this.yardImageId = yardImageId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getYardId() {
        return yardId;
    }

    public void setYardId(Integer yardId) {
        this.yardId = yardId;
    }
}

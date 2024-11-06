package com.example.badmintonbookingapp.dto;

import com.google.gson.annotations.SerializedName;

public class YardImagesDTO {

    @SerializedName("yard_id")
    private Integer yard_id;

    @SerializedName("image")
    private String img;

    public Integer getYard_id() {
        return yard_id;
    }

    public void setYard_id(Integer yard_id) {
        this.yard_id = yard_id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

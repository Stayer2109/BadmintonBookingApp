package com.example.badmintonbookingapp.dto.request;

public class YardImageRequestDTO {
    private String image;

    public YardImageRequestDTO(String image){
        this.image = image;
    }

    public String getImage(){
        return image;
    }

    public void setImage(){
        this.image = image;
    }
}

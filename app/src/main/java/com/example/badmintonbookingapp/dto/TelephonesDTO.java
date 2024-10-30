package com.example.badmintonbookingapp.dto;

public class TelephonesDTO {
    private Integer id;
    private Integer yardId;
    private String telephone;

    public TelephonesDTO(Integer id, Integer yardId, String telephone) {
        this.id = id;
        this.yardId = yardId;
        this.telephone = telephone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYardId() {
        return yardId;
    }

    public void setYardId(Integer yardId) {
        this.yardId = yardId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}

package com.example.badmintonbookingapp.dto.request;

public class BookingOrdersRequestDTO {
    private Integer yardId;
    private Integer userId;
    private Integer slotId;

    public Integer getYardId() {
        return yardId;
    }

    public void setYardId(Integer yardId) {
        this.yardId = yardId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSlotId() {
        return slotId;
    }

    public void setSlotId(Integer slotId) {
        this.slotId = slotId;
    }
}

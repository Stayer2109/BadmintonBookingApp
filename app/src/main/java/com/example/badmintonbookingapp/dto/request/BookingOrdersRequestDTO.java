package com.example.badmintonbookingapp.dto.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookingOrdersRequestDTO {

    @SerializedName("userId")
    private Integer userId;

    @SerializedName("bookingDate")
    private String bookingDate;

    @SerializedName("slotIds")
    private List<Integer> slotIds;

    public BookingOrdersRequestDTO(Integer userId, List<Integer> slotIds, String bookingDate) {
        this.userId = userId;
        this.slotIds = slotIds;
        this.bookingDate = bookingDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Integer> getSlotIds() {
        return slotIds;
    }

    public void setSlotIds(List<Integer> slotIds) {
        this.slotIds = slotIds;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    @Override
    public String toString() {
        return "BookingOrdersRequestDTO{" +
                "userId=" + userId +
                ", bookingDate='" + bookingDate + '\'' +
                ", slotIds=" + slotIds +
                '}';
    }
}

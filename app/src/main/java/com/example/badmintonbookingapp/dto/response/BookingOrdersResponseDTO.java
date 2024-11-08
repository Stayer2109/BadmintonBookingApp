package com.example.badmintonbookingapp.dto.response;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class BookingOrdersResponseDTO {
    @SerializedName("bookingOrderId")
    private Integer bookingOrderId;

    @SerializedName("userId")
    private Integer userId;

    @SerializedName("bookingDate")
    private String bookingDate;

    @SerializedName("isActive")
    private Boolean isActive;

    @SerializedName("slots")
    private List<SlotResponseDTO> slots;

    @SerializedName("user")
    private UserResponseDTO user;

    public BookingOrdersResponseDTO(Integer bookingOrderId, Integer userId, String bookingDate, Boolean isActive, List<SlotResponseDTO> slots, UserResponseDTO user) {
        this.bookingOrderId = bookingOrderId;
        this.userId = userId;
        this.bookingDate = bookingDate;
        this.isActive = isActive;
        this.slots = slots;
        this.user = user;
    }

    public Integer getBookingOrderId() {
        return bookingOrderId;
    }

    public void setBookingOrderId(Integer bookingOrderId) {
        this.bookingOrderId = bookingOrderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<SlotResponseDTO> getSlots() {
        return slots;
    }

    public void setSlots(List<SlotResponseDTO> slots) {
        this.slots = slots;
    }

    public UserResponseDTO getUser() {
        return user;
    }

    public void setUser(UserResponseDTO user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "BookingOrdersResponseDTO{" +
                "bookingOrderId=" + bookingOrderId +
                ", userId=" + userId +
                ", bookingDate='" + bookingDate + '\'' +
                ", isActive=" + isActive +
                ", slots=" + slots +
                ", user=" + user +
                '}';
    }
}

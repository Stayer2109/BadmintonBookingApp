package com.example.badmintonbookingapp.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingOrdersResponseDTO {
    private Integer id;
    private LocalDateTime bookingAt;
    private Boolean status;
    private SimpleYardResponseDTO yard;
    private Integer userId;
    private SlotResponseDTO slot;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getBookingAt() {
        return bookingAt;
    }

    public void setBookingAt(LocalDateTime bookingAt) {
        this.bookingAt = bookingAt;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public SimpleYardResponseDTO getYard() {
        return yard;
    }

    public void setYard(SimpleYardResponseDTO yard) {
        this.yard = yard;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public SlotResponseDTO getSlot() {
        return slot;
    }

    public void setSlot(SlotResponseDTO slot) {
        this.slot = slot;
    }
}

package com.example.badmintonbookingapp.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingOrdersResponseDTO {
    private Integer id;
    private LocalDateTime bookingAt;
    private Boolean status;
//    private SimpleYardResponseDTO yard;
    private Integer userId;
    private SlotResponseDTO slot;
    private LocalDate tournamentStart;
    private LocalDate tournamentEnd;

}

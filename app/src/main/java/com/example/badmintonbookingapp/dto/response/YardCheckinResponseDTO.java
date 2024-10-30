package com.example.badmintonbookingapp.dto.response;

import java.time.LocalTime;

public class YardCheckinResponseDTO {
    private Integer id;
    private Boolean status;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private Integer paymentId;
    private Integer userId;
    private Integer checkInById;
}

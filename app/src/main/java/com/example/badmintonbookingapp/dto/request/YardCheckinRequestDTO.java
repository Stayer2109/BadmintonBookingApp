package com.example.badmintonbookingapp.dto.request;

import java.time.LocalTime;

public class YardCheckinRequestDTO {
    private Integer id;
    private Boolean status;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private Integer paymentId;
    private Integer userId;
    private Integer checkInById;
}

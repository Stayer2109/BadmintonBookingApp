package com.example.badmintonbookingapp.dto.request;

import java.time.LocalTime;

public class YardRequestDTO {
    private String name;
    private String address;
    private Integer provinceId;
    private String description;
    private Boolean status;
    private LocalTime openTime;
    private LocalTime closeTime;
    private Integer hostId;
}

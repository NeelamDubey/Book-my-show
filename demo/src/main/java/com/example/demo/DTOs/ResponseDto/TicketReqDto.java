package com.example.demo.DTOs.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketReqDto {

    private String showTime;

    private Date date;

    private String movieName;

    private String theaterName;

    private String bookedSeat;

    public List<String> getRequestedSeats() {
    }
}
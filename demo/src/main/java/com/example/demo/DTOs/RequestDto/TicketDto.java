package com.example.demo.DTOs.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {

    private int showId;

    private int userId;

    private List<String> requestedSeats;
}
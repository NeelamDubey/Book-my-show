package com.example.demo.Transformers;

import com.example.demo.DTOs.ResponseDto.TicketReqDto;
import com.example.demo.Models.Show;
import com.example.demo.Models.Ticket;

public class TicketTransformer {
    public static TicketReqDto getTicketResDto(Show show, Ticket ticket){
        TicketReqDto ticketResDto=new TicketReqDto();
        ticketResDto.setDate(show.getShowDate());
        ticketResDto.setMovieName(show.getMovie().getName());
        ticketResDto.setTheaterName(show.getTheater().getName());
        ticketResDto.setBookedSeat(ticket.getBookedSeats());
        ticketResDto.setShowTime(show.getShowTime());
        return ticketResDto;
    }
}

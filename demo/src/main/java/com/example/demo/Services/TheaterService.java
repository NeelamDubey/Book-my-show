package com.example.demo.Services;

import com.example.demo.DTOs.RequestDto.TheaterDto;
import com.example.demo.DTOs.RequestDto.TheaterseatDto;
import com.example.demo.Enums.SeatType;
import com.example.demo.Exceptions.TheaterNotFound;
import com.example.demo.Models.Theater;
import com.example.demo.Models.Theaterseat;
import com.example.demo.Repositories.TheaterRepository;
import com.example.demo.Transformers.TheaterTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;import com.example.demo.DTOs.RequestDto.TheaterseatDto;

public class TheaterService {
    @Autowired
    private TheaterRepository theaterRepository;
    public String addTheater(TheaterDto theaterDto) {

        Theater theater= TheaterTransformer.TheaterDtoToEntity(theaterDto);
        theaterRepository.save(theater);

        return "Theater added Sucessfully";
    }

    public String addTheaterSeats(TheaterseatDto theaterSeatDto) throws TheaterNotFound {
        Theater optionalTheater= theaterRepository.findByLocation(theaterSeatDto.getLocation());
        if (optionalTheater==null){
            throw new TheaterNotFound("Theare  is not any Theater on  location");
        }

        Integer col=theaterSeatDto.getNoOfSeatsIn1Row();
        Integer classicSeats= theaterSeatDto.getNofOfClassicSeats();
        Integer primiumSeats= theaterSeatDto.getNoOfPremiumSeats();

        List<Theaterseat> theaterseatList=optionalTheater.getTheaterseatList();

        int seatCount=1;
        char ch='A';

        for (int i=1; i<=classicSeats; i++){

            String seatNo=seatCount+"";
            seatNo+=ch;
            ch++;
            if(ch-'A'==col){
                ch='A';
                seatCount++;
            }
            Theaterseat seat=Theaterseat.builder().seatNo(seatNo).seatType(SeatType.CLASSIC).theater(optionalTheater).build();
            theaterseatList.add(seat);
        }

        for (int i=1; i<=primiumSeats; i++){
            String seatNo=seatCount+"";
            seatNo+=ch;
            ch++;
            if(ch-'A'==col){
                ch='A';
                seatCount++;
            }

            Theaterseat seat=Theaterseat.builder().seatNo(seatNo).seatType(SeatType.PREMIUM).theater(optionalTheater).build();
            theaterseatList.add(seat);
        }

        theaterRepository.save(optionalTheater);
        return "Theater Seat Successfully Added to the theater";
    }
}

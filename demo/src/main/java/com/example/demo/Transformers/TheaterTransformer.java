package com.example.demo.Transformers;

import com.example.demo.DTOs.RequestDto.TheaterDto;
import com.example.demo.Models.Theater;

public class TheaterTransformer {
    public static Theater TheaterDtoToEntity(TheaterDto theaterDto) {
            Theater theater=Theater.builder().name(theaterDto.getName())
                    .location(theaterDto.getLocation()).build();
            return theater;
    }
}

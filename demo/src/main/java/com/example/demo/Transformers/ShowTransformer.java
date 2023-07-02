package com.example.demo.Transformers;

import com.example.demo.DTOs.RequestDto.ShowDto;
import com.example.demo.Models.Show;

public class ShowTransformer {
    public static Show showDtoToEntity(ShowDto showDto){

        Show show=Show.builder().showDate(showDto.getDate()).showTime(showDto.getTime()).build();
        return show;
    }
}

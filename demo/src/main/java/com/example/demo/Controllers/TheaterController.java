package com.example.demo.Controllers;
import com.example.demo.DTOs.RequestDto.TheaterDto;
import com.example.demo.DTOs.RequestDto.TheaterseatDto;
import com.example.demo.Services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController

public class TheaterController {
    @Autowired
    private TheaterService theaterService;

    @PostMapping("/add")
    public ResponseEntity<String> addTheater(@RequestBody TheaterDto theaterDto){
        try {
            String result=theaterService.addTheater(theaterDto);
            return new ResponseEntity<>(result,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addTheaterSeats")
    public ResponseEntity<String> addTheaterSeats(@RequestBody TheaterseatDto theaterSeatDto){
        try {
            String result=theaterService.addTheaterSeats(theaterSeatDto);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}

package com.example.demo.Controllers;

import com.example.demo.DTOs.RequestDto.ShowDto;
import com.example.demo.DTOs.RequestDto.ShowseatDto;
import com.example.demo.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ShowController {
    @Autowired
    private ShowService showService;

    @PostMapping("/add")
    public ResponseEntity<String> addShow(@RequestBody ShowDto showDto){
        try{
            String res=showService.addShow(showDto);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/associate-seats")
    public ResponseEntity<String> associateSeats(@RequestBody ShowseatDto showSeatDto){
        try {
            String res=showService.associateSeats(showSeatDto);
            return new ResponseEntity<>(res,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}

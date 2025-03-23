package com.learndesign.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seat")
public class PessimisticController {

    @GetMapping("/getAllBookedSeats")
    public String getBookingDetails(){

        return null;
    }

    @PostMapping("/bookSeat")
    public String saveSeat(@RequestParam("id") int id){

        return null;
    }


}

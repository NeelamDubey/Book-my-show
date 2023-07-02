package com.example.demo.Services;



import com.example.demo.DTOs.ResponseDto.TicketReqDto;
import com.example.demo.Exceptions.ShowNotFound;
import com.example.demo.Exceptions.UserNotFound;
import com.example.demo.Models.Show;
import com.example.demo.Models.Ticket;
import com.example.demo.Models.User;
import com.example.demo.Repositories.ShowRepository;
import com.example.demo.Repositories.TicketRepository;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private JavaMailSender emailSender;

    public TicketReqDto bookTicket(TicketReqDto ticketRequestDto)throws UserNotFound, ShowNotFound {

        //User validation
        int userId = ticketRequestDto.getUserId();
        Optional<User> userOptional = userRepository.findById(userId);
        if(!userOptional.isPresent()){
            throw new UserNotFound("User Id is incorrect");
        }

        //Show Validation
        int showId = ticketRequestDto.getShowId();
        Optional<Show> showOptional = showRepository.findById(showId);
        if(!showOptional.isPresent()){
            throw new ShowNotFound("Show is not found");
        }
        Show show = showOptional.get();

        //Validation for the requested Seats are available or not
        boolean isValid = validateShowAvailability(show,ticketRequestDto.getRequestedSeats());

        if(isValid==false){
            throw new Exception("Requested Seats entered are not available");
        }

        Ticket ticket = new Ticket();
        //calculate the total price

        int totalPrice = calculateTotalPrice(show,ticketRequestDto.getRequestedSeats());

        ticket.setTotalTicketsPrice(totalPrice);

        //Convert the list of booked seats into string from list
        String bookedSeats = convertListToString(ticketRequestDto.getRequestedSeats());

        ticket.setBookedSeats(bookedSeats);
        //Do bidirectional mapping

        User user = userOptional.get();

        ticket.setUser(user);
        ticket.setShow(show);

        ticket = ticketRepository.save(ticket);

        user.getTicketList().add(ticket);
        //Saving the relevant repositories

        userRepository.save(user);

        show.getTicketList().add(ticket);

        showRepository.save(show);


        //We can send a mail to the person
        SimpleMailMessage simpleMessageMail = new SimpleMailMessage();

        String body = "Hi "+user.getName()+" ! \n"+
                "You have successfully booked a ticket. Please find the following details"+
                "booked seat No's"  + bookedSeats
                +"movie Name" + show.getMovie().getMovieName()
                +"show Date is "+show.getDate()+
                "And show time is "+show.getTime()+
                "Enjoy the Show !!!";

        simpleMessageMail.setSubject("Ticket Confirmation Mail");
        simpleMessageMail.setFrom("springacciojob@gmail.com");
        simpleMessageMail.setText(body);
        simpleMessageMail.setTo(user.getEmail());

        emailSender.send(simpleMessageMail);


        return createTicketReponseDto(show,ticket);
    }

    private boolean validateShowAvailability(Show show, List<String> requestedSeats){

        List<ShowSeat> showSeatList = show.getShowSeatList();

        for(ShowSeat showSeat : showSeatList){
            String seatNo = showSeat.getSeatNo();
            if(requestedSeats.contains(seatNo)){

                if(showSeat.isAvailable()==false)
                    return false;
            }
        }
        return true;

    }

    private int calculateTotalPrice(Show show, List<String> requestedSeats){

        int totalPrice = 0;

        List<ShowSeat> showSeatList = show.getShowSeatList();

        for(ShowSeat showSeat : showSeatList){

            if(requestedSeats.contains(showSeat.getSeatNo())){
                totalPrice = totalPrice + showSeat.getPrice();
                showSeat.setAvailable(false);
            }
        }

        return totalPrice;
    }

    String convertListToString(List<String> seats){

        String result = "";
        for(String seatNo : seats){
            result = result + seatNo+", ";
        }
        return result;
    }

    private TicketDto createTicketReponseDto(Show show,Ticket ticket){

        TicketDto ticketResponseDto = TicketDto.builder()
                .bookedSeats(ticket.getBookedSeats())
                .location(show.getTheater().getLocation())
                .theaterName(show.getTheater().getName())
                .movieName(show.getMovie().getMovieName())
                .showDate(show.getDate())
                .showTime(show.getTime())
                .totalPrice(ticket.getTotalTicketsPrice())
                .build();

        return ticketResponseDto;
    }
}
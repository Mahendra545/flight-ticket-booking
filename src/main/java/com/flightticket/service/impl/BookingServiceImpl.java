package com.flightticket.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightticket.config.JwtTokenUtil;
import com.flightticket.dto.BookingDetails;
import com.flightticket.dto.BookingHistory;
import com.flightticket.dto.FlightDetails;
import com.flightticket.dto.PassengerDetails;
import com.flightticket.entity.Booking;
import com.flightticket.entity.Flight;
import com.flightticket.entity.Passenger;
import com.flightticket.entity.User;
import com.flightticket.exception.InvalidUserCredentials;
import com.flightticket.exception.NotFoundBookingHistory;
import com.flightticket.repository.BookingRepository;
import com.flightticket.repository.PassengerRepository;
import com.flightticket.repository.UserRepository;
import com.flightticket.service.BookingService;


@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	JwtTokenUtil jwtTokenUtil;
	@Autowired
	UserRepository usersRepository;
	@Autowired
	PassengerRepository passengerRepository;
	@Autowired
	BookingRepository bookingRepository;
	@Override
	public BookingHistory getBookingHistory(String userName) {
		User user=validateUser(userName);
		List<Booking> bookingList = bookingRepository.findByUserId(user.getUserId());
		BookingHistory bookingHistory = new BookingHistory();
		List<BookingDetails> bookingDetailsList = new ArrayList<>();
		bookingHistory.setUserName(user.getUserName());
		if(!bookingList.isEmpty()) {
			bookingList.forEach(booking->{
				BookingDetails bookingDetails = new BookingDetails();
				FlightDetails flightDetails = new FlightDetails();
				bookingDetails.setBookingId(booking.getBookingId());
				Flight flight=booking.getFlightId();
				flightDetails.setDate(flight.getDate());
				flightDetails.setDestination(flight.getDestination());
				flightDetails.setFlightName(flight.getFlightName());
				flightDetails.setSource(flight.getSource());			
				flightDetails.setTime(flight.getTime());		
				bookingDetails.setFlightDetails(flightDetails);				
				List<Passenger> PassengerList = passengerRepository.findBybookingId(booking.getBookingId());
				List<PassengerDetails> passengerDetailsList = new ArrayList<>();
				PassengerList.forEach(passenger->{	
				PassengerDetails passengerDetails = new PassengerDetails();
				passengerDetails.setAge(passenger.getAge());				
				passengerDetails.setGender(passenger.getGender());			
				passengerDetails.setMealPreference(passenger.getMealPreference());			
				passengerDetails.setName(passenger.getName());			
				passengerDetails.setSeatPreference(passenger.getSeatPreference());			
				passengerDetailsList.add(passengerDetails);
				});
				bookingDetails.setPassengerDetails(passengerDetailsList);
				bookingDetailsList.add(bookingDetails);
			});
			bookingHistory.setBookingDetails(bookingDetailsList);
			return bookingHistory;
		}
		else {
			throw new NotFoundBookingHistory("There are no booking details are available");
		}
		
	}

	
	@Override
	public User validateUser(String userName) throws InvalidUserCredentials {
		Optional<User> user = usersRepository.findByUserName(userName);
		if(user.isPresent()) {
		return user.get();
		}else {
			throw new InvalidUserCredentials("Not authorized to perform operation");
		}
	}
	
}

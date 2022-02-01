package com.flightticket.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightticket.config.JwtTokenUtil;
import com.flightticket.dto.BookingDetails;
import com.flightticket.dto.BookingDto;
import com.flightticket.dto.BookingHistory;
import com.flightticket.dto.FlightDetails;
import com.flightticket.dto.PassengerDetails;
import com.flightticket.dto.PassengerDto;
import com.flightticket.entity.Booking;
import com.flightticket.entity.Flight;
import com.flightticket.entity.Passenger;
import com.flightticket.entity.User;
import com.flightticket.exception.InvalidUserCredentials;
import com.flightticket.exception.NotFoundBookingHistory;
import com.flightticket.repository.BookingRepository;
import com.flightticket.repository.FlightRepository;
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
	@Autowired
	FlightRepository flightRepository;
	
	 private final Logger logger = LogManager.getLogger(BookingServiceImpl.class);
	
	@Override
	public BookingHistory getBookingHistory(String userName) {
		logger.info("Based on the username you are getting the user details");
		User user = validateUser(userName);
		BookingHistory bookingHistory = new BookingHistory();
		bookingHistory.setUserName(user.getUserName());
		logger.info("Getting the user ticket booking history");
		List<Booking> bookinglist = bookingRepository.findByUserId(user.getUserId());
		List<BookingDetails> bookingDetailsList =new ArrayList<>();
		if(!bookinglist.isEmpty()) {
		bookinglist.forEach(booking->{
			BookingDetails bookingDetails =new BookingDetails();
			FlightDetails flightDetails = new FlightDetails();
			bookingDetails.setBookingId(booking.getBookingId());
			Flight flight=booking.getFlightId();
			BeanUtils.copyProperties(flight, flightDetails);
			bookingDetails.setFlightDetails(flightDetails);	
			List<Passenger> PassengerList = booking.getPassengers();
			List<PassengerDetails> passengerDetailsList = new ArrayList<>();
			PassengerList.forEach(passenger->{
				PassengerDetails passengerDetails = new PassengerDetails();
				BeanUtils.copyProperties(passenger, passengerDetails);
				passengerDetailsList.add(passengerDetails);
			});
			bookingDetails.setPassengerDetails(passengerDetailsList);
			bookingDetailsList.add(bookingDetails);
		});
		bookingHistory.setBookingDetails(bookingDetailsList);
		logger.info("Sending the user ticket booking history");
		return bookingHistory;
		}else {
			logger.error("There are no booking details are available");
			throw new NotFoundBookingHistory("There are no booking details are available");
		}
	}

	
	@Override
	public Booking bookTicket(BookingDto bookingDto) {
		
		Booking booking = convertDtoToEntity(bookingDto);
		logger.info("Saving the booking details");
		Booking bookingSaved = bookingRepository.save(booking);
		List<Passenger> passengerListSaved = booking.getPassengers().stream()
				.map(passengerEntity -> savePassengerEntity(bookingSaved, passengerEntity))
				.collect(Collectors.toList());

		bookingSaved.setPassengers(passengerListSaved);
		
		
		logger.info("Sending the booking details");

		return bookingSaved;

	}

	private Passenger savePassengerEntity(Booking bookingSaved, Passenger passenger) {
		passenger.setBooking(bookingSaved);

		return passengerRepository.save(passenger);
	}

	private Booking convertDtoToEntity(BookingDto bookingDto) {

		Booking booking = new Booking();
		Optional<Flight> flightOptional = flightRepository.findById(bookingDto.getFlightId());
		
		BeanUtils.copyProperties(bookingDto, booking);

		List<Passenger> passengerList = bookingDto.getPassengerDto().stream()
				.map(passengerDto -> getPassengerEntity(passengerDto)).collect(Collectors.toList());

		booking.setPassengers(passengerList);
		booking.setFlightId(flightOptional.get());

		return booking;
	}

	private Passenger getPassengerEntity(PassengerDto passengerDto) {

		Passenger passenger = new Passenger();
		BeanUtils.copyProperties(passengerDto, passenger);

		return passenger;
	}
	
	
	
	
	
	
	
	@Override
	public User validateUser(String userName) throws InvalidUserCredentials {
		logger.info("Checking the user details");
		Optional<User> user = usersRepository.findByUserName(userName);
		if(user.isPresent()) {
		return user.get();
		}else {
			logger.error("Not authorized to perform operation");
			throw new InvalidUserCredentials("Not authorized to perform operation");
		}
	}
	
}

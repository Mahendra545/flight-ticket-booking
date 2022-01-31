package com.flightticket.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.flightticket.dto.BookingDetails;
import com.flightticket.dto.BookingHistory;
import com.flightticket.dto.FlightDetails;
import com.flightticket.dto.PassengerDetails;
import com.flightticket.entity.Booking;
import com.flightticket.entity.Flight;
import com.flightticket.entity.Passenger;
import com.flightticket.entity.User;
import com.flightticket.exception.InvalidUserCredentials;
import com.flightticket.repository.BookingRepository;
import com.flightticket.repository.PassengerRepository;
import com.flightticket.repository.UserRepository;

@ExtendWith(SpringExtension.class)
class BookingServiceImplTest {

	@InjectMocks
	BookingServiceImpl bookingServiceImpl;
	@Mock
	UserRepository usersRepository;
	@Mock
	PassengerRepository passengerRepository;
	@Mock
	BookingRepository bookingRepository;
	@Test
	void getBookingHistoryException() {
		
		Flight fligh = new Flight();
		fligh.setFlightId(1);
		fligh.setDate(new Date(2022,01,31));
		fligh.setDestination("Bangalore");
		fligh.setFlightName("Air545");
		fligh.setSource("Chennai");
		fligh.setTime("8:30");
		Booking booking = new Booking();
		booking.setBookingId(1);
		booking.setFlightId(fligh);
		booking.setUserId(1);
		Passenger passenger = new Passenger();
		passenger.setAge(30);
		passenger.setBookingId(1);
		passenger.setGender("male");
		passenger.setMealPreference("non veg");
		passenger.setName("mahendra");
		passenger.setPassengerId(1);
		passenger.setSeatPreference("G5");
		List<Booking> bookingList = new ArrayList<>();
		bookingList.add(booking);
		List<Passenger> passengerList = new ArrayList<>();
		passengerList.add(passenger);
		User user = new User();
		user.setUserName("user1");
		user.setUserId(1);
		user.setPassword("xyz");
		when(usersRepository.findByUserName("user1")).thenReturn(Optional.of(user));
		when(bookingRepository.findByUserId(1)).thenReturn(bookingList);
		when(passengerRepository.findBybookingId(booking.getBookingId())).thenReturn(passengerList);	
		assertThrows(InvalidUserCredentials.class, () -> {

			bookingServiceImpl.getBookingHistory("user");
		});
		
	}
	@Test
	void getBookingHistory() {
		
		Flight fligh = new Flight();
		fligh.setFlightId(1);
		fligh.setDate(new Date(2022,01,31));
		fligh.setDestination("Bangalore");
		fligh.setFlightName("Air545");
		fligh.setSource("Chennai");
		fligh.setTime("8:30");
		Booking booking = new Booking();
		booking.setBookingId(1);
		booking.setFlightId(fligh);
		booking.setUserId(1);
		Passenger passenger = new Passenger();
		passenger.setAge(30);
		passenger.setBookingId(1);
		passenger.setGender("male");
		passenger.setMealPreference("non veg");
		passenger.setName("mahendra");
		passenger.setPassengerId(1);
		passenger.setSeatPreference("G5");
		List<Booking> bookingList = new ArrayList<>();
		bookingList.add(booking);
		List<Passenger> passengerList = new ArrayList<>();
		passengerList.add(passenger);
		User user = new User();
		user.setUserName("user1");
		user.setUserId(1);
		user.setPassword("xyz");
		BookingDetails bookingDetails = new BookingDetails();
		FlightDetails flightDetails = new FlightDetails();
		PassengerDetails passengerDetails = new PassengerDetails();
		flightDetails.setDate(fligh.getDate());
		flightDetails.setDestination(fligh.getDestination());
		flightDetails.setFlightName(fligh.getFlightName());
		flightDetails.setSource(fligh.getSource());
		flightDetails.setTime(fligh.getTime());
		bookingDetails.setFlightDetails(flightDetails);	
		List<PassengerDetails> passengerDetailsList = new ArrayList<>();
		passengerDetails.setAge(passenger.getAge());
		passengerDetails.setGender(passenger.getGender());
		passengerDetails.setMealPreference(passenger.getMealPreference());
		passengerDetails.setName(passenger.getName());
		passengerDetails.setSeatPreference(passenger.getSeatPreference());
		passengerDetailsList.add(passengerDetails);
		bookingDetails.setPassengerDetails(passengerDetailsList);
		List<BookingDetails> bookingDetailsList = new ArrayList<>();
		bookingDetailsList.add(bookingDetails);
		BookingHistory bookingHistory = new BookingHistory();
		bookingHistory.setUserName(user.getUserName());
		bookingHistory.setBookingDetails(bookingDetailsList);
		//BookingServiceImpl mockBookingService = mock(BookingServiceImpl.class);
		when(usersRepository.findByUserName("user1")).thenReturn(Optional.of(user));
		when(bookingRepository.findByUserId(1)).thenReturn(bookingList);
		when(passengerRepository.findBybookingId(booking.getBookingId())).thenReturn(passengerList);
		 BookingHistory bookingHistorytest  =   bookingServiceImpl.getBookingHistory("user1");
		 assertEquals("user1", bookingHistorytest.getUserName()); 
	
		
	}
	@Test
	void getBookingHistoryFail() {
		
		Flight fligh = new Flight();
		fligh.setFlightId(1);
		fligh.setDate(new Date(2022,01,31));
		fligh.setDestination("Bangalore");
		fligh.setFlightName("Air545");
		fligh.setSource("Chennai");
		fligh.setTime("8:30");
		Booking booking = new Booking();
		booking.setBookingId(1);
		booking.setFlightId(fligh);
		booking.setUserId(1);
		Passenger passenger = new Passenger();
		passenger.setAge(30);
		passenger.setBookingId(1);
		passenger.setGender("male");
		passenger.setMealPreference("non veg");
		passenger.setName("mahendra");
		passenger.setPassengerId(1);
		passenger.setSeatPreference("G5");
		List<Booking> bookingList = new ArrayList<>();
		bookingList.add(booking);
		List<Passenger> passengerList = new ArrayList<>();
		passengerList.add(passenger);
		User user = new User();
		user.setUserName("user1");
		user.setUserId(1);
		user.setPassword("xyz");
		BookingDetails bookingDetails = new BookingDetails();
		FlightDetails flightDetails = new FlightDetails();
		PassengerDetails passengerDetails = new PassengerDetails();
		flightDetails.setDate(fligh.getDate());
		flightDetails.setDestination(fligh.getDestination());
		flightDetails.setFlightName(fligh.getFlightName());
		flightDetails.setSource(fligh.getSource());
		flightDetails.setTime(fligh.getTime());
		bookingDetails.setFlightDetails(flightDetails);	
		List<PassengerDetails> passengerDetailsList = new ArrayList<>();
		passengerDetails.setAge(passenger.getAge());
		passengerDetails.setGender(passenger.getGender());
		passengerDetails.setMealPreference(passenger.getMealPreference());
		passengerDetails.setName(passenger.getName());
		passengerDetails.setSeatPreference(passenger.getSeatPreference());
		passengerDetailsList.add(passengerDetails);
		bookingDetails.setPassengerDetails(passengerDetailsList);
		List<BookingDetails> bookingDetailsList = new ArrayList<>();
		bookingDetailsList.add(bookingDetails);
		BookingHistory bookingHistory = new BookingHistory();
		bookingHistory.setUserName(user.getUserName());
		bookingHistory.setBookingDetails(bookingDetailsList);
		//BookingServiceImpl mockBookingService = mock(BookingServiceImpl.class);
		when(usersRepository.findByUserName("user1")).thenReturn(Optional.of(user));
		when(bookingRepository.findByUserId(1)).thenReturn(bookingList);
		when(passengerRepository.findBybookingId(booking.getBookingId())).thenReturn(passengerList);
		 BookingHistory bookingHistorytest  =   bookingServiceImpl.getBookingHistory("user1");
		 assertNotEquals("user", bookingHistorytest.getUserName()); 
	
		
	}
}

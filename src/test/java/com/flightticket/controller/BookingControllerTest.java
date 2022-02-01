package com.flightticket.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.flightticket.dto.BookingDetails;
import com.flightticket.dto.BookingDto;
import com.flightticket.dto.BookingHistory;
import com.flightticket.dto.FlightDetails;
import com.flightticket.dto.PassengerDetails;
import com.flightticket.entity.Booking;
import com.flightticket.entity.Flight;
import com.flightticket.entity.Passenger;
import com.flightticket.entity.User;



import com.flightticket.service.BookingService;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookingControllerTest {

	
	


	@Mock
	BookingService bookingService;

	@InjectMocks
	BookingController bookingControler;

	@Test
	void getBookingHistory() throws Exception {
		
		User user = new User();
		user.setUserName("user1");
		user.setUserId(1);
		user.setPassword("xyz");	
		Flight fligh = new Flight();
		fligh.setFlightId(1);
		fligh.setDate(new Date(2022,02,01));
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
		passenger.setBooking(booking);
		passenger.setGender("male");
		passenger.setMealPreference("non veg");
		passenger.setName("mahendra");
		passenger.setPassengerId(1);
		passenger.setSeatPreference("G5");
		List<Passenger> passengerList = new ArrayList<>();
		passengerList.add(passenger);
		booking.setPassengers(passengerList);
		List<Booking> bookingList = new ArrayList<>();
		bookingList.add(booking);
		Booking bookingDetails = new Booking();
		FlightDetails flightDetails = new FlightDetails();
		PassengerDetails passengerDetails = new PassengerDetails();
		BeanUtils.copyProperties(fligh,flightDetails);
		List<PassengerDetails> passengerDetailsList = new ArrayList<>();
		BeanUtils.copyProperties(passenger,passengerDetails);
		passengerDetailsList.add(passengerDetails);
		bookingDetails.setPassengers(passengerList);
		List<Booking> bookingDetailsList = new ArrayList<>();
		bookingDetailsList.add(bookingDetails);
		BookingDetails bd = new BookingDetails();
		bd.setBookingId(bookingDetails.getBookingId());
		bd.setPassengerDetails(passengerDetailsList);
		bd.setFlightDetails(flightDetails);
		List<BookingDetails> bDList = new ArrayList<>();
		bDList.add(bd);
		BookingHistory bookingHistory = new BookingHistory();
		bookingHistory.setUserName(user.getUserName());
		bookingHistory.setBookingDetails(bDList);
		when(bookingService.getBookingHistory("user1")).thenReturn(bookingHistory);
		
		assertEquals("user1", bookingHistory.getUserName());
		
		
	}

	@Test
	void getBookingHistoryFail()  {
		
		User user = new User();
		user.setUserName("user1");
		user.setUserId(1);
		user.setPassword("xyz");	
		Flight fligh = new Flight();
		fligh.setFlightId(1);
		fligh.setDate(new Date(2022,02,01));
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
		passenger.setBooking(booking);
		passenger.setGender("male");
		passenger.setMealPreference("non veg");
		passenger.setName("mahendra");
		passenger.setPassengerId(1);
		passenger.setSeatPreference("G5");
		List<Passenger> passengerList = new ArrayList<>();
		passengerList.add(passenger);
		booking.setPassengers(passengerList);
		List<Booking> bookingList = new ArrayList<>();
		bookingList.add(booking);
		Booking bookingDetails = new Booking();
		FlightDetails flightDetails = new FlightDetails();
		PassengerDetails passengerDetails = new PassengerDetails();
		BeanUtils.copyProperties(fligh,flightDetails);
		List<PassengerDetails> passengerDetailsList = new ArrayList<>();
		BeanUtils.copyProperties(passenger,passengerDetails);
		passengerDetailsList.add(passengerDetails);
		bookingDetails.setPassengers(passengerList);
		List<Booking> bookingDetailsList = new ArrayList<>();
		bookingDetailsList.add(bookingDetails);
		BookingDetails bd = new BookingDetails();
		bd.setBookingId(bookingDetails.getBookingId());
		bd.setPassengerDetails(passengerDetailsList);
		bd.setFlightDetails(flightDetails);
		List<BookingDetails> bDList = new ArrayList<>();
		bDList.add(bd);
		BookingHistory bookingHistory = new BookingHistory();
		bookingHistory.setUserName(user.getUserName());
		bookingHistory.setBookingDetails(bDList);
		when(bookingService.getBookingHistory("user1")).thenReturn(bookingHistory);
		
		assertNotNull("user", bookingHistory.getUserName());
		
		
	}






@Test
@DisplayName("book Ticket")
@Order(1)
	void testAvailableBalancePostive() {

		Booking bookingDetailsSaved = new Booking();
		bookingDetailsSaved.setBookingId(1);

		BookingDto bookingDetailsDto = new BookingDto();
		bookingDetailsDto.setUserId(1);
		
		Mockito.when(bookingService.bookTicket(bookingDetailsDto)).thenReturn(bookingDetailsSaved);

		ResponseEntity<?> result = bookingControler.bookTicket(bookingDetailsDto);

		Booking bookingDetailsResponse = (Booking) result.getBody();
		assertNotNull(bookingDetailsResponse);
		assertEquals(1, bookingDetailsResponse.getBookingId());



		}
}

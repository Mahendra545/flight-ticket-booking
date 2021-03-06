package com.flightticket.service;

import com.flightticket.dto.BookingDto;
import com.flightticket.dto.BookingHistory;
import com.flightticket.entity.Booking;
import com.flightticket.entity.User;
import com.flightticket.exception.InvalidUserCredentials;

public interface BookingService {

	BookingHistory getBookingHistory(String userName);

	User validateUser(String userName) throws InvalidUserCredentials;
	
	Booking bookTicket(BookingDto booking);

}

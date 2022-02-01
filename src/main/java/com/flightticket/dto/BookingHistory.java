package com.flightticket.dto;

import java.util.List;

public class BookingHistory {
	
	private String userName;
	private List<BookingDetails> bookingDetails;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<BookingDetails> getBookingDetails() {
		return bookingDetails;
	}
	public void setBookingDetails(List<BookingDetails> bookingDetails) {
		this.bookingDetails = bookingDetails;
	}
	
	

}

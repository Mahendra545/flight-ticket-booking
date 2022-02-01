package com.flightticket.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BookingDto {
	
	@NotNull(message = "flightId not  empty")
	private Integer flightId;
	@NotNull(message = "userId not  empty")
	private Integer userId;
	@NotEmpty(message = "At least need to add one passenger details")
	private List<PassengerDto> passengerDto;
	
	public int getFlightId() {
		return flightId;
	}
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public List<PassengerDto> getPassengerDto() {
		return passengerDto;
	}
	public void setPassengerDto(List<PassengerDto> passengerDto) {
		this.passengerDto = passengerDto;
	}
	
	
	
	

}

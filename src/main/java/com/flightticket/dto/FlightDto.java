package com.flightticket.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

public class FlightDto {

	@NotEmpty
	private String source;
	
	@NotEmpty
	private String destination;
	private LocalDate date;

	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate localDate) {
		this.date = localDate;
	}
	
	
}

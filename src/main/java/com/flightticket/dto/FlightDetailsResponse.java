package com.flightticket.dto;


import com.flightticket.entity.Flight;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightDetailsResponse {
	
	private String message;
	
	private Flight flightDetails;
	
}

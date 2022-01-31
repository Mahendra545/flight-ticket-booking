package com.flightticket.service;

import java.util.List;

import com.flightticket.dto.FlightDto;
import com.flightticket.dto.FlightResponse;

public interface FlightService {

	List<FlightResponse> getAllAvailableFlightDetails( FlightDto flightDto);

}

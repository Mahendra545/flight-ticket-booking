package com.flightticket.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flightticket.dto.FlightDto;
import com.flightticket.dto.FlightResponse;
import com.flightticket.service.FlightService;

@RestController
public class FlightController {
	
	@Autowired
	FlightService flightService;
	
	@PostMapping("/flights")
	public List<FlightResponse> getAllAvailableFlight( @Valid @RequestBody FlightDto flightDto) {
	
		return flightService.getAllAvailableFlightDetails(flightDto);
	}

}

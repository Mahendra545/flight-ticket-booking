package com.flightticket.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightticket.dto.FlightDto;
import com.flightticket.dto.FlightResponse;
import com.flightticket.entity.Flight;
import com.flightticket.exception.MissMatchDate;
import com.flightticket.repository.FlightRepository;
import com.flightticket.service.FlightService;


@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	FlightRepository flightRepository;
	
	private final Logger logger = LogManager.getLogger(FlightServiceImpl.class);
	@Override
	public List<FlightResponse> getAllAvailableFlightDetails( FlightDto flightDto) {
		List<FlightResponse> flightResponseList = new ArrayList<>();
		logger.info("Checking the Source and Destination same or not");
		if(!flightDto.getSource().equalsIgnoreCase
				(flightDto.getDestination())) {
			if(flightDto.getDate()==null) {
				flightDto.setDate(java.time.LocalDate.now());
			}
			logger.info("Getting the flight details based on the search");
			List<Flight> flightList = flightRepository.findBySourceAndDestinationAndDate(flightDto.getSource(), flightDto.getDestination(),java.sql.Date.valueOf(flightDto.getDate()) );
			if(!flightList.isEmpty()) {
				flightList.forEach(flight->{
				FlightResponse flightResponse = new FlightResponse();
				flightResponse.setDate(flight.getDate());
				flightResponse.setDestination(flight.getDestination());
				flightResponse.setFlightid(flight.getFlightId());
				flightResponse.setFlightName(flight.getFlightName());
				flightResponse.setSource(flight.getSource());
				flightResponse.setTime(flight.getTime());
				flightResponseList.add(flightResponse);
				
				});
				logger.info("Sending the flight details based on the search");
				return flightResponseList;
			}else {
				logger.error("No flights are available");
				throw new MissMatchDate("No flights are available");
			}
		}
		else {
			logger.error("Source and destination should not be same");
			throw new MissMatchDate("Source and destination should not be same");
		}
		
		
	}

}

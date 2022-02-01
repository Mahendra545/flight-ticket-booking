package com.flightticket.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightticket.dto.FlightDetailsRequest;
import com.flightticket.dto.FlightDetailsResponse;
import com.flightticket.entity.Flight;
import com.flightticket.exception.MyApplicationException;
import com.flightticket.repository.FlightRepository;
import com.flightticket.service.AdminService;



@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	FlightRepository flightsRepository;

	private final Logger logger = LogManager.getLogger(AdminServiceImpl.class);
	
	@Override
	public FlightDetailsResponse saveFlightDetails(FlightDetailsRequest flightDetailsRequest) {
		try {
			logger.info("checking flight details already existing or not");
			Flight response = flightsRepository.findByFlightNameAndSourceAndDateAndTime(flightDetailsRequest.getFlightName(), flightDetailsRequest.getSource(),
					flightDetailsRequest.getDate(),flightDetailsRequest.getTime());
			if(response!=null) {
				logger.error("Flight Details Already exists");
				throw new MyApplicationException("Flight Details Already exists");
			}else {
				logger.info("Saving the flight details");
				Flight flight = new Flight();
				BeanUtils.copyProperties(flightDetailsRequest, flight);
				Flight dbresponse = flightsRepository.save(flight);

				FlightDetailsResponse flightDetailsResponse = new FlightDetailsResponse();
				flightDetailsResponse.setMessage("Flight Details added successfully with details");
				flightDetailsResponse.setFlightDetails(dbresponse);
				logger.info("Flight Details added successfully");
				return flightDetailsResponse;
			}
		}catch (Exception e) {
			throw e;
		}
	}

}

package com.flightticket.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.flightticket.dto.FlightDetailsRequest;
import com.flightticket.dto.FlightDetailsResponse;
import com.flightticket.entity.Flight;
import com.flightticket.exception.MyApplicationException;
import com.flightticket.repository.FlightRepository;

@ExtendWith(SpringExtension.class)
public class AdminServiceImplTest {

	@InjectMocks
	AdminServiceImpl adminServiceImpl;

	@Mock
	FlightRepository flightsRepository;

	@Test
	public void testSaveFlightDetailsFailure() {
		FlightDetailsRequest flightDetailsRequest = new FlightDetailsRequest();
		flightDetailsRequest.setSource("Hyderabad");
		flightDetailsRequest.setDestination("Chennai");
		flightDetailsRequest.setFlightName("Indigo");
		flightDetailsRequest.setDate(new Date(2022,01,31));
		flightDetailsRequest.setTime("11:00");
		Flight flights = new Flight();

		Mockito.when(flightsRepository.findByFlightNameAndSourceAndDateAndTime(flightDetailsRequest.getFlightName(), flightDetailsRequest.getSource(),
				flightDetailsRequest.getDate(),flightDetailsRequest.getTime())).thenReturn(flights);

		com.flightticket.exception.MyApplicationException thrown = Assertions.assertThrows(MyApplicationException.class, () -> {
			adminServiceImpl.saveFlightDetails(flightDetailsRequest);

		});
		Assertions.assertEquals("Flight Details Already exists",thrown.getMessage());



	}


	@Test
	public void testSaveFlightDetailsSuccess() {
		FlightDetailsRequest flightDetailsRequest = new FlightDetailsRequest();
		flightDetailsRequest.setSource("Hyderabad");
		flightDetailsRequest.setDestination("Chennai");
		flightDetailsRequest.setFlightName("Indigo");
		flightDetailsRequest.setDate(new Date(2022,01,31));
		flightDetailsRequest.setTime("11:00");
		Flight flights = new Flight();

		Mockito.when(flightsRepository.findByFlightNameAndSourceAndDateAndTime(flightDetailsRequest.getFlightName(), flightDetailsRequest.getSource(),
				flightDetailsRequest.getDate(),flightDetailsRequest.getTime())).thenReturn(null);
		BeanUtils.copyProperties(flightDetailsRequest, flights);
		Mockito.when(flightsRepository.save(flights)).thenReturn(flights);

		FlightDetailsResponse response = adminServiceImpl.saveFlightDetails(flightDetailsRequest);
		
		assertEquals("Flight Details added successfully with details", response.getMessage());


	}

}

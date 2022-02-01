package com.flightticket.service;

import com.flightticket.dto.FlightDetailsRequest;
import com.flightticket.dto.FlightDetailsResponse;

public interface AdminService {

	FlightDetailsResponse saveFlightDetails(FlightDetailsRequest flightDetailsRequest);
}

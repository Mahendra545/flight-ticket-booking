package com.flightticket.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flightticket.dto.FlightDetailsRequest;
import com.flightticket.dto.FlightDetailsResponse;
import com.flightticket.entity.Flight;
import com.flightticket.service.AdminService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@Operation(summary = "add flight details",description = "as a admin i can add flight details",tags="Post")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Flights details added successfully",
					content= {@Content(mediaType = "application/json",schema =@Schema(implementation =FlightDetailsResponse.class ))}),
			@ApiResponse(responseCode = "500" , description = "Flight Details Already exists",content = @Content)
	})
    @PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/flight")
	public ResponseEntity<FlightDetailsResponse> addFlightDetails(@Valid @RequestBody FlightDetailsRequest flightDetailsRequest) {
		
		FlightDetailsResponse response = adminService.saveFlightDetails(flightDetailsRequest);
		
		if(response!=null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

}

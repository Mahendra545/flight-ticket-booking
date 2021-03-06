package com.flightticket.controller;

import static com.flightticket.constants.Constants.TOKEN_PREFIX;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.flightticket.config.JwtTokenUtil;
import com.flightticket.dto.BookingDto;
import com.flightticket.dto.BookingHistory;
import com.flightticket.service.BookingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
public class BookingController {

	@Autowired
	BookingService bookingService;

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	 private final Logger logger = LogManager.getLogger(BookingController.class);
	
	@Operation(summary = "Get booking history",description = "Get a list of tickets booking history",tags="Get")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the ticket booking history",
					content= {@Content(mediaType = "application/json",schema =@Schema(implementation =BookingHistory.class ))}),
			@ApiResponse(responseCode = "404" , description = "Ticket booking history not found",content = @Content)
	})
	@SecurityRequirement(name = "flight")
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/booking")
	public BookingHistory getBookingHistory(@RequestHeader (name="Authorization") String token){
		logger.info("Getting the user name from JWT token");
		String userName = jwtTokenUtil.getUsernameFromToken(token.replace(TOKEN_PREFIX,""));
		return bookingService.getBookingHistory(userName);
	}
	
	@SecurityRequirement(name = "flight")
	@PostMapping("/bookTicket")
	public ResponseEntity<?> bookTicket(@Valid @RequestBody BookingDto bookingDto){
		
	
		return new ResponseEntity<>(bookingService.bookTicket(bookingDto),HttpStatus.OK);
	
	}

}

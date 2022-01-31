package com.flightticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication
@OpenAPIDefinition(info=@Info(title = "Flight Ticket API",version = "1.0",description = "Flight ticket booking and checking the booking history"))
@SecurityScheme(name = "flight", scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class FlightTicketApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightTicketApplication.class, args);
	}

}

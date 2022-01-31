package com.flightticket.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightDetailsRequest {

	@NotEmpty(message = "Please enter flight Name")
	private String flightName;

	@NotEmpty(message = "Please enter source")
	private String source;

	@NotEmpty(message = "Please enter destination")
	private String destination;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	@NotNull
	private Date date;

	@NotEmpty(message = "Please enter time")
	private String time;
}

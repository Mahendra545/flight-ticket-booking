package com.flightticket.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer flightId;
	
	private String flightName;
	
	private String source;
	
	private String destination;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date date;
	
	private String time;
}

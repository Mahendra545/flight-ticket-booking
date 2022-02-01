package com.flightticket.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "booking_details")
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookingId;
	@JoinColumn(name = "flight_id")
	@ManyToOne(targetEntity = Flight.class,cascade = CascadeType.ALL )
	private Flight flightId;
	private int userId;
	
	//@OneToMany( mappedBy = "booking")
	@OneToMany(mappedBy = "booking", fetch = FetchType.EAGER)
	private List<Passenger> passengers;
	
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public Flight getFlightId() {
		return flightId;
	}
	public void setFlightId(Flight flightId) {
		this.flightId = flightId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public List<Passenger> getPassengers() {
		return passengers;
	}
	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}
	
	


}

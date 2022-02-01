package com.flightticket.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "passenger_details")
public class Passenger {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer passengerId;
	private int age;
	private String name;
	private String gender;
	private String mealPreference;
	private String seatPreference;
	//@ManyToOne(fetch = FetchType.LAZY)
	@ManyToOne(fetch = FetchType.EAGER, optional = false,cascade = CascadeType.ALL)
	@JsonIgnore
	@JoinColumn(name = "booking_id", nullable = false)
	private Booking booking;
	public Integer getPassengerId() {
		return passengerId;
	}
	public void setPassengerId(Integer passengerId) {
		this.passengerId = passengerId;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMealPreference() {
		return mealPreference;
	}
	public void setMealPreference(String mealPreference) {
		this.mealPreference = mealPreference;
	}
	public String getSeatPreference() {
		return seatPreference;
	}
	public void setSeatPreference(String seatPreference) {
		this.seatPreference = seatPreference;
	}
	public Booking getBooking() {
		return booking;
	}
	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	
	
	

}

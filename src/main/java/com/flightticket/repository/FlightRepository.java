package com.flightticket.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightticket.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

	Flight findByFlightNameAndSourceAndDateAndTime(String flightName, String source, Date date, String time);

}

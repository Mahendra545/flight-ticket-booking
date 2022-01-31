package com.flightticket.dto;



import java.sql.Date;

public class FlightResponse {
	
		private Integer flightid;
		private String flightName;
		private String source;
		private String destination;
		private Date date;
		private String time;
		public Integer getFlightid() {
			return flightid;
		}
		public void setFlightid(Integer flightid) {
			this.flightid = flightid;
		}
		public String getFlightName() {
			return flightName;
		}
		public void setFlightName(String flightName) {
			this.flightName = flightName;
		}
		public String getSource() {
			return source;
		}
		public void setSource(String source) {
			this.source = source;
		}
		public String getDestination() {
			return destination;
		}
		public void setDestination(String destination) {
			this.destination = destination;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		

}

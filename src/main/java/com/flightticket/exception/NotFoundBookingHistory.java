package com.flightticket.exception;

public class NotFoundBookingHistory extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
    public NotFoundBookingHistory(){
        super();
    }
    public NotFoundBookingHistory(String message){
        super(message);
    }
}

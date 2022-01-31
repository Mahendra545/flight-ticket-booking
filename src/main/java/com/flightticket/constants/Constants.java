package com.flightticket.constants;

public class Constants {
private Constants() {
		
	}
    public static final long TOKEN_VALIDITY = (long) 5*60*60;
    public static final String SIGNING_KEY = "flight";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTHORITIES_KEY ="roles";
}

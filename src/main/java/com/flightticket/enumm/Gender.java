package com.flightticket.enumm;



public enum Gender {

	  M {
	    @Override
	    public String toString() {
	      return "MALE";
	    }
	  },
	  F {
	    @Override
	    public String toString() {
	      return "FEMALE";
	    }
	  }

	}
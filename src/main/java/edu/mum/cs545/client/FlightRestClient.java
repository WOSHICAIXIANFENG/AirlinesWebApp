package edu.mum.cs545.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import cs545.airline.model.Flight;

@Named
@SessionScoped
public class FlightRestClient implements Serializable {

	public List<Flight> getAllFlight2() {
		Flight flight = new Flight("NW 36", "2009-08-06", "19:10:00", "2015-06-25", "19:10:00");
		List<Flight> flights = new ArrayList<>();
		System.out.println("Samuel Test flights = " + flights);
		flights.add(flight);
		return flights;
	}
}

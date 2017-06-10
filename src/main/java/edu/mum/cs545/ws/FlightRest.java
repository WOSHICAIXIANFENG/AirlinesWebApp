package edu.mum.cs545.ws;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import cs545.airline.model.Airline;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;
import cs545.airline.service.FlightService;

@Named
@Path("flight")
public class FlightRest {
	
	@Inject
	private FlightService flightService;
	
	@Path("all")
	@Produces("application/json")
	@GET
	public List<Flight> getAllFlight() {
		List<Flight> flights = flightService.findAll();
		return flights;
	}
	
	@Path("find/by/number")
	@Produces("application/json")
	@GET
	public List<Flight> getFlightByNumber(@QueryParam("flightnr") String flightnr) {
		List<Flight> flights = flightService.findByNumber(flightnr);
		return flights;
	}
	
	@Path("find/by/airline")
	@Consumes("application/json")
	@Produces("application/json")
	@POST
	public List<Flight> getFlightByAirline(Airline airline) {
		List<Flight> flights = flightService.findByAirline(airline);
		return flights;
	}
	
	@Path("find/by/origin")
	@Consumes("application/json")
	@Produces("application/json")
	@POST
	public List<Flight> getFlightByOrigin(Airport airport) {
		List<Flight> flights = flightService.findByOrigin(airport);
		return flights;
	}
	
	@Path("find/by/destination")
	@Consumes("application/json")
	@Produces("application/json")
	@POST
	public List<Flight> getFlightByDestination(Airport airport) {
		List<Flight> flights = flightService.findByDestination(airport);
		return flights;
	}
	
	@Path("find/by/arrival")
	@Produces("application/json")
	@GET
	public List<Flight> getFlightByArrival(@QueryParam("datetime") Date datetime) {
		List<Flight> flights = flightService.findByArrival(datetime);
		return flights;
	}
	
	@Path("find/by/arrivalbtw")
	@Produces("application/json")
	@GET
	public List<Flight> getFlightByArrivalBetween(@QueryParam("datetimeFrom") Date datetimeFrom,
			@QueryParam("datetimeTo") Date datetimeTo) {
		List<Flight> flights = flightService.findByArrivalBetween(datetimeFrom, datetimeTo);
		return flights;
	}
	
	@Path("find/by/depart")
	@Produces("application/json")
	@GET
	public List<Flight> getFlightByDeparture(@QueryParam("datetime") Date datetime) {
		List<Flight> flights = flightService.findByDeparture(datetime);
		return flights;
	}
	
	@Path("find/by/departbtw")
	@Produces("application/json")
	@GET
	public List<Flight> getFlightByDepartureBetween(@QueryParam("datetimeFrom") Date datetimeFrom,
			@QueryParam("datetimeTo") Date datetimeTo) {
		List<Flight> flights = flightService.findByDepartureBetween(datetimeFrom, datetimeTo);
		return flights;
	}
	
	@Path("update")
	@Consumes("application/json")
	@Produces("application/json")
	@POST
	public Flight updateFlight(Flight flight) {
		flightService.update(flight);
		return flight;
	}
	
	//2015-06-25	09:00:00	2009-08-06	19:10:00	NW 36	2	3	4	5
	// 1 / 09:11:11
	@Path("test/update/{id}/{name}")
	@Produces("application/json")
	@GET
	public void updateTest(@PathParam(value="id") long id, @PathParam(value = "arrivalTime") String arrivalTime) {
		Flight flight = new Flight("NW 36", "2009-08-06", "19:10:00", "2015-06-25", arrivalTime);
		flight.setId(id);
		Client client = ClientBuilder.newClient();
		client.target("http://localhost:8080/airlinesWebApp/rs/flight/update").request(MediaType.APPLICATION_JSON).post(Entity.json(flight));
	}
}

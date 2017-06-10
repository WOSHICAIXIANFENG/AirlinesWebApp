package edu.mum.cs545.ws;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
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

import cs545.airline.model.Airport;
import cs545.airline.model.Flight;
import cs545.airline.service.AirportService;

@Named
@Path("airport")
public class AirportRest {
	
	@Inject
	private AirportService AirportService;
	
	@Path("all")
	@Produces("application/json")
	@GET
	public List<Airport> getAllAirports() {
		List<Airport> ports = AirportService.findAll();
		return ports;
	}
	
	@Path("")
	@Produces("application/json")
	@GET
	public Airport getAirportByCode(@QueryParam("airportcode") String airportcode) {
		Airport plane = AirportService.findByCode(airportcode);
		return plane;
	}
	
	@Path("")
	@Consumes("application/json")
	@Produces("application/json")
	@POST
	public List<Airport> getAirportByArrival(Flight flight) {
		List<Airport> lines = AirportService.findByArrival(flight);
		return lines;
	}
	
	@Path("")
	@Consumes("application/json")
	@Produces("application/json")
	@POST
	public List<Airport> getAirportByDeparture(Flight flight) {
		List<Airport> lines = AirportService.findByDeparture(flight);
		return lines;
	}
	
	@Path("")
	@Produces("application/json")
	@GET
	public List<Airport> getAirportByCity(@QueryParam("city") String city) {
		List<Airport> ports = AirportService.findByCity(city);
		return ports;
	}
	
	@Path("")
	@Produces("application/json")
	@GET
	public List<Airport> getAirportByCountry(@QueryParam("country") String country) {
		List<Airport> ports = AirportService.findByCountry(country);
		return ports;
	}
	
	@Path("")
	@Produces("application/json")
	@GET
	public List<Airport> getAirportByName(@QueryParam("name") String name) {
		List<Airport> ports = AirportService.findByName(name);
		return ports;
	}
	
	@Path("add")
	@POST
	public String addAirport(@FormParam("airportcode") String airportcode,
							  @FormParam("name") String name,
							  @FormParam("city") String city,
							  @FormParam("country") String country) {
		Airport ap = new Airport(airportcode, name, city, country);
		AirportService.create(ap);
		return "success";
	}
	
	
	@Path("delete")
	@Consumes("application/json")
	@POST
	public boolean deleteAirport(Airport Airport) {
		AirportService.delete(Airport);
		return true;
	}
	
	@Path("update")
	@Consumes("application/json")
	@Produces("application/json")
	@POST
	public Airport updateAirport(Airport Airport) {
		AirportService.update(Airport);
		return Airport;
	}
	
	// 4 / Schiphol1
	@Path("test/update/{id}/{name}")
	@Produces("application/json")
	@GET
	public void updateTest(@PathParam(value="id") long id, @PathParam(value = "capacity") int capacity) {
		Airport Airport = new Airport("AMS", "Schiphol", "Amsterdam", "The Netherlands");
		Airport.setId(id);
		Client client = ClientBuilder.newClient();
		client.target("http://localhost:8080/airlinesWebApp/rs/airport/update").request(MediaType.APPLICATION_JSON).post(Entity.json(Airport));
	}
}

package edu.mum.cs545.ws;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import cs545.airline.model.Airline;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;

@Named
@Path("airline")
public class AirlineRest {
	@Inject
	private AirlineService airlineService;

	@Path("all")
	@Produces("application/json")
	@GET
	public List<Airline> getAllAirline() {
		List<Airline> lines = airlineService.findAll();
		return lines;
	}
	
	@Path("")
	@Produces("application/json")
	@GET
	public Airline getAirlineByName(@QueryParam("name") String name) {
		Airline line = airlineService.findByName(name);
		return line;
	}
	
	@Path("")
	@Consumes("application/json")
	@Produces("application/json")
	@POST
	public List<Airline> getAirlineByFlight(Flight flight) {
		List<Airline> lines = airlineService.findByFlight(flight);
		return lines;
	}
	
	@Path("add")
	@POST
	public String addAirline(@DefaultValue("Default Name") @FormParam("name") String name) {
		System.out.println("Samuel Test name = " + name );
		Airline ap = new Airline();
		ap.setName(name);
		airlineService.create(ap);
		return "success" +  name + " , new airline = " + airlineService.findByName(name);
	}
	
	
	@Path("delete")
	@Consumes("application/json")
	@POST
	public boolean deleteAirline(Airline airline) {
		airlineService.delete(airline);
		return true;
	}
	
	@Path("update")
	@Consumes("application/json")
	@POST
	public boolean updateAirline(Airline airline) {
		airlineService.update(airline);
		return true;
	}
}

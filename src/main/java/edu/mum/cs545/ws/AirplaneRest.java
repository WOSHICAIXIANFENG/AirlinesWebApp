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

import cs545.airline.model.Airplane;
import cs545.airline.model.Flight;
import cs545.airline.service.AirplaneService;

@Named
@Path("airplane")
public class AirplaneRest {
	
	@Inject
	private AirplaneService airplaneService;
	
	@Path("all")
	@Produces("application/json")
	@GET
	public List<Airplane> getAllAirplanes() {
		List<Airplane> planes = airplaneService.findAll();
		return planes;
	}
	
	@Path("")
	@Produces("application/json")
	@GET
	public Airplane getAirplaneBySrlnr(@QueryParam("serialno") String serialno) {
		Airplane plane = airplaneService.findBySrlnr(serialno);
		return plane;
	}
	
	@Path("")
	@Consumes("application/json")
	@Produces("application/json")
	@POST
	public List<Airplane> getAirplaneByFlight(Flight flight) {
		List<Airplane> lines = airplaneService.findByFlight(flight);
		return lines;
	}
	
	@Path("")
	@Produces("application/json")
	@GET
	public List<Airplane> getAirplaneByModel(@QueryParam("model") String model) {
		List<Airplane> planes = airplaneService.findByModel(model);
		return planes;
	}
	
	@Path("add")
	@POST
	public String addAirplane(@FormParam("serialnr") String serialnr,
							  @FormParam("model") String model,
							  @FormParam("capacity") int capacity) {
		Airplane ap = new Airplane(serialnr, model, capacity);
		airplaneService.create(ap);
		return "success";
	}
	
	
	@Path("delete")
	@Consumes("application/json")
	@POST
	public boolean deleteAirplane(Airplane airplane) {
		airplaneService.delete(airplane);
		return true;
	}
	
	@Path("update")
	@Consumes("application/json")
	@Produces("application/json")
	@POST
	public Airplane updateAirplane(Airplane airplane) {
		airplaneService.update(airplane);
		return airplane;
	}
	
	// 3 / 600
	@Path("test/update/{id}/{name}")
	@Produces("application/json")
	@GET
	public void updateTest(@PathParam(value="id") long id, @PathParam(value = "capacity") int capacity) {
		Airplane airplane = new Airplane("12345", "A380", capacity);
		airplane.setId(id);
		Client client = ClientBuilder.newClient();
		client.target("http://localhost:8080/airlinesWebApp/rs/airplane/update").request(MediaType.APPLICATION_JSON).post(Entity.json(airplane));
	}
}

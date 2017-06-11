package edu.mum.cs545.ui.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cs545.airline.model.Airline;
import cs545.airline.service.AirlineService;
import edu.mum.cs545.ws.AirlineRest;

@Named("flightFilter")
@ViewScoped
public class FlightFilterBean implements Serializable {
	private static final long serialVersionUID = -6461674436819414652L;
	
	private List<Airline> airlineList;
	
	public List<Airline> getAirlineList() {
		return airlineList;
	}

	public void setAirlineList(List<Airline> airlineList) {
		this.airlineList = airlineList;
	}

	@Inject
	private AirlineRest airlineRest;
	
	@PostConstruct
	public void init() {
		//airlineList = new ArrayList<>();
		airlineList = airlineRest.getAllAirline();
	}
	
	private Date departDate;
	
	private String airline;

	public Date getDepartDate() {
		return departDate;
	}

	public void setDepartDate(Date departDate) {
		this.departDate = departDate;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}
	
	
}

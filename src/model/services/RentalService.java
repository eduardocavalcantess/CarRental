package model.services;

import model.entities.CarRental;
import model.entities.Invoce;

public class RentalService {
	private Double pricePerDay;
	private Double pricePerHour;
	
	private BrazilTaxService taxService;

	public RentalService(Double pricePerDay, Double pricePerHour, BrazilTaxService taxService) {
		this.pricePerDay = pricePerDay;
		this.pricePerHour = pricePerHour;
		this.taxService = taxService;
	}
	
	public void processInvoce(CarRental carRental) {
		long t1 = carRental.getStart().getTime();
		long t2 = carRental.getEnd().getTime();
		double hours = (double)(t2 - t1) / 1000 / 60 / 60;
		
		double basicPayment;
		if (hours <= 12.0) {
			basicPayment = Math.ceil(hours) * pricePerHour; //ceil = hora arredondada
		}
		else {
			basicPayment = Math.ceil(hours / 24) * pricePerDay;
		}
		
		double tax = taxService.tax(basicPayment);
		carRental.setInvoce(new Invoce(basicPayment, tax));
	}
	
	
}

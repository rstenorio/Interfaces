package model.services;

import model.entities.CarRental;
import model.entities.Invoice;

public class RentalService {
	private Double pricePerHour;
	private Double pricePerDay;
	
	private TaxService taxService;

	public RentalService(Double pricePerHour, Double pricePerDay, TaxService taxService) {
		super();
		this.pricePerHour = pricePerHour;
		this.pricePerDay = pricePerDay;
		this.taxService = taxService;
	}
	
	public void processInvoice(CarRental carRental) {
		long t1 = carRental.getStart().getTime();
		long t2 = carRental.getFinish().getTime();
		double hours = (double) (t2 - t1) / 1000 / 60 / 60;
		
		double basicPay;
		if(hours <= 12.0) {
			basicPay = Math.ceil(hours) * pricePerHour;
		}else {
			basicPay = Math.ceil(hours / 12) * pricePerDay; 
		}
		
		double tax = taxService.tax(basicPay);
		
		carRental.setInvoice(new Invoice(basicPay, tax));
	}
}

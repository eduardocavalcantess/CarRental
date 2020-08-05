package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Invoce;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Scanner sc = new Scanner(System.in);
		

		System.out.println("Enter rental data:");
		System.out.print("Car model: ");
		String vehicle = sc.nextLine();
		
		System.out.print("Pickup (dd/mm/yyyy hh:mm): ");
		Date start = sdf.parse(sc.nextLine());
		
		System.out.print("Return (dd/mm/yyyy hh:mm): ");
		Date end = sdf.parse(sc.nextLine());
		
		CarRental carR = new CarRental(start, end, new Vehicle(vehicle));
		
		System.out.print("Enter price per hour: ");
		Double pricePerHour = sc.nextDouble();
		
		System.out.print("Enter price per day: ");
		Double pricePerDay = sc.nextDouble();
		
		RentalService rentalService = new RentalService(pricePerDay, pricePerHour, new BrazilTaxService());	
		rentalService.processInvoce(carR);
		
		System.out.println("INVOCE:");
		System.out.println("Basic payment: " + String.format("%.2f", carR.getInvoce().getBasicPayment()));
		System.out.println("Tax: " + String.format("%.2f", carR.getInvoce().getTax()));
		System.out.println("Total Payment: " + String.format("%.2f", carR.getInvoce().getTotalPayment()));
		
		sc.close();
	}

}

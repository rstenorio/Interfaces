package app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");

		System.out.println("Enter rental data");

		System.out.print("Car model: ");
		String carModel = sc.nextLine();

		System.out.print("Pickup date (DD/MM/YYYY hh:mm): ");
		Date start = sdf.parse(sc.nextLine());

		System.out.print("Return date (DD/MM/YYYY hh:mm): ");
		Date finish = sdf.parse(sc.nextLine());

		CarRental cr = new CarRental(start, finish, new Vehicle(carModel));

		System.out.print("Enter the price per hour: ");
		double pricePerHour = sc.nextDouble();

		System.out.print("Enter the price per day: ");
		double pricePerDay = sc.nextDouble();

		RentalService rs = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());

		rs.processInvoice(cr);

		System.out.println();
		System.out.println("INVOICE: ");
		System.out.println("Basic payment: " + cr.getInvoice().getBasicPayment());
		System.out.println("Tax: " + cr.getInvoice().getTax());
		System.out.println("Total payment: " + cr.getInvoice().getTotalPayment());

		sc.close();
	}

}

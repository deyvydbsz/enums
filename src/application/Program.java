package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.TimeZone;

import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import entities.enums.OrderStatus;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner (System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		sdf1.setTimeZone(TimeZone.getTimeZone("GMT-3"));
		Date currentDate = new Date(System.currentTimeMillis());
		
		System.out.println("Enter cliente data:");
		System.out.print("Name: ");
		String clientName = sc.nextLine();
		System.out.print("Email: ");
		String clientEmail = sc.nextLine();
		System.out.print("Birth date (DD/MM/YYYY): ");
		Date clientBirthDate = sdf.parse(sc.next());
		sc.nextLine();
		
		Client client = new Client(clientName, clientEmail, clientBirthDate);
		
		System.out.println("Enter order data:");
		System.out.print("Status: ");
		String orderStatus = sc.nextLine();
		OrderStatus.valueOf(orderStatus);
		
		Order order = new Order(new Date(), OrderStatus.valueOf(orderStatus), client);
		
		System.out.print("How many items to this order? ");
		int n = sc.nextInt();
		
		for (int i=1; i<=n; i++) {
			System.out.println("Enter #" + i + " item data:");
			System.out.print("Product name: ");
			sc.nextLine();
			String productName = sc.nextLine();
			
			System.out.print("Product price: ");
			double productPrice = sc.nextDouble();
			
			System.out.print("Quantity: ");
			int productQuantity = sc.nextInt();
			
			Product product = new Product(productName, productPrice);
			OrderItem item = new OrderItem(productQuantity, productPrice, product);
			order.addItem(item);			
		}
		
		System.out.println();
		System.out.println("ORDER SUMMARY:");
		System.out.println("Order moment: " + sdf1.format(currentDate));
		System.out.println("Order status: " + orderStatus);
		System.out.println("Client: " + client.toString());
		System.out.println("Order items:");
		System.out.printf(order.toString());
		System.out.printf("Total price: %.2f" , order.total());
		
		sc.close();
	}

}

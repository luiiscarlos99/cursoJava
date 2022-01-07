package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import entities.model.OrderStatus;

public class Program {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws ParseException {
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
		String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		System.out.println("Dados do cliente");
		System.out.print("Nome: ");
		String name = sc.nextLine();
		System.out.print("Email: ");
		String email = sc.nextLine();
		System.out.print("Aniversário: ");
		Date birthDate = sdf2.parse(sc.nextLine());
		
		System.out.println("Dados do pedido");
		System.out.print("Status: ");
		String status = sc.nextLine();
		System.out.print("Quantidade de itens: ");
		int quantidade = sc.nextInt();
		
		Order o = new Order(sdf.parse(timeStamp), OrderStatus.valueOf(status), new Client(name, email, birthDate));
		
		for(int i = 1; i <= quantidade; i++) {
			System.out.println("Dados do item " + i);
			System.out.print("Nome do produto: ");
			String nomeProduto = sc.next();
			System.out.print("Valor do produto: ");
			double valorProduto = sc.nextDouble();
			System.out.print("Quantidade: ");
			int quantProduto = sc.nextInt();
			
			OrderItem oi = new OrderItem(quantProduto, new Product(nomeProduto, valorProduto));
			o.addItem(oi);
		}
		
		System.out.println(o);
		
		sc.close();
	}

}

package entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.model.OrderStatus;

public class Order {
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	private static SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");

	private Date moment;
	private OrderStatus status;
	private Client client;
	private List<OrderItem> orderItem = new ArrayList<>();
	
	public Order() {
	}

	public Order(Date moment, OrderStatus status, Client client) {
		this.moment = moment;
		this.status = status;
		this.client = client;
	}

	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	
	public List<OrderItem> getOrderItem() {
		return orderItem;
	}

	public void addItem(OrderItem o) {
		this.orderItem.add(o);
	}
	
	public void removeItem(OrderItem o) {
		this.orderItem.remove(o);
	}
	
	public double total() {
		double sum = 0;
		for(OrderItem o : orderItem) {
			sum += o.subTotal();
		}
		return sum;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Ordem:" + "\n");
		sb.append("Data da ordem: " + sdf.format(moment) + "\n");
		sb.append("Status: " + status + "\n");
		sb.append("Cliente: " + client.getName() + ", (" + sdf2.format(client.getBirthDate()) + ") - " + client.getEmail() + "\n");
		sb.append("Itens:" + "\n");
		for(OrderItem oi : orderItem) {
			sb.append(oi.getProduct().getName() + " $" + oi.getProduct().getPrice() + ", Quantidade: " + oi.getQuantity() + ", SubTotal: $" + oi.subTotal() + "\n");
		}
		sb.append("Total: $" + total());
		
		return sb.toString();
	}
}

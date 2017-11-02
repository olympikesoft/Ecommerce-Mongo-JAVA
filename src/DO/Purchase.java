package DO;

import java.util.ArrayList;
import java.util.List;

public class Purchase {
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotalpay() {
		return totalpay;
	}

	public void setTotalpay(int totalpay) {
		this.totalpay = totalpay;
	}

	public int quantity;
	public int totalpay;

	public List<User> getPersonsList() {
		return personsList;
	}

	public void AddPerson(User user) {
		personsList.add(user);
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void addProduct(Product product) {
		productList.add(product);
	}

	public Purchase() {
		super();
	}

	public List<User> personsList = new ArrayList<User>();
	public List<Product> productList = new ArrayList<Product>();
}

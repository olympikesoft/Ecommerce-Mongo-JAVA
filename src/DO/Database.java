package DO;

import java.net.UnknownHostException;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import java.util.logging.Logger;
import java.util.logging.Level;

public class Database {

	private MongoClient mongo;
	private DBCollection table;

	public Database() {
		try {
			mongo = new MongoClient("localhost", 27017);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
			mongoLogger.setLevel(Level.SEVERE); // e.g. or Log.WARNING, etc.
			DB db = mongo.getDB("testdb");
			table = db.getCollection("user");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}

	}

	public void InsertUser(List<User> UserList) {
		for (int i = 0; i < UserList.size(); i++) {
			BasicDBObject document = new BasicDBObject();
			document.put("Nome", UserList.get(i).getName());
			document.put("Email", UserList.get(i).getEmail());
			document.put("FacebookID", UserList.get(i).getFacebookUrl());
			document.put("Morada", UserList.get(i).getAdress());
			document.put("CodigoPostal", UserList.get(i).getCp());
			table.insert(document);
		}
	}

	public void InsertPurchase(List<Purchase> PurchaseList) {
		for (int i = 0; i < PurchaseList.size(); i++) {
			BasicDBObject document = new BasicDBObject();
			document.put("Nome do Cliente", PurchaseList.get(i).personsList
					.get(0).getName());
			document.put("Nome do Produto", PurchaseList.get(i).productList
					.get(0).getName());
			document.put("Quantidade", PurchaseList.get(i).getQuantity());
			document.put("Total", PurchaseList.get(i).getTotalpay());
			table.insert(document);
		}
	}

	public void InsertProduct(List<Product> ProductList) {
		for (int i = 0; i < ProductList.size(); i++) {
			BasicDBObject document = new BasicDBObject();
			document.put("Nome do Produto", ProductList.get(i).getName());
			document.put("Preço", ProductList.get(i).getPrice());
			document.put("Quantidade", ProductList.get(i).getQuantity());
			table.insert(document);
		}

	}
	/*
	 * public int GetAllPurchase(){
	 *
	 *
	 * int quantity = 0; List<Purchase> PurchaseList = new
	 * ArrayList<Purchase>();
	 *
	 * BasicDBObject query = new BasicDBObject(); query.put("user", "user");
	 * DBCursor cursor = table.find(query); while (cursor.hasNext()) { DBObject
	 * theObj = cursor.next(); //How to get the DBObject value to ArrayList of
	 * Java Object?
	 *
	 * BasicDBList studentsList = (BasicDBList) theObj.get("purchase"); for (int
	 * i = 0; i < studentsList.size(); i++) { BasicDBObject studentObj =
	 * (BasicDBObject) studentsList.get(i); String firstName =
	 * studentObj.getString("firstName"); String lastName =
	 * studentObj.getString("lastName"); String age =
	 * studentObj.getString("age"); String gender =
	 * studentObj.getString("gender");
	 *
	 * Student student = new Student(); student.setFirstName(firstName);
	 * student.setLastName(lastName); student.setAge(age);
	 * student.setGender(gender);
	 *
	 * students.add(student); } } }
	 */
}

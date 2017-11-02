package Classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

import DO.Database;
import DO.Product;
import DO.Purchase;
import DO.User;

class MyFileReader extends Thread {

	String filePath;
	String fileName;
	Database db;
	String destinationFolder; // destionationDB;

	List<User> UserList;
	List<Product> ProductList;
	List<Purchase> PurchaseList;

	MyFileReader(String aFileName) {

		fileName = aFileName;
		filePath = "C:\\Invoices\\";
		destinationFolder = "C:\\Trash";
		db = new Database();
		UserList = new ArrayList<User>();
		ProductList = new ArrayList<Product>();
		PurchaseList = new ArrayList<Purchase>();

	}

	@Override
	public void run() {
		File fileNamePath = FileUtils.getFile(filePath + fileName);
		File DestinationPathFolder = FileUtils.getFile(destinationFolder);

		try {

			if (fileName.startsWith("user")) {
				readFileUser();
			} else if (fileName.startsWith("products")) {
				readFileProducts();

			}

			FileUtils.moveFileToDirectory(fileNamePath, DestinationPathFolder,
					true);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (UserList.size() > 0) {

			db.InsertUser(UserList);

			/*
			 * System.out.println("User details:");
			 * System.out.println(UserList.get(i).getName());
			 * System.out.println(UserList.get(i).getAdress());
			 * System.out.println(UserList.get(i).getCp());
			 */

			// db.saveWebsphereErrorstoDB(UserList);
		}

		if (ProductList.size() > 0) {

			db.InsertProduct(ProductList);
			/*
			 * for (int i = 0; i < ProductList.size(); i++) { /* for (int j = 0;
			 * j < PurchaseList.size(); j++) { if (ProductList.get(i).getName()
			 * == PurchaseList.get(j) .getProductList().get(0).getName()) { int
			 * diff = ProductList.get(i).getQuantity() -
			 * PurchaseList.get(j).getProductList().get(0) .getQuantity();
			 * System.out.println(diff); }
			 * 
			 * }
			 */
			/*
			 * System.out.println(ProductList.get(i).getName());
			 * System.out.println(ProductList.get(i).getQuantity());
			 * 
			 * 
			 * }
			 */
		}
		if (PurchaseList.size() > 0) {

			db.InsertPurchase(PurchaseList);
			/* for (int i = 0; i < PurchaseList.size(); i++) { */

			/*
			 * System.out.println("Purchases"); System.out.println(PurchaseList
			 * .get(i).getPersonsList().get(0) .getName()); System.out.println
			 * (PurchaseList.get(i).getProductList().get(0) .getName());
			 * System.out.println(PurchaseList.get(i).getTotalpay());
			 */
		}

	}

	private void readFileProducts() throws IOException {
		File fileNamePath = FileUtils.getFile(filePath + fileName);

		String fivepattern = "(?m)(?<=\\bProduto:).*$";
		String sixpattern = "(?m)(?<=\\bQuantidade:).*$";
		String sevenpattern = "(?m)(?<=\\bPrice:).*$";

		// Matcher the pattern

		Pattern nmprod = Pattern.compile(fivepattern);
		Pattern qtprod = Pattern.compile(sixpattern);
		Pattern pricprod = Pattern.compile(sevenpattern);

		BufferedReader reader = new BufferedReader(new FileReader(fileNamePath));
		String line;
		line = reader.readLine();

		while (line != null) {

			Product prod = new Product();

			Matcher m4 = nmprod.matcher(line); // match productname;
			if (m4.find()) {
				prod.setName(m4.group());
				line = reader.readLine();

			}
			Matcher m5 = qtprod.matcher(line);
			if (m5.find()) {
				String quantity = m5.group().toString().replaceAll(" +", "");
				// int quantity = Integer.parseInt(m5.group().toString());
				prod.setQuantity(Integer.parseInt(quantity));
				line = reader.readLine();
			}
			Matcher m6 = pricprod.matcher(line);

			if (m6.find()) {
				prod.setPrice(m6.group());
				ProductList.add(prod);

			}

			// /System.out.println(line);

			line = reader.readLine();

		}

		try {
			reader.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void readFileUser() throws IOException {

		File fileNamePath = FileUtils.getFile(filePath + fileName);

		String pattern = "(?m)(?<=\\bNome:).*$";
		String secondpattern = "(?m)(?<=\\bEmail:).*$";
		String thirdpattern = "(?m)(?<=\\bFacebookID:).*$";
		String fourthpattern = "^(.+)[,\\s]+(.+?)\\s*(\\d{5})?$";
		String fivepattern = "(?m)(?<=\\bProduto:).*$";
		String sixpattern = "(?m)(?<=\\bQuantidade:).*$";
		String sevenpattern = "(?m)(?<=\\bPrice:).*$";

		// Matcher the pattern
		Pattern nme = Pattern.compile(pattern);
		Pattern ema = Pattern.compile(secondpattern);
		Pattern fb = Pattern.compile(thirdpattern);
		Pattern mor = Pattern.compile(fourthpattern);
		Pattern nmprod = Pattern.compile(fivepattern);
		Pattern qtprod = Pattern.compile(sixpattern);
		Pattern pricprod = Pattern.compile(sevenpattern);

		BufferedReader reader = new BufferedReader(new FileReader(fileNamePath));
		String line;
		line = reader.readLine();

		while (line != null) {

			User user = new User();
			Product prod = new Product();
			Purchase purch = new Purchase();
			Matcher m = nme.matcher(line);

			if (m.find()) {

				user.setName(m.group());

				line = reader.readLine();

			}
			Matcher m1 = ema.matcher(line);
			if (m1.find()) {

				user.setEmail(m1.group());
				line = reader.readLine();
			}
			Matcher m2 = fb.matcher(line);
			if (m2.find()) {
				user.setFacebookUrl(m2.group());
				line = reader.readLine();
			}
			Matcher m3 = mor.matcher(line);
			if (m3.find()) {

				// if (mas.lookingAt()) { // Check if exist datetime;
				user.setAdress(m3.group(1));
				user.setCp(m3.group(2));

				line = reader.readLine();
				UserList.add(user);
			}
			Matcher m4 = nmprod.matcher(line); // match productname;
			if (m4.find()) {
				prod.setName(m4.group());
				line = reader.readLine();

			}
			Matcher m5 = qtprod.matcher(line);
			if (m5.find()) {
				String quantity = m5.group().toString().replaceAll(" +", "");
				// int quantity = Integer.parseInt(m5.group().toString());
				purch.setQuantity(Integer.parseInt(quantity));
				line = reader.readLine();
			}
			Matcher m6 = pricprod.matcher(line);

			if (m6.find()) {
				String pay = m6.group().toString().replaceAll(" +", "");

				purch.setTotalpay(Integer.parseInt(pay));
				purch.addProduct(prod);
				purch.AddPerson(user);
				PurchaseList.add(purch);
			}

			// /System.out.println(line);

			line = reader.readLine();

		}

		try {
			reader.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

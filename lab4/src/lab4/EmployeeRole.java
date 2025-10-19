/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class EmployeeRole extends Roles {
    private ProductDataBase productsDatabase;
    private CustomerProductDatabase customerProductDatabase;

    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public EmployeeRole() {
        productsDatabase = new ProductDataBase("Products.txt");
        productsDatabase.readFromFile();

        customerProductDatabase = new CustomerProductDatabase("CustomerProducts.txt");
        customerProductDatabase.readFromFile();

    }
    public void addProduct(String productID, String productName, String manufacturerName, String supplierName, int quantity, float price) {

        if (productsDatabase.contains(productID)) {
            System.out.println("Product with ID " + productID + "already exists");
        } else {
            Product P = new Product(productID, productName, manufacturerName, supplierName, quantity, price);
            productsDatabase.insertRecord(P);
            System.out.println("Product with ID " + productID + "Successfully added");
        }

    }
public Product[] getListOfProducts() {

        ArrayList<Record> Records = productsDatabase.returnAllRecords();
        ArrayList<Product> productsList = new ArrayList<>();

        for (Record r : Records) {
            productsList.add((Product) r);
        }

        return productsList.toArray(new Product[0]);

    }
public CustomerProduct[] getListOfPurchasingOperations() {

        ArrayList<Record> Records = customerProductDatabase.returnAllRecords();
        ArrayList<CustomerProduct> CPlist = new ArrayList<>();

        for (Record r : Records) {
            CPlist.add((CustomerProduct) r);
        }

        return CPlist.toArray(new CustomerProduct[0]);

    }
public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate) {

        Product P = (Product) productsDatabase.getRecord(productID);

        int quantity = P.getQuantity();
        if (quantity == 0) {
            System.out.println("Product" + productID + "is out of stock");
            return false;
        }
         quantity--;
        P.setQuantity(quantity);
        CustomerProduct CP = new CustomerProduct(customerSSN, productID, purchaseDate);
        customerProductDatabase.insertRecord(CP);

        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();
        System.out.println("Purchase recorded for " + customerSSN + " -> " + productID);

        return true;
    }
public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate, LocalDate returnDate) {

        if (returnDate.isBefore(purchaseDate)) {
            System.out.println("Return date is before purchase date");
            return -1;
        }
        Product P = (Product) productsDatabase.getRecord(productID);

        if (P == null) {
            return -1;
        }
        String key = customerSSN + "," + productID + "," + purchaseDate.format(fmt);

        if (!customerProductDatabase.contains(key)) {
            System.out.println("Purchase record with key :" + key);
            return -1;
        }
        long days = ChronoUnit.DAYS.between(purchaseDate, returnDate);

        if (days > 14) {
            System.out.println("Return period exceeded: " + days + " days");
            return -1;
        }
         P.setQuantity(P.getQuantity() + 1);

        customerProductDatabase.deleteRecord(key);

        productsDatabase.saveToFile();

        customerProductDatabase.saveToFile();

        System.out.println("Return processed for " + key);

        return P.getPrice();

    }
 public boolean applyPayment(String customerSSN, LocalDate purchaseDate) {

        CustomerProduct[] CPlist = getListOfPurchasingOperations();

        boolean purchase = false;

        for (CustomerProduct CP : CPlist) {
            if (CP.getCustomerSSN().equals(customerSSN) && CP.getPurchaseDate().equals(purchaseDate)) {
                if (!CP.isPaid()) {
                    CP.setPaid(true);
                    purchase = true;
                }
            }
        }
        if (purchase) {
            customerProductDatabase.saveToFile();
        }

        return purchase;
    }
    
    public void logout(){
        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();
    }
}

}

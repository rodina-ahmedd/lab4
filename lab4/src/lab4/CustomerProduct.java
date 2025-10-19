/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerProduct extends Record {
    
    private String customerSSN;
    private String productId;
    private LocalDate purchaseDate;
    private boolean paid;
    
    private final static DateTimeFormatter date = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public CustomerProduct(String customerSSN, String productId, LocalDate purchaseDate) {
        this.customerSSN = customerSSN;
        this.productId = productId;
        this.purchaseDate = purchaseDate;
        
    }

    public String getCustomerSSN() {
        return customerSSN;
    }

    public String getProductId() {
        return productId;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
    
    @Override
    public String lineRepresentation(){
        String formattedDate = purchaseDate.format(date);
        return customerSSN + "," + productId + "," + formattedDate + "," + paid ; 
    }
    
    @Override
    
    public String getSearchKey(){
       String formattedDate = purchaseDate.format(date);
       return customerSSN + "," + productId + "," + formattedDate;    
    }

    String getProductID() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
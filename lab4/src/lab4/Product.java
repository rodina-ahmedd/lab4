/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;


public class Product extends Record {
    private String productId;
    private String productName; 
    private String manufacturerName;
    private String supplierName;
    private int quantity;
    private float price;

    public Product(String productId, String productName, String manufacturerName, String supplierName, int quantity, float price) {
        this.productId = productId;
        this.productName = productName;
        this.manufacturerName = manufacturerName;
        this.supplierName = supplierName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }
    
    
    
    @Override
    
    public String lineRepresentation(){
        return productId + "," + productName + "," + manufacturerName + "," + supplierName  + "," + quantity + "," + price ;
    }
    
    @Override
    
    public String getSearchKey(){
        return productId ;
    }
}
    


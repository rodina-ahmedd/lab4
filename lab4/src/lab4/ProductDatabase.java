/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

public class ProductDatabase extends Database {
    
    public ProductDatabase(String filename){
        super(filename);
    }
            
    //public Product(String productId, String productName, String manufacturerName, String supplierName, int quantity, float price)
    
    @Override
    public Record createRecordFrom(String line) {
       
        String[] parts = line.split(",");
       
        String productId =  parts[0];
        String productName = parts[1];
        String manufacturerName = parts[2];
        String supplierName = parts[3];
        int quantity = Integer.parseInt(parts[4]);
        float price = Float.parseFloat(parts[5]);
        

        return new Product(productId,productName,manufacturerName,supplierName,quantity,price);
    }
}

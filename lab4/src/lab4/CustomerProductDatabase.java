/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



public class CustomerProductDatabase extends Database {
    
    public CustomerProductDatabase (String filename){
        super(filename);
    }
    
    @Override
    public Record createRecordFrom(String line) {
       
        
       String[] parts = line.split(",");
       
        String customerSSN =  parts[0];
        String productId = parts[1];
        LocalDate purchaseDate = LocalDate.parse(parts[2], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        boolean paid = Boolean.parseBoolean(parts[3]); //stil not sure
        
        CustomerProduct CP = new CustomerProduct(customerSSN,productId,purchaseDate);
        
        CP.setPaid(paid);
      
        return CP;
    }
    
}


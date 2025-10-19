/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;


public class EmployeeUserDatabase extends Database {
    
    public EmployeeUserDatabase(String filename) {
        super(filename);
    }
    
    @Override
    public Record createRecordFrom(String line) {
       
        String[] parts = line.split(",");
       
        String employeeId =  parts[0];
        String Name = parts[1];
        String Email = parts[2];
        String Address = parts[3];
        String PhoneNumber = parts[4];
        

        return new EmployeeUser(employeeId,Name,Email,Address,PhoneNumber);
    }
    
}
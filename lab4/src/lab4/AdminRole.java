/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

import java.util.ArrayList;


public class AdminRole extends Roles {
    private EmployeeUserDatabase database;
     public AdminRole() {
        database = new EmployeeUserDatabase("Employees.txt");
        database.readFromFile();

    }
     public void addEmployee(String employeeId, String name, String email, String address, String phoneNumber) {
        if (database.contains(employeeId)) {
            System.out.println("Employee Id" + employeeId + " already exists");
        } else {
            EmployeeUser E = new EmployeeUser(employeeId, name, email, address, phoneNumber);
            database.insertRecord(E);

            System.out.println("Successfully added Employee with Id " + employeeId);

        }
    }
     public EmployeeUser[] getListOfEmployees() {

        ArrayList<Record> Records = database.returnAllRecords();
        ArrayList<EmployeeUser> Employees = new ArrayList<>();

        for (Record r : Records) {
            Employees.add((EmployeeUser) r);
        }

        return Employees.toArray(new EmployeeUser[0]);

    }
     public void removeEmployee(String key) {
        database.deleteRecord(key);
    }
     public void logout(){
        database.saveToFile();
    }
    
}

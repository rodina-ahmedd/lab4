
package lab4;


public class EmployeeUser extends Record{

    
    private String employeeId;
    private String Name;
    private String Email;
    private String Address;
    private String PhoneNumber;

    public EmployeeUser(String employeeId, String Name, String Email, String Address, String PhoneNumber) {
        this.employeeId = employeeId;
        this.Name = Name;
        this.Email = Email;
        this.Address = Address;
        this.PhoneNumber = PhoneNumber;
    }

    @Override
    public String lineRepresentation(){
        return employeeId + "," + Name + "," + Email + "," + Address + "," + PhoneNumber ;
    }
    
    @Override
    public String getSearchKey(){
        return employeeId ;
    }
}


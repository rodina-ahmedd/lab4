package lab4 ;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Lab4{


    public static void main(String[] args) {
        
        Scanner sc =new Scanner(System.in);
        AdminRole adminRole=new AdminRole();
        EmployeeRole employeeRole=new EmployeeRole();
        DateTimeFormatter fmt=DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println("Welcome to the Inventory System.");
        boolean running=true;
        while (running){
            System.out.println("\nChoose role:");
            System.out.println("1-Admin");
            System.out.println("2-Employee");
            System.out.println("3-Exit");
            int roleChoice=readInt(sc,"Invalid number");
            switch (roleChoice){
                case 1:
                    adminMenu(adminRole,sc);
                    break;
                case 2:
                        employeeMenu(employeeRole,sc,fmt);
                     break; 
                case 3 :{
                    System.out.println("Saving and exiting");
                    adminRole.logout();
                    employeeRole.logout();
                    running = false;
                }
                break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }
    private static void adminMenu(AdminRole admin,Scanner sc){
        while (true){
            System.out.println("\n Admin Menu");
            System.out.println("1-Add Employee");
            System.out.println("2-Remove Employee");
            System.out.println("3-List Employees");
            System.out.println("4-Back");
            int c=readInt(sc,"Invalid number.");
            switch (c) {
                case 1 :{
                    String id=readNonEmpty(sc,"employeeId: ");
                    String name = readNonEmpty(sc,"name: ");
                    String email = readNonEmpty(sc,"email: ");
                    String addr = readNonEmpty(sc,"address: ");
                    String phone = readNonEmpty(sc,"phone: ");
                    admin.addEmployee(id,name,email,addr,phone);
                }
                break;
                case 2: {
                    String rem=readNonEmpty(sc, "employeeId to remove: ");
                    admin.removeEmployee(rem);
                }
                break;
                case 3:{
                    EmployeeUser[] all = admin.getListOfEmployees();
                    for (EmployeeUser e : all) {
                        System.out.println(" - " + e.lineRepresentation());
                    }
                }
                break;
                case 4:{
                    return;
                }
                //break;
                default :
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void employeeMenu(EmployeeRole employeeRole, Scanner sc, DateTimeFormatter fmt){
        while (true) {
            System.out.println("\n Employee Menu");
            System.out.println("1-Add Product");
            System.out.println("2-List Products");
            System.out.println("3-Purchase Product");
            System.out.println("4-Return Product");
            System.out.println("5-List Purchases");
            System.out.println("6-Apply Payment");
            System.out.println("7-Back");
            int c=readInt(sc, "Invalid number");
            switch (c){
                case 1 : {
                    String pid=readNonEmpty(sc, "productID: ");
                    String pnm=readNonEmpty(sc, "productName: ");
                    String mn=readNonEmpty(sc, "manufacturerName: ");
                    String sn=readNonEmpty(sc, "supplierName: ");
                    int q=readPositiveInt(sc, "quantity must be positive");
                    float pr=readPositiveFloat(sc, "price must be positive");
                    employeeRole.addProduct(pid, pnm, mn, sn, q, pr);
                }
                break;
                case 2 :{
                    Product[] prds = employeeRole.getListOfProducts();
                    System.out.println("Products:");
                    for (Product p : prds) {
                        System.out.println(" - " + p.lineRepresentation());
                    }
                }
                break;
                case 3 : {
                    String ssn = readNonEmpty(sc, "customerSSN: ");
                    String buyPid = readNonEmpty(sc, "productID: ");
                    LocalDate purchaseDate = readDate(sc, fmt, "purchaseDate (dd-MM-yyyy): ");
                    boolean ok = employeeRole.purchaseProduct(ssn, buyPid, purchaseDate);
                    System.out.println("Purchase success: " + ok);
                }
                break;
                case 4 : {
                    String rssn = readNonEmpty(sc, "customerSSN: ");
                    String rpid = readNonEmpty(sc, "productID: ");
                    LocalDate purchaseDate2 = readDate(sc, fmt, "purchaseDate (dd-MM-yyyy): ");
                    LocalDate returnDate = readDate(sc, fmt, "returnDate (dd-MM-yyyy): ");
                    double price = employeeRole.returnProduct(rssn, rpid, purchaseDate2, returnDate);
                    System.out.println("Return result (price or -1): " + price);
                }
                case 5 :{
                    CustomerProduct[] cps = employeeRole.getListOfPurchasingOperations();
                    System.out.println("Purchases:");
                    for (CustomerProduct cp : cps) {
                        System.out.println(" - " + cp.lineRepresentation());
                    }
                }
                break;
                case 6:{
                    String pssn = readNonEmpty(sc, "customerSSN: ");
                    LocalDate pd2 = readDate(sc, fmt, "purchaseDate (dd-MM-yyyy): ");
                    boolean paid = employeeRole.applyPayment(pssn, pd2);
                    System.out.println("Payment applied: " + paid);
                }
                break;
                case 7 : {
                    return;
                }
               
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

 
    private static String readNonEmpty(Scanner sc, String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("This field cannot be empty.");
            }
        } while (input.isEmpty());
        return input;
    }

    private static int readInt(Scanner sc, String errorMsg) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(errorMsg);
                
            }
        }
    }

    private static int readPositiveInt(Scanner sc, String errorMsg) {
        int val;
        do {
            val = readInt(sc, "Invalid number.");
            if (val <= 0) System.out.println("not " + errorMsg);
        } while (val <= 0);
        return val;
    }

    private static float readPositiveFloat(Scanner sc, String errorMsg) {
        while (true) {
            try {
                float val = Float.parseFloat(sc.nextLine().trim());
                if (val > 0) return val;
                System.out.println("not" + errorMsg);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number");
            }
        }
    }

    private static LocalDate readDate(Scanner sc, DateTimeFormatter fmt, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            try {
                return LocalDate.parse(input, fmt);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Use dd-MM-yyyy.");
            }
        }
    }
}

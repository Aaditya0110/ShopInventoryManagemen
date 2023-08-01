import java.sql.SQLException;
import java.util.Scanner;

public class App {
    public static void product() throws SQLException {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        int choice2;
        Imethods im=new Imethods();
        while (!exit) {
            displayProductMenu();
            choice2 = sc.nextInt();
            sc.nextLine();

            switch (choice2) {
                case 1:
                    im.addProduct();
                    break;
                case 2:
                     im.viewInventory();
                    break;
                case 3:
                    // addSupplier(scanner);
                    break;
                case 4:
                    im.displayTables();
                    break;
                case 5:
                    im.deleteProduct();
                    break;
                case 6:
                        im.update();
                        break;
                case 7:
                    System.out.println("Enter id:");
                    int fid=sc.nextInt();
                    sc.nextLine();
                    im.findbyid(fid);
                    break;
                case 8:
                    // sc.nextLine();
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        // sc.close();
    }

     public static void supplier() throws SQLException {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        supmain sup=new supmain();
        esupplier esup=new esupplier();
        while (!exit) {
            displaySupplierMenu();
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    sup.editSupplierMain();
                    break;
                case 2:
                    esup.viewSupplier();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        // sc.close();
    }

    public static void sales()throws SQLException{
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        Sales sales=new Sales();
        while (!exit) {
            displaySalesMenu();
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    sales.addSales();
                    break;
                
                case 2:
                    sales.generateReport();
                    break;

                case 3:
                    sales.viewSales();
                    break;

                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        // sc.close();
    }

    private static void displayProductMenu() {
        System.out.println("Product");
        System.out.println("------------------------------");
        System.out.println("1. Add Product");
        System.out.println("2. View Inventory");
        System.out.println("3. Add Supplier");
        System.out.println("4. Display Tables");
        System.out.println("5. Delete");
        System.out.println("6. Update");
        System.out.println("7. Search");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void displaySalesMenu() {
        System.out.println("Sales");
        System.out.println("------------------------------");
        System.out.println("1. Add sale");
        System.out.println("2. Generate Report");
        System.out.println("3. View Sales");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void displayMainMenu() {
        System.out.println("Shop Inventory Management System");
        System.out.println("------------------------------");
        System.out.println("1. Inventory");
        System.out.println("2. Supplier");
        System.out.println("3. Sales");
        System.out.println("4. Exit");
        System.out.print("Choose table:"); 
    }

   private static void displaySupplierMenu(){
    System.out.println("Supplier");
        System.out.println("------------------------------");
        System.out.println("1. Edit supplier");
        System.out.println("2. View Supplier table");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
   }

    public static void main(String[] args) throws SQLException {
    Scanner sc = new Scanner(System.in);
    boolean mexit = false;
    int choice;
    while (!mexit) {
        displayMainMenu();
        // System.out.println();
        // sc.nextLine();
        choice = sc.nextInt();
        sc.nextLine(); 
    
        switch (choice) {
            case 1:
                product();
                // sc.nextLine();
                break;
            case 2:
                supplier();
                break;
            case 3:
                sales();
                break;
            case 4:
                mexit = true;
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    sc.close();
}

}

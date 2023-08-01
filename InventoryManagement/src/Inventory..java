import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

class Inventory {
    private int product_id;
    private String product_name;
    private double price;
    private int stock;
    private int supplier_id;
    
    public int getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setInventory(String product_name,double price,int stock,int supplier_id){
        this.product_name=product_name;
        this.price=price;
        this.stock=stock;
        this.supplier_id=supplier_id;
    }
    
}

abstract class IMethode{
    abstract void viewInventory()throws SQLException;
    abstract void addProduct() throws SQLException;
    abstract void displayDeleteMenu() throws SQLException;
}

class Imethods extends IMethode{
    Scanner sc=new Scanner(System.in);
    public void viewInventory() throws SQLException{
        Statement statement = ConnectionManager.getConnection();
                String query = "SELECT inventory.product_id,inventory.product_name,inventory.price,inventory.stock,supplier.supplier_name FROM inventory inner join supplier on inventory.supplier_id=supplier.supplier_id;";
                ResultSet rs=statement.executeQuery(query);
                        System.out.println("------------------------------------------------------------------------------------");
        System.out.format("| %-10s | %-20s | %-10s | %-8s | %-20s |%n", "Product ID", "Product Name", "Price", "Stock", "Supplier Name");
        System.out.println("------------------------------------------------------------------------------------");

                while (rs.next()) {
            int productId = rs.getInt(1);
            String productName = rs.getString("product_name");
            int price = rs.getInt("price");
            int stock = rs.getInt("stock");
            String supplierName = rs.getString("supplier_name");

            System.out.format("| %-10d | %-20s | %-10d | %-8d | %-20s |%n", productId, productName, price, stock, supplierName);
        }

        System.out.println("------------------------------------------------------------------------------------");
        System.out.println();
    }

    public Integer getPrice(int product_id) throws SQLException{
        Statement statement = ConnectionManager.getConnection();
                String query = "SELECT price FROM inventory where product_id="+product_id;
                ResultSet rs=statement.executeQuery(query);
                int n=0;
                while(rs.next()){
                n=rs.getInt("price");
                }
                return n;
    }

    public Integer getStock(int product_id) throws SQLException{
        Statement statement = ConnectionManager.getConnection();
                String query = "SELECT stock FROM inventory where product_id="+product_id;
                ResultSet rs=statement.executeQuery(query);
                int n=0;
                while(rs.next()){
                n=rs.getInt("stock");
                }
                return n;
    }

    public void addProduct()throws SQLException{
        Inventory inv=new Inventory();
        System.out.println("productName:");
        String productName=sc.next();
        System.out.println("price :");
        Double price=sc.nextDouble();
        System.out.println("stock :");
        int stock=sc.nextInt();
        System.out.println("supplierId :");
        int supplierId=sc.nextInt();
        inv.setInventory(productName, price, stock, supplierId);
         Statement statement = ConnectionManager.getConnection();
       String query="Insert into inventory(product_name,price,stock,supplier_id) values('"+inv.getProduct_name()+"',"+inv.getPrice()+","+inv.getStock()+","+inv.getSupplier_id()+")";
       statement.executeUpdate(query);
    }
    
    public void displayDeleteMenu() {
        System.out.println("------------------------------");
        System.out.println("1. Delete by id");
        System.out.println("2. Delete by name");
    }
    
    public void deleteProduct() throws SQLException{
        Statement statement = ConnectionManager.getConnection();
        displayDeleteMenu();
        int choice2=sc.nextInt();
        switch(choice2){
            case 1:
              System.out.println("Enter id:");
               int did=sc.nextInt();
               String query = "DELETE FROM inventory WHERE product_id = "+did;
               statement.executeUpdate(query);
               break;
            case 2:
            System.out.println("Enter name:");
            String dname=sc.next();
               String query2 = "DELETE FROM inventory WHERE product_name = "+dname;
               statement.executeUpdate(query2);
               break;
        }
    }

    public void displayTables() throws SQLException{
        Statement statement = ConnectionManager.getConnection();
        String query="show tables";
        ResultSet rs=statement.executeQuery(query);
                System.out.println("------------------------------");
                while(rs.next()){
                    System.out.println(rs.getString(1));
                }
                System.out.println("------------------------------");
    }

    public void findbyid(int id)throws SQLException{
        Statement statement = ConnectionManager.getConnection();
        String query = "SELECT inventory.product_id,inventory.product_name,inventory.price,inventory.stock,supplier.supplier_name FROM inventory inner join supplier on inventory.supplier_id=supplier.supplier_id where product_id="+id;
        ResultSet rs=statement.executeQuery(query);
        while(rs.next()){
         System.out.println(rs.getInt(1)+" "+rs.getString("product_name")+" "+rs.getInt("price")+" "+rs.getInt("stock")+" "+rs.getString("supplier_name"));
        }
    }

    public void diplayUpdateMenu(){
        System.out.println("1. Update name:");
        System.out.println("2. Update stock");
        System.out.println("3. Update price");
        System.out.println("4. Update supplier");

    }

    public void update()throws SQLException{
        System.out.print("Enter id:");
        int id=sc.nextInt();
        System.out.print("Enter stock:");
        int stock=sc.nextInt();
        Connection connetion=ConnectionManager.getStatement();
        String query="update inventory set stock = ? where product_id = ?";
        PreparedStatement preparedStatement = connetion.prepareStatement(query);
        preparedStatement.setInt(1, stock);
        preparedStatement.setInt(2, id);
        preparedStatement.executeUpdate();
    }

     public void update2(int id,int stock)throws SQLException{
        Connection connetion=ConnectionManager.getStatement();
        String query="update inventory set stock = ? where product_id = ?";
        PreparedStatement preparedStatement = connetion.prepareStatement(query);
        preparedStatement.setInt(1, stock);
        preparedStatement.setInt(2, id);
        preparedStatement.executeUpdate();
    }
}
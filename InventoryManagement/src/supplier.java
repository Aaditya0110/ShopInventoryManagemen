import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Statement;
// import java.sql.Statement;
// import java.sql.ResultSet;

class supplier {
    public void editSupplier(int id,String name) throws SQLException{
        Connection connetion=ConnectionManager.getStatement();
        String query="update supplier set supplier_name = ? where supplier_id = ?";
        PreparedStatement preparedStatement = connetion.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, id);
        preparedStatement.executeUpdate();
    }
    
}

class esupplier extends supplier{ 
    public void editSupplier(int id,String email) throws SQLException{
        Connection connetion=ConnectionManager.getStatement();
        String query="update supplier set email = ? where supplier_id = ?";
        PreparedStatement preparedStatement = connetion.prepareStatement(query);
        preparedStatement.setString(1, email);
        preparedStatement.setInt(2, id);
        preparedStatement.executeUpdate();

    }
    public void viewSupplier() throws SQLException{
        System.out.println("Supplier Id | Name | email");
        Statement statement = ConnectionManager.getConnection();
                String query = "SELECT * FROM supplier";
                ResultSet rs=statement.executeQuery(query);
                while(rs.next()){
                  System.out.println(rs.getInt(1)+" | "+rs.getString(2)+" | "+rs.getString(3));
                }
    }
}

class supmain{
    Scanner sc=new Scanner(System.in);
    supplier s=new supplier();
    esupplier e=new esupplier();
    public void displayeditSupOptions(){
        System.out.println("1. Edit name");
        System.out.println("2. Edit email");
    }

    public void editSupplierMain() throws SQLException{
        displayeditSupOptions();
        int n=sc.nextInt();
        System.out.print("Enter id:");
        int id=sc.nextInt();
        switch(n){
            case 1:
                System.out.println("Enter name:");
                String name=sc.next();
                s.editSupplier(id,name);
                break;
            case 2:
                System.out.println("Enter email:");
                String email=sc.next();
                e.editSupplier(id, email);
        }
    }

    // public void deleteProduct() throws SQLException{
    //     int choice2=sc.nextInt();
    //     switch(choice2){
    //         case 1:
    //            int did=sc.nextInt();
    //            statement.executeUpdate(query);
    //            break;
    //         case 2:
    //         System.out.println("Enter name:");
    //         String dname=sc.next();
    //            String query2 = "DELETE FROM inventory WHERE product_name = "+dname;
    //            statement.executeUpdate(query2);
    //            break;
    //     }
}

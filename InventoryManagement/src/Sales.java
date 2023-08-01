import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Sales {
    Scanner sc=new Scanner(System.in);
    Imethods i=new Imethods();

    public void viewSales() throws SQLException{
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.format("| %-10s | %-17s | %-20s | %-8s | %-15s |%n", "Product ID", "Transaction Date", "Product Name", "Quantity", "Total Revenue");
        System.out.println("--------------------------------------------------------------------------------------");
        Statement statement = ConnectionManager.getConnection();
                String query = "SELECT sales.transaction_id,sales.transaction_date,inventory.product_name,sales.quantity,sales.total_revenue FROM sales join inventory on inventory.product_id=sales.product_id";
                ResultSet rs=statement.executeQuery(query);
                while (rs.next()) {
                    int productId = rs.getInt(1);
                    java.sql.Date transactionDate = rs.getDate(2);
                    String productName = rs.getString(3);
                    int quantity = rs.getInt(4);
                    double totalRevenue = rs.getDouble(5);
        
                    System.out.format("| %-10d | %-17s | %-20s | %-8d | %-15.2f |%n", productId, transactionDate, productName, quantity, totalRevenue);
                }
        
                System.out.println("--------------------------------------------------------------------------------------");
    }

    public void generateReport()throws SQLException{
        System.out.print("Enter start date:");
        String startDateStr=sc.nextLine();
        //  LocalDate startDate = LocalDate.parse(inputDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.print("Enter end date:");
       String endDateStr=sc.nextLine();
       LocalDate startDate = LocalDate.parse(startDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate endDate = LocalDate.parse(endDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        // System.out.println("Total amount sold:");
        Connection connection=ConnectionManager.getStatement();
        String query="Select sum(total_revenue) from sales where transaction_date between ? and ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setDate(1, java.sql.Date.valueOf(startDate));
        preparedStatement.setDate(2, java.sql.Date.valueOf(endDate));
        ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int productId = resultSet.getInt(1);
                System.out.println("Total amount sold: " + productId);
            }
        System.out.println("Sales of each product:");
        System.out.println("Revenue | Quantity  |Product Id");
        String query2="Select sum(total_revenue),sum(quantity),product_id from sales where transaction_date between ? and ? group by product_id; ";
        PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
        preparedStatement2.setDate(1, java.sql.Date.valueOf(startDate));
        preparedStatement2.setDate(2, java.sql.Date.valueOf(endDate));
        ResultSet resultSet2 = preparedStatement2.executeQuery();
            while (resultSet2.next()) {
                 System.out.println(resultSet2.getInt(1)+" | "+resultSet2.getInt(2)+" | "+resultSet2.getInt(3));
                 
            }
    }

    public void addSales() throws SQLException{
        System.out.print("Enter product id:");
        int productId = sc.nextInt();
        System.out.print("Enter quantity:");
        int quantity = sc.nextInt();
        double productPrice = i.getPrice(productId); 
        int stock=i.getStock(productId);
        if(stock>quantity){
        i.update2(productId,stock-quantity);
        // System.out.println(productPrice);
        double totalRevenue = quantity * productPrice;

            Connection connection=ConnectionManager.getStatement();
            String insertQuery = "INSERT INTO sales (transaction_date, product_id, quantity, total_revenue) VALUES (CURRENT_TIMESTAMP, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            preparedStatement.setInt(1, productId);
            preparedStatement.setInt(2, quantity);
            preparedStatement.setDouble(3, totalRevenue);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Transaction inserted successfully.");
            } else {
                System.out.println("Failed to insert the transaction.");
            }
        }
        else{
            System.out.println("Not enough stock");
        }
    }
}

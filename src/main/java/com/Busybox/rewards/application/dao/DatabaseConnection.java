
  package com.Busybox.rewards.application.dao;
  
  import java.sql.Connection; import java.sql.DriverManager; import
  java.sql.SQLException;
  import java.sql.PreparedStatement;

  
  public class DatabaseConnection {
  
  private static final String JDBC_URL =
  "jdbc:mysql://192.168.0.106:3306/the_juice_nation_master"; private static
  final String USER = "master"; private static final String PASSWORD = "demo";
  
  public static Connection getConnection() throws SQLException { return
  DriverManager.getConnection(JDBC_URL, USER, PASSWORD); }
  
  public static void abc() {
      String jdbcUrl = "jdbc:mysql://192.168.0.106:3306/the_juice_nation_master";
      String username = "master";
      String password = "demo";

      String updateQuery = "UPDATE wallet_balance_master " +
                           "SET wallet_balance = wallet_balance + 500 " +
                           "WHERE wallet_id = ? AND customer_id = ?";

      try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
           PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
           
          preparedStatement.setString(1, "wid-1");
          preparedStatement.setString(2, "CUS1");

          int rowsAffected = preparedStatement.executeUpdate();
          System.out.println(rowsAffected + " row(s) updated.");
      } catch (SQLException e) {
          e.printStackTrace();
      
  }
}
  
  
  }
 
    
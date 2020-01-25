package com.revature.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import com.revature.model.Expense;
import com.revature.model.User;

public class ErsDaoPostgres implements ErsDao {

  private static Connection conn;
  // private static Logger log = Logger.getLogger(arg0)

  static {
    try {
      Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e1) {
      e1.printStackTrace();
    }

    try {
      conn = DriverManager.getConnection(System.getenv("connstring"), System.getenv("username"),
          System.getenv("password"));
      // "jdbc:postgresql://database-1.cs4xqzqqrfzt.us-east-1.rds.amazonaws.com:5432/postgres",
      // "postgres", "5235923a");
      // log.info("Connected to Database");
    } catch (SQLException e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    System.out.println("You are Connected");
  }

  @Override
  public User getUser(int id) {
    // TODO Auto-generated method stub
    User out = null;
    try {
      PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user_table WHERE id =?");
      stmt.setInt(1, id);
      stmt.execute();

      ResultSet rs = stmt.getResultSet();
      rs.next();

      out = new User(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"),
          rs.getString("username"), rs.getString("user_password"));


    } catch (SQLException e) {
      e.printStackTrace();
    }

    return out;

  }

  @Override
  public List<User> getAllUsers() {
    // TODO Auto-generated method stub

    List<User> allUsers = new ArrayList<User>();

    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      stmt = conn.prepareStatement("SELECT * FROM user_table");
      if (stmt.execute()) {
        rs = stmt.getResultSet();
      }
      while (rs.next()) {
        allUsers.add(new User(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"),
            rs.getString("username"), rs.getString("user_password")));
      }
    } catch (SQLException e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return allUsers;
  }

  @Override
  public Expense getExpense(int id) {
    // TODO Auto-generated method stub
    Expense out = null;
    try {
      PreparedStatement stmt = conn.prepareStatement("SELECT * FROM expense WHERE id =?");
      stmt.setInt(1, id);
      stmt.execute();

      ResultSet rs = stmt.getResultSet();
      rs.next();

      out = new Expense(rs.getInt("id"), rs.getString("title"), rs.getString("expense_date"),
          rs.getDouble("expense_amount"), rs.getInt("userid"), rs.getInt("managerid"), rs.getString("resolved_date"), rs.getString("image_link"));

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return out;
  }

  @Override
  public List<Expense> getAllUserExpenses(int id) {
    // TODO Auto-generated method stub
    List<Expense> allExpenses = new ArrayList<Expense>();

    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      stmt = conn.prepareStatement("SELECT * FROM expense WHERE userid = ?");
      stmt.setInt(1, id);
      if (stmt.execute()) {
        rs = stmt.getResultSet();
      }
      while (rs.next()) {
        allExpenses.add(new Expense(rs.getInt("id"), rs.getString("title"), rs.getString("expense_date"),
            rs.getDouble("expense_amount"), rs.getInt("userid"), rs.getInt("managerid"), rs.getString("decision"), rs.getString("image_link")));
      }
    } catch (SQLException e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return allExpenses;
  }

  @Override
  public User getUsername(String userName) {

    // TODO Auto-generated method stub
    User out = null;
    try {
      PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user_table WHERE username = ?");
      stmt.setString(1, userName);
      stmt.execute();

      ResultSet rs = stmt.getResultSet();
      rs.next();

      out = new User(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"),
          rs.getString("username"), rs.getString("user_password"));


    } catch (SQLException e) {
      e.printStackTrace();
    }

    return out;
  }

  @Override
  public void saveExpense(Expense expense) {
    // TODO Auto-generated method stub

    PreparedStatement stmt = null;

    try {
      stmt = conn.prepareStatement(
          "insert into expense (id, title, expense_date, expense_amount, userid, managerid, decision, image_link) values(default, ?, ?, ?, ?, ?, ?, ?)");
      stmt.setString(1, expense.getTitle());
      stmt.setString(2, expense.getExpenseDate());
      stmt.setDouble(3, expense.getExpenseAmount());
      stmt.setInt(4, expense.getUserId());
      stmt.setInt(5, expense.getManagerId());
      stmt.setString(6, expense.getDecision());
      stmt.setString(7, expense.getImageLink());

      stmt.execute();

      System.out.println("Your expense was created");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<Expense> getAllExpenses() {
    List<Expense> allExpenses = new ArrayList<Expense>();

    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      stmt = conn.prepareStatement("SELECT * FROM expense");
      if (stmt.execute()) {
        rs = stmt.getResultSet();
      }
      while (rs.next()) {
        allExpenses.add(new Expense(rs.getInt("id"), rs.getString("title"), rs.getString("expense_date"),
            rs.getDouble("expense_amount"), rs.getInt("userid"), rs.getInt("managerid"), rs.getString("decision"), rs.getString("image_link")));
      }
    } catch (SQLException e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return allExpenses;
  }

  @Override
  public void updateExpense(Expense expense) {
    // TODO Auto-generated method stub
    PreparedStatement stmt = null;

    try {
      stmt = conn.prepareStatement(
          "UPDATE expense SET title = ?, expense_date =?, expense_amount = ?, userid=?, managerid=?, decision=? WHERE id = ?");
      stmt.setString(1, expense.getTitle());
      stmt.setString(2, expense.getExpenseDate());
      stmt.setDouble(3, expense.getExpenseAmount());
      stmt.setInt(4, expense.getUserId());
      stmt.setInt(5, expense.getManagerId());
      stmt.setString(6, expense.getDecision());
      stmt.setInt(7, expense.getId());

      stmt.execute();

      System.out.println("Your expense was updated.");
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }

  @Override
  public void updateUser(User user) {
    // TODO Auto-generated method stub
    PreparedStatement stmt = null;
    
    try {
      stmt = conn.prepareStatement(
          "UPDATE user SET lastname = ?, firstname = ?, username = ?, user_password = ? WHERE id = ?");
      stmt.setString(1, user.getLastName());
      stmt.setString(2,  user.getFirstName());
      stmt.setString(3, user.getUserName());
      stmt.setString(4, user.getPassword());
      stmt.setInt(5, user.getId());
      
      stmt.execute();
      System.out.println("User was updated");
    }  catch (Exception e) {
      e.printStackTrace();
    }
    
  }
}

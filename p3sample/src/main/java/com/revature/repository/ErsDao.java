package com.revature.repository;

import java.util.List;
import com.revature.model.Expense;
import com.revature.model.User;

public interface ErsDao {
  
  User getUser(int id);
  
  User getUsername(String userName);
  
  List<User> getAllUsers();
  
  
  Expense getExpense(int id);
  
  List<Expense> getAllExpenses();
  
  List<Expense> getAllUserExpenses(int id);
  
  void saveExpense(Expense expense);
  
  void updateExpense(Expense expense);
  
  void updateUser(User user);

}

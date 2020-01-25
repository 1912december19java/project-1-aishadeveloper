package com.revature.services;

import java.util.List;
import com.revature.model.Expense;
import com.revature.model.User;
import com.revature.repository.ErsDao;

public class ErsService {
  
  private ErsDao ersDao;
  
  
  public ErsService(ErsDao ersDao) {
    this.ersDao = ersDao;
  }
  
  
  public User getUser(int id) {
    return ersDao.getUser(id);
  }
  
  
  public List<User> getAllUsers(){
    return ersDao.getAllUsers();
  }
  
  public User getUsername(String userName) {
    return ersDao.getUsername(userName);
  }
  
  public void saveExpense(Expense expense) {
    ersDao.saveExpense(expense);
  }
  
  public List<Expense> getAllExpenses(){
    return ersDao.getAllExpenses();
  }
  
  public List<Expense> getAllUserExpense(int id){
    return ersDao.getAllUserExpenses(id);
  }
  
  public Expense getExpense(int id) {
    return ersDao.getExpense(id);
  }
  
  public void updateExpense(Expense expense) {
    ersDao.updateExpense(expense);
  }
  

}

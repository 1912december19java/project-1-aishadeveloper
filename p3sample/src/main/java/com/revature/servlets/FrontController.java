package com.revature.servlets;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Expense;
import com.revature.model.User;
import com.revature.repository.ErsDaoPostgres;
import com.revature.services.ErsService;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(name = "FrontController", urlPatterns = {"/api/*"})
public class FrontController extends HttpServlet {

  private ErsService ersService;
  private ObjectMapper om;

  @Override
  public void init() throws ServletException {
    // TODO Auto-generated method stub
    this.ersService = new ErsService(new ErsDaoPostgres());
    this.om = new ObjectMapper();
    super.init();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // TODO Auto-generated method stub
    System.out.println("My FrontController ERS Server is working");
    

    System.out.println("URI:" + req.getRequestURI());
    String[] tokens = req.getRequestURI().split("/");
    boolean idWasSpecified = false;

    switch (tokens[3]) {
      case "users":
        idWasSpecified = tokens.length > 4;
        if (idWasSpecified) {
          User user = new User();
          if (tokens[4].matches("[0-9]")) {
            Integer id = Integer.valueOf(tokens[4]);
            user = ersService.getUser(id);
          } else {
            String username = tokens[4];
            user = ersService.getUsername(username);
          }

          if (user == null) {
            resp.setStatus(404);
          }
          resp.getWriter().write(om.writeValueAsString(user));
          System.out.println(om.writeValueAsString(user));

        } else {
          List<User> users = ersService.getAllUsers();

          String myJsonObject = om.writeValueAsString(users);

          resp.getWriter().write(myJsonObject);
          System.out.println(myJsonObject);
        }

        break;

      case "expenses":
        idWasSpecified = tokens.length > 4;
        if (idWasSpecified) {
          Expense expense = new Expense();
          if (tokens[4].matches("[0-9]")) {
            Integer id = Integer.valueOf(tokens[4]);
            expense = ersService.getExpense(id);
          }

          if (expense == null) {
            resp.setStatus(404);
          }
          resp.getWriter().write(om.writeValueAsString(expense));
          System.out.println(om.writeValueAsString(expense));

        } else {
          List<Expense> expenses = ersService.getAllExpenses();

          String myJsonObject = om.writeValueAsString(expenses);

          resp.getWriter().write(myJsonObject);
          System.out.println(myJsonObject);
        }
        break;

      default:
        resp.setStatus(404);
        break;
    }
  }


  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // TODO Auto-generated method stub
    System.out.println("URI" + req.getRequestURI());
    String[] tokens = req.getRequestURI().split("/");
    List<Expense> allExpenses = ersService.getAllExpenses();

    switch (tokens[3]) {
      case "expense":
        Expense receivedExpense = om.readValue(req.getReader(), Expense.class);
        System.out.println(receivedExpense);
        
      
        for (Expense expense : allExpenses) {
          if (expense.getId() > 0) {
            ersService.updateExpense(receivedExpense);
            break;
          }else{
            ersService.saveExpense(receivedExpense);
            break;
          }
        }        

      default:
        resp.setStatus(404);
    }
  }
  
  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // TODO Auto-generated method stub
    //super.doPut(req, resp);
    System.out.println("URI" + req.getRequestURI());
    String[] tokens = req.getRequestURI().split("/");

    switch (tokens[3]) {
      case "expense":
        Expense receivedExpense = om.readValue(req.getReader(), Expense.class);
        System.out.println(receivedExpense);
        ersService.updateExpense(receivedExpense);
        break;
        
      case "user":
        User receivedUser = om.readValue(req.getReader(), User.class);
        System.out.println(receivedUser);
        //ersService.saveExpense(receivedUser);
        break;

      default:
        resp.setStatus(404);
    }
    
  }
}



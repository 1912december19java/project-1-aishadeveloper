package com.revature.model;

public class Expense {
  private int id;
  private String title;
  private String expenseDate;
  private double expenseAmount;
  private int userId;
  private int managerId;
  private String decision;
  private String imageLink;
 // private byte image;
  
  
  public Expense() {
    super();
  }  
  
  
  
public Expense(int id, String title, String expenseDate, double expenseAmount, int userId,
    int managerId, String decision, String imageLink) {
  super();
  this.id = id;
  this.title = title;
  this.expenseDate = expenseDate;
  this.expenseAmount = expenseAmount;
  this.userId = userId;
  this.managerId = managerId;
  this.decision = decision;
  this.imageLink = imageLink;
}




/*

  public byte getImage() {
    return image;
  }




  public void setImage(byte image) {
    this.image = image;
  }
*/



  public int getId() {
    return id;
  }


  public void setId(int id) {
    this.id = id;
  }


  public String getTitle() {
    return title;
  }


  public void setTitle(String title) {
    this.title = title;
  }


  public String getExpenseDate() {
    return expenseDate;
  }


  public void setExpenseDate(String expenseDate) {
    this.expenseDate = expenseDate;
  }


  public double getExpenseAmount() {
    return expenseAmount;
  }


  public void setExpenseAmount(double expenseAmount) {
    this.expenseAmount = expenseAmount;
  }  


  public int getUserId() {
    return userId;
  }


  public void setUserId(int userId) {
    this.userId = userId;
  }


  public int getManagerId() {
    return managerId;
  }


  public void setManagerId(int managerId) {
    this.managerId = managerId;
  }


  public String getDecision() {
    return decision;
  }


  public void setDecision(String decision) {
    this.decision = decision;
  }


  public String getImageLink() {
    return imageLink;
  }


  public void setImageLink(String imageLink) {
    this.imageLink = imageLink;
  }


  @Override
  public String toString() {
    return "Expense [id=" + id + ", title=" + title + ", expenseDate=" + expenseDate
        + ", expenseAmount=" + expenseAmount + ", userId=" + userId + ", managerId=" + managerId
        + ", decision=" + decision + ", imageLink=" + imageLink + "]";
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    long temp;
    temp = Double.doubleToLongBits(expenseAmount);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    result = prime * result + ((expenseDate == null) ? 0 : expenseDate.hashCode());
    result = prime * result + id;
    result = prime * result + ((imageLink == null) ? 0 : imageLink.hashCode());
    result = prime * result + managerId;
    result = prime * result + ((decision == null) ? 0 : decision.hashCode());
    result = prime * result + ((title == null) ? 0 : title.hashCode());
    result = prime * result + userId;
    return result;
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Expense other = (Expense) obj;
    if (Double.doubleToLongBits(expenseAmount) != Double.doubleToLongBits(other.expenseAmount))
      return false;
    if (expenseDate == null) {
      if (other.expenseDate != null)
        return false;
    } else if (!expenseDate.equals(other.expenseDate))
      return false;
    if (id != other.id)
      return false;
    if (imageLink == null) {
      if (other.imageLink != null)
        return false;
    } else if (!imageLink.equals(other.imageLink))
      return false;
    if (managerId != other.managerId)
      return false;
    if (decision == null) {
      if (other.decision != null)
        return false;
    } else if (!decision.equals(other.decision))
      return false;
    if (title == null) {
      if (other.title != null)
        return false;
    } else if (!title.equals(other.title))
      return false;
    if (userId != other.userId)
      return false;
    return true;
  }
  
  
  




}

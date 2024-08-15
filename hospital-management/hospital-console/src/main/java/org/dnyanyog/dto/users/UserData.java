package org.dnyanyog.dto.users;


public class UserData {
  private String userName;

  private long userId;

  private String eMail;

  private String mobileNumber;

  private String role;

  private String status;

  private String password;

//  private String confirmPassword;

  public static UserData getInstace() {
    return new UserData();
  }

  public String getUserName() {
    return userName;
  }

  public UserData setUserName(String userName) {
    this.userName = userName;
    return this;
  }

  public String geteMail() {
    return eMail;
  }

  public UserData seteMail(String eMail) {
    this.eMail = eMail;
    return this;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public UserData setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
    return this;
  }

  public String getRole() {
    return role;
  }

  public UserData setRole(String role) {
    this.role = role;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserData setPassword(String password) {
    this.password = password;
    return this;
  }

//  public String getConfirmPassword() {
//    return confirmPassword;
//  }
//
//  public UserData setConfirmPassword(String confirmPassword) {
//    this.confirmPassword = confirmPassword;
//    return this;
//  }

  public String getStatus() {
    return status;
  }

  public UserData setStatus(String status) {
    this.status = status;
    return this;
  }

  public long getUserId() {
    return userId;
  }

  public UserData setUserId(long userId) {
    this.userId = userId;
    return this;
  }
}

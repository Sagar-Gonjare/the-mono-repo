package org.dnyanyog.entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity(name = "users_Table")
@Table
public class Users {

  @GeneratedValue @Id @Column private long userId;

  @Column private String userName;

  @Column private String eMail;

  @Column private String mobileNumber;

  @Column private String role;

  @Column private String password;

  //  @Column private String confirmPassword;

  @Column private String status;

  public static Users getInstance() {
    return new Users();
  }

  public long getUserId() {
    return userId;
  }

  public Users setUserId(long userId) {
    this.userId = userId;
    return this;
  }

  public String getUserName() {
    return userName;
  }

  public Users setUserName(String userName) {
    this.userName = userName;
    return this;
  }

  public String geteMail() {
    return eMail;
  }

  public Users seteMail(String eMail) {
    this.eMail = eMail;
    return this;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public Users setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
    return this;
  }

  public String getRole() {
    return role;
  }

  public Users setRole(String role) {
    this.role = role;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public Users setPassword(String password) {
    this.password = password;
    return this;
  }

  //  public String getConfirmPassword() {
  //    return confirmPassword;
  //  }
  //
  //  public Users setConfirmPassword(String confirmPassword) {
  //    this.confirmPassword = confirmPassword;
  //    return this;
  //  }

  public String getStatus() {
    return status;
  }

  public Users setStatus(String status) {
    this.status = status;
    return this;
  }
}

package org.dnyanyog.dto.users;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class UserResponse {

  private int responseCode;

  private String responseMsg;

  private UserData data;

  public int getResponseCode() {
    return responseCode;
  }

  public void setResponseCode(int responseCode) {
    this.responseCode = responseCode;
  }

  public String getResponseMsg() {
    return responseMsg;
  }

  public void setResponseMsg(String responseMsg) {
    this.responseMsg = responseMsg;
  }

  public UserData getData() {
    return data;
  }

  public void setData(UserData data) {
    this.data = data;
  }
}

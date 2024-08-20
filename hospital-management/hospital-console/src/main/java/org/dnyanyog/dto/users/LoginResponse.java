package org.dnyanyog.dto.users;

public class LoginResponse {

  private int responseCode;

  private String responseMsg;

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
}

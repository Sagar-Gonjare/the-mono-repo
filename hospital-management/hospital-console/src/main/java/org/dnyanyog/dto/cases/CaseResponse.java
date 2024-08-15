package org.dnyanyog.dto.cases;

public class CaseResponse {
  private int responseCode;

  private String responseMsg;

  private CaseData data;

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

  public CaseData getData() {
    return data;
  }

  public void setData(CaseData data) {
    this.data = data;
  }
}

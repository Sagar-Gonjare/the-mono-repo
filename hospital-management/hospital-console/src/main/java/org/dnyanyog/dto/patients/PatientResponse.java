package org.dnyanyog.dto.patients;

import java.util.List;

public class PatientResponse {
  private int responseCode;

  private String responseMsg;

  private PatientData data;

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

  public PatientData getData() {
    return data;
  }

  public void setData(PatientData data) {
    this.data = data;
  }
}

package or.dnyanyog.dto.response;

import org.springframework.stereotype.Component;

@Component
public class AppointmentResponse {
  private int responseCode;

  private String responseMsg;

  private AppointmentData data;

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

  public AppointmentData getData() {
    return data;
  }

  public void setData(AppointmentData data) {
    this.data = data;
  }
}

package org.dnyanyog.common;

public enum ApiEndPoints {
  DIRECTORY_BASE("http://localhost:8080/"),
  PATIENT_BASE("http://localhost:8080/"),
  APPOINTMENT_BASE("http://localhost:8080/"),
  CASES_BASE("http://localhost:8080/"),
	
	
	// case
  
  ADD_CASE("http://localhost:8080/case/api/v1/addCase"),
  UPDATE_CASE("http://localhost:8080/case/api/v1/updateCase"),
  DELETE_CASE("http://localhost:8080/case/api/v1/deleteCase/"),
  SERACH_CASE_BY_CASEID("http://localhost:8080/case/api/v1/getcaseIdById/"),
	
	// appointments 
	
	ADD_APPOINTMENT("http://localhost:8080/appointment/api/v1/addAppointment"),
	UPDATE_APPOINTMENTS ("http://localhost:8080/appointment/api/v1/updateAppointment"),
	GET_APPOINTMENT_BY_APPOINTENT_ID("http://localhost:8080/appointment/api/v1/getAppointmentByAppointment/"),
	DELETE_APPOINTMENT("http://localhost:8080/appointment/api/v1/delteAppointment/"),
	
	
	
	
	// directory 
	LOGIN("http://localhost:8080/api/v1/public/directory/userLogin"),
	ADD_USER("http://localhost:8080/api/directory/v1/addUser"),
	
	UPDATE_USER("http://localhost:8080/api/directory/v1/updateUser"),
	GET_USER_BY_USER_ID("http://localhost:8080/api/directory/v1/getUserByUserId/"),
	DELETE_USER("http://localhost:8080/api/directory/v1/delteUser/"),
	
	
	
	
	// patients
	ADD_PATIENT("http://localhost:8080/patient/api/v1/addPatient"),
	UPDATE_PATIENT("http://localhost:8080/patient/api/v1/updatePatient"),
	DELETEPATIENT("http://localhost:8080/patient/api/v1/deletePatient/"),
	GET_PATIENT_BY_PATIENT_ID("http://localhost:8080/patient/api/v1/getPatientById/")
	;

  private final String url;

  ApiEndPoints(String url) {
    this.url = url;
  }

  public String getUrl() {
    return url;
  }
}

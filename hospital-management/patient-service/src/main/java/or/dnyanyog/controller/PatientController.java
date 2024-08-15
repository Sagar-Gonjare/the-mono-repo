package or.dnyanyog.controller;

import java.util.List;
import or.dnyanyog.dto.request.PatientRequest;
import or.dnyanyog.dto.response.PatientResponse;
import or.dnyanyog.repository.PatientRepo;
import or.dnyanyog.service.PatientServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {
  @Autowired PatientServiceImpl service;
  @Autowired PatientResponse response;
  @Autowired PatientRepo repo;
  private static final Logger logger = Logger.getLogger(PatientController.class);

  @PostMapping(
      path = "patient/api/v1/addPatient",
      consumes = {"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public ResponseEntity<PatientResponse> addPatient(@RequestBody PatientRequest request) {

    logger.info("");
    return service.addPatient(request);
  }

  @PostMapping(
      path = "patient/api/v1/updatePatient",
      consumes = {"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public ResponseEntity<PatientResponse> updatePatient(@RequestBody PatientRequest request) {
    logger.info("");
    return service.updatePatiet(request);
  }

  @GetMapping(path = "patient/api/v1/getAllPatient")
  public ResponseEntity<List<PatientResponse>> getAllPatients() {

    logger.info("");
    return service.getAllPateins();
  }

  @GetMapping(path = "patient/api/v1/getPatientByName/{patientName}")
  public ResponseEntity<PatientResponse> getPatientByName(@PathVariable String pateintName) {
    logger.info("");
    return service.getPatientByPatientName(pateintName);
  }

  @GetMapping(path = "patient/api/v1/getPatientById/{patientId}")
  public ResponseEntity<PatientResponse> getPatientById(@PathVariable Long patientId) {
    logger.info("");
    PatientResponse patientResponse = service.getPateintsById(patientId);
    return ResponseEntity.status(patientResponse.getResponseCode()).body(patientResponse);
  }

  @DeleteMapping(path = "patient/api/v1/deletePatient/{patientId}")
  public ResponseEntity<PatientResponse> deletePatient(@PathVariable Long patientId) {
    logger.info("");
    PatientResponse patientResponse = service.deletePatient(patientId);
    return ResponseEntity.status(patientResponse.getResponseCode()).body(patientResponse);
  }
}

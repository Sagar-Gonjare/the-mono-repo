package or.dnyanyog.controller;

import java.util.List;
import or.dnyanyog.dto.request.AppointmentRequest;
import or.dnyanyog.dto.response.AppointmentResponse;
import or.dnyanyog.service.AppointmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppointmentController {

  @Autowired AppointmentServiceImpl service;

  @PostMapping(
      path = "appointment/api/v1/addAppointment",
      consumes = {"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public ResponseEntity<AppointmentResponse> addAppointment(
      @RequestBody AppointmentRequest request) {
    return service.addAppointments(request);
  }

  @PostMapping(
      path = "appointment/api/v1/updateAppointment",
      consumes = {"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public ResponseEntity<AppointmentResponse> updateAppointment(
      @RequestBody AppointmentRequest request) throws Exception {
    return service.updateUser(request);
  }

  @GetMapping(path = "appointment/api/v1/getAllAppointment")
  public ResponseEntity<List<AppointmentResponse>> getAllAppointments() {
    return service.getAllPatients();
  }

  @GetMapping(path = "appointment/api/v1/getAppointmentByAppointment/{appointmentId}")
  public ResponseEntity<AppointmentResponse> getAppointmentById(@PathVariable Long appointmentId) {
    AppointmentResponse response = service.getAppointmentByAppointmentID(appointmentId);
    return ResponseEntity.status(response.getResponseCode()).body(response);
  }

  @DeleteMapping(path = "appointment/api/v1/delteAppointment/{appointmentId}")
  public ResponseEntity<AppointmentResponse> deleteAppointment(@PathVariable Long appointmentId) {
    AppointmentResponse response = service.deleteAppointment(appointmentId);
    return ResponseEntity.status(response.getResponseCode()).body(response);
  }
}

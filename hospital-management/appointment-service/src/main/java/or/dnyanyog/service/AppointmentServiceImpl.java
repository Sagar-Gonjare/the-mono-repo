package or.dnyanyog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import or.dnyanyog.dto.request.AppointmentRequest;
import or.dnyanyog.dto.response.AppointmentData;
import or.dnyanyog.dto.response.AppointmentResponse;
import or.dnyanyog.entity.Appointments;
import or.dnyanyog.repository.AppointmentRepo;

@Component
@Service
public class AppointmentServiceImpl implements AppointmentService {

  @Autowired AppointmentResponse response;

  @Autowired Appointments appointments;

  @Autowired AppointmentData data;

  @Autowired AppointmentRepo repo;

  public ResponseEntity<AppointmentResponse> addAppointments(AppointmentRequest request) {

    if (repo.findByExaminationDateAndPatientName(
            request.getExaminationDate(), request.getPatientName())
        != null) {
      response = new AppointmentResponse();
      response.setResponseCode(HttpStatus.UNAUTHORIZED.value());
      response.setResponseMsg("Appointment Already exist");
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
    if (!(request.getAppointmentTime().isEmpty()
        && request.getExaminationDate().isEmpty()
        && request.getPatientName().isEmpty()
        && request.getPatientId().isEmpty())) {
      Appointments appointments =
          Appointments.getInstance()
              .setAppointmentTime(request.getAppointmentTime())
              .setExaminationDate(request.getExaminationDate())
              .setPatientId(request.getPatientId())
              .setPatientName(request.getPatientName());
      appointments = repo.save(appointments);

      response.setResponseCode(HttpStatus.CREATED.value());
      response.setResponseMsg("Appointment added successfully");

      AppointmentData data =
          AppointmentData.getInstance()
              .setAppointmentId(appointments.getAppointmentId())
              .setAppointmentTime(appointments.getAppointmentTime())
              .setExaminationDate(appointments.getExaminationDate())
              .setPatientId((appointments.getPatientId()))
              .setPatientName(appointments.getPatientName())
              .setStatus("Pending");
      response.setData(data);
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  public ResponseEntity<AppointmentResponse> updateUser(AppointmentRequest request)
      throws Exception {
    Optional<Appointments> appointment = repo.findById(request.getAppointmentId());

    if (appointment.isPresent()) {

      Appointments updateAppointment = appointment.get();
      if (request.getAppointmentTime() != null) {
        updateAppointment.setAppointmentTime(request.getAppointmentTime());
      }
      if (request.getExaminationDate() != null) {
        updateAppointment.setExaminationDate(request.getExaminationDate());
      }
      if (request.getPatientId() != null) {
        updateAppointment.setPatientId(request.getPatientId());
      }
      if (request.getPatientName() != null) {
        updateAppointment.setPatientName(request.getPatientName());
      }
      if (request.getStatus() != null) {
        updateAppointment.setStatus(request.getStatus());
      }

      AppointmentData appointmentData =
          AppointmentData.getInstance()
              .setPatientId(updateAppointment.getPatientId())
              .setAppointmentId(updateAppointment.getAppointmentId())
              .setAppointmentTime(updateAppointment.getAppointmentTime())
              .setExaminationDate(updateAppointment.getExaminationDate())
              .setPatientName(updateAppointment.getPatientName())
              .setStatus(updateAppointment.getStatus());

      repo.save(updateAppointment);

      response = new AppointmentResponse();
      response.setResponseCode(HttpStatus.CREATED.value());
      response.setResponseMsg("Appointment updated");
      response.setData(appointmentData);
      return ResponseEntity.status(HttpStatus.CREATED).body(response);
    } else {
      response = new AppointmentResponse();
      response.setResponseCode(HttpStatus.NOT_FOUND.value());
      response.setResponseMsg("Appointment not found");
      return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }
  }

  public AppointmentResponse deleteAppointment(Long appointmentId) {
    Optional<Appointments> user = repo.findByAppointmentId(appointmentId);
    response = new AppointmentResponse();
    if (user.isPresent()) {
      Appointments deletedAppointment = user.get();
      deletedAppointment.setStatus("Inactive");
      repo.saveAndFlush(deletedAppointment);
      response.setResponseCode(HttpStatus.OK.value());
      response.setResponseMsg("Appointment marked as Rejected");
    } else {
      response.setResponseCode(HttpStatus.NOT_FOUND.value());
      response.setResponseMsg("Appointment not found");
    }
    return response;
  }

  public AppointmentResponse getAppointmentByAppointmentID(Long appointmentId) {
    Optional<Appointments> appointment = repo.findById(appointmentId);
    if (appointment.isPresent()) {
      Appointments getAppointment = appointment.get();
      AppointmentData userData =
          AppointmentData.getInstance()
              .setPatientId(getAppointment.getPatientId())
              .setAppointmentId(getAppointment.getAppointmentId())
              .setAppointmentTime(getAppointment.getAppointmentTime())
              .setExaminationDate(getAppointment.getExaminationDate())
              .setPatientName(getAppointment.getPatientName())
              .setStatus(getAppointment.getStatus());
      response = new AppointmentResponse();
      response.setResponseCode(HttpStatus.FOUND.value());
      response.setResponseMsg("Appointment  found");
      response.setData(userData);
      return response;
    } else {
      response = new AppointmentResponse();
      response.setResponseCode(HttpStatus.NOT_FOUND.value());
      response.setResponseMsg("Appointment not found");
      return response;
    }
  }
  
  public ResponseEntity<List<AppointmentResponse>> getAllPatients() {
	    response = new AppointmentResponse();
	    List<Appointments> appointments = repo.findAll();

	    if (appointments != null) {
	      response.setResponseCode(HttpStatus.FOUND.value());
	      response.setResponseMsg("Appointments data found");
	    }
	    List<AppointmentResponse> appointmentList = new ArrayList<>();
	    for (Appointments appointment : appointments) {
	    	 AppointmentData appointmentData =
	    	          AppointmentData.getInstance()
	    	              .setPatientId(appointment.getPatientId())
	    	              .setAppointmentId(appointment.getAppointmentId())
	    	              .setAppointmentTime(appointment.getAppointmentTime())
	    	              .setExaminationDate(appointment.getExaminationDate())
	    	              .setPatientName(appointment.getPatientName())
	    	              .setStatus(appointment.getStatus());
	      appointmentList.add(response);
	      response.setData(appointmentData);
	    }
	    return ResponseEntity.status(HttpStatus.OK).body(appointmentList);
	  }
  
}

package or.dnyanyog.service;

import java.util.List;
import or.dnyanyog.dto.request.PatientRequest;
import or.dnyanyog.dto.response.PatientResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface PatientService {

  public ResponseEntity<PatientResponse> addPatient(PatientRequest request);

  public ResponseEntity<PatientResponse> updatePatiet(PatientRequest request);

  public PatientResponse deletePatient(Long patientId);

  public ResponseEntity<List<PatientResponse>> getAllPateins();

  public PatientResponse getPateintsById(Long pateintId);

  public ResponseEntity<PatientResponse> getPatientByPatientName(String patientName);
}

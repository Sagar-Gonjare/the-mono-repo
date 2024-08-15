package org.dnyanyog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;
import org.dnyanyog.dto.request.CaseRequest;
import org.dnyanyog.dto.response.CaseData;
import org.dnyanyog.dto.response.CaseResponse;
import org.dnyanyog.entity.Cases;
import org.dnyanyog.repository.CaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class CaseServiceImpl implements CaseService {
  @Autowired CaseResponse response;

  @Autowired CaseRepo repo;

  @Autowired Cases cases;
  private static final Logger logger = Logger.getLogger(CaseServiceImpl.class);

  public ResponseEntity<CaseResponse> addCase(CaseRequest request) throws Exception {

    response = new CaseResponse();

    if (repo.existsByCaseNumber(request.getCaseNo())) {
      CaseResponse response = new CaseResponse();
      response.setResponseCode(HttpStatus.CONFLICT.value());
      response.setResponseMsg("Case is already exist");
      logger.info("Case is already exist");
      return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    if (!(repo.existsByCaseNumber(request.getCaseNo()))) {
      response = new CaseResponse();
      // response.setData(new CaseData());
      Cases caseTable =
          Cases.getInsatnce()
              .setCaseNumber(request.getCaseNo())
              .setExaminationDate(request.getExaminationDate())
              .setPatientId(request.getPatientId())
              .setPatientName(request.getPatientName())
              .setPrescription(request.getPrescription())
              .setSymptoms(request.getSymptoms())
              .setStatus("Active");
      caseTable = repo.save(caseTable);

      response.setResponseCode(HttpStatus.CREATED.value());
      logger.info("Case record created successfully");
      response.setResponseMsg("Case record created successfully");

      CaseData caseData =
          CaseData.getInstance()
              .setCaseId(caseTable.getCaseId())
              .setPatientId(caseTable.getPatientId())
              .setCaseNo(caseTable.getCaseNo())
              .setExaminationDate(caseTable.getExaminationDate())
              .setPatientName(caseTable.getPatientName())
              .setPrescription(caseTable.getPrescription())
              .setSymptoms(caseTable.getSymptoms())
              .setStatus(caseTable.getStatus());
      response.setData(caseData);
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  public ResponseEntity<CaseResponse> updateCase(CaseRequest request) throws Exception {
    Optional<Cases> getCase = repo.findByCaseId(request.getCaseId());

    if (getCase.isPresent()) {

      Cases updateCases = getCase.get();

      if (request.getCaseNo() != null) {
        updateCases.setCaseNumber(request.getCaseNo());
      }
      if (request.getExaminationDate() != null) {
        updateCases.setExaminationDate(request.getExaminationDate());
      }
      if (request.getPatientId() != null) {
        updateCases.setPatientId(request.getPatientId());
      }
      if (request.getPatientName() != null) {
        updateCases.setPatientName(request.getPatientName());
      }
      if (request.getPrescription() != null) {
        updateCases.setPrescription(request.getPrescription());
      }
      if (request.getSymptoms() != null) {
        updateCases.setSymptoms(request.getSymptoms());
      }
      if (request.getStatus() != null) {
        updateCases.setStatus(request.getStatus());
      }

      CaseData caseData =
          CaseData.getInstance()
              .setCaseId(updateCases.getCaseId())
              .setPatientId(updateCases.getPatientId())
              .setCaseNo(updateCases.getCaseNo())
              .setExaminationDate(updateCases.getExaminationDate())
              .setPatientName(updateCases.getPatientName())
              .setPrescription(updateCases.getPrescription())
              .setSymptoms(updateCases.getSymptoms())
              .setStatus(updateCases.getStatus());
      repo.save(updateCases);

      response = new CaseResponse();
      response.setResponseCode(HttpStatus.CREATED.value());
      logger.info("Case updated");
      response.setResponseMsg("Case updated");
      response.setData(caseData);
      return ResponseEntity.status(HttpStatus.CREATED).body(response);
    } else {
      response = new CaseResponse();
      response.setResponseCode(HttpStatus.NOT_FOUND.value());
      response.setResponseMsg("Case not found");
      logger.info("Case not found");
      return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }
  }

  public CaseResponse deleteCase(Long caseId) {
    Optional<Cases> getCase = repo.findByCaseId(caseId);
    response = new CaseResponse();
    if (getCase.isPresent()) {
      Cases deletedCase = getCase.get();
      deletedCase.setStatus("Deleted");
      repo.saveAndFlush(deletedCase);
      response.setResponseCode(HttpStatus.OK.value());
      logger.info("Case marked as Deleted");
      response.setResponseMsg("Case marked as Deleted");
    } else {
      response.setResponseCode(HttpStatus.NOT_FOUND.value());
      logger.info("Case not found");
      response.setResponseMsg("Case not found");
    }
    return response;
  }

  public CaseResponse getCaseByCaseID(Long caseId) {
    Optional<Cases> caseGet = repo.findByCaseId(caseId);
    if (caseGet.isPresent()) {
      Cases getCase = caseGet.get();
      CaseData caseData =
          CaseData.getInstance()
              .setCaseId(getCase.getCaseId())
              .setPatientId(getCase.getPatientId())
              .setCaseNo(getCase.getCaseNo())
              .setExaminationDate(getCase.getExaminationDate())
              .setPatientName(getCase.getPatientName())
              .setPrescription(getCase.getPrescription())
              .setSymptoms(getCase.getSymptoms())
              .setStatus(getCase.getStatus());
      response = new CaseResponse();
      response.setResponseCode(HttpStatus.OK.value());
      logger.info("Case  found");
      response.setResponseMsg("Case  found");
      response.setData(caseData);
      return response;
    } else {
      response = new CaseResponse();
      response.setResponseCode(HttpStatus.OK.value());
      logger.info("Case not found");
      response.setResponseMsg("Case not found");
      return response;
    }
  }

  public ResponseEntity<List<CaseResponse>> getAllCases() {
    response = new CaseResponse();
    List<Cases> cases = repo.findAll();

    if (cases != null) {
      response.setResponseCode(HttpStatus.FOUND.value());
      logger.info("Cases data found");
      response.setResponseMsg("Cases data found");
    }
    List<CaseResponse> casesList = new ArrayList<>();
    for (Cases getCase : cases) {
      CaseData caseData =
          CaseData.getInstance()
              .setCaseId(getCase.getCaseId())
              .setPatientId(getCase.getPatientId())
              .setCaseNo(getCase.getCaseNo())
              .setExaminationDate(getCase.getExaminationDate())
              .setPatientName(getCase.getPatientName())
              .setPrescription(getCase.getPrescription())
              .setSymptoms(getCase.getSymptoms())
              .setStatus(getCase.getStatus());
      casesList.add(response);
      response.setData(caseData);
    }
    return ResponseEntity.status(HttpStatus.OK).body(casesList);
  }
}

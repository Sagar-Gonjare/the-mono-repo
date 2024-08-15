package org.dnyanyog.controller;

import java.util.List;
import org.apache.log4j.Logger;
import org.dnyanyog.dto.request.CaseRequest;
import org.dnyanyog.dto.response.CaseResponse;
import org.dnyanyog.repository.CaseRepo;
import org.dnyanyog.service.CaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CaseController {

  @Autowired CaseServiceImpl service;
  @Autowired CaseResponse response;
  @Autowired CaseRepo repo;

  private static final Logger logger = Logger.getLogger(CaseController.class);

  @PostMapping(
      path = "case/api/v1/addCase",
      consumes = {"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public ResponseEntity<CaseResponse> addCase(@RequestBody CaseRequest request) throws Exception {
    logger.info("Add Case");
    return service.addCase(request);
  }

  @PostMapping(
      path = "case/api/v1/updateCase",
      consumes = {"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public ResponseEntity<CaseResponse> updateCase(@RequestBody CaseRequest request)
      throws Exception {
    logger.info("update case");
    return service.updateCase(request);
  }

  @GetMapping(path = "case/api/v1/getAllCase")
  public ResponseEntity<List<CaseResponse>> getAllCases() {
    logger.info("get All cases");
    return service.getAllCases();
  }

  @GetMapping(path = "case/api/v1/getcaseIdById/{caseId}")
  public ResponseEntity<CaseResponse> getCaseByCaseID(@PathVariable Long caseId) {
    CaseResponse caseResponse = service.getCaseByCaseID(caseId);
    logger.info("getCaseByCaseID");
    return ResponseEntity.status(caseResponse.getResponseCode()).body(caseResponse);
  }

  @DeleteMapping(path = "case/api/v1/deleteCase/{caseId}")
  public ResponseEntity<CaseResponse> deleteCase(@PathVariable Long caseId) {
    CaseResponse caseResponse = service.deleteCase(caseId);
    logger.info("delete Cases");
    return ResponseEntity.status(caseResponse.getResponseCode()).body(caseResponse);
  }
}

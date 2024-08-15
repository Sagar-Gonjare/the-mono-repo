package org.dnyanyog.service;

import java.util.List;
import org.dnyanyog.dto.request.CaseRequest;
import org.dnyanyog.dto.response.CaseResponse;
import org.springframework.http.ResponseEntity;

public interface CaseService {

  public ResponseEntity<CaseResponse> addCase(CaseRequest request) throws Exception;

  public ResponseEntity<CaseResponse> updateCase(CaseRequest request) throws Exception;

  public CaseResponse deleteCase(Long caseId);

  public CaseResponse getCaseByCaseID(Long caseId);

  public ResponseEntity<List<CaseResponse>> getAllCases();
}

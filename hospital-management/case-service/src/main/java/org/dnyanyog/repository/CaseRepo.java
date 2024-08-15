package org.dnyanyog.repository;

import java.util.Optional;
import org.dnyanyog.entity.Cases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface CaseRepo extends JpaRepository<Cases, Long> {
  Optional<Cases> findByCaseId(Long caseId);

  Optional<Cases> findByCaseNumber(String caseNo);

  boolean existsByCaseNumber(String patientId);
}

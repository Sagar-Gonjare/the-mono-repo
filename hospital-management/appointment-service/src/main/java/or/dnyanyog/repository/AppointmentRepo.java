package or.dnyanyog.repository;

import java.util.Optional;
import or.dnyanyog.entity.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface AppointmentRepo extends JpaRepository<Appointments, Long> {
  Optional<Appointments> findByAppointmentId(Long appointmentId);

  Appointments findByExaminationDateAndPatientName(String date, String name);
}

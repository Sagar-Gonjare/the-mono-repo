package or.dnyanyog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import or.dnyanyog.entity.Appointments;


@Component
@Repository
public interface AppointmentRepo extends JpaRepository<Appointments,Long>{
	Optional<Appointments> findByAppointmentId(Long appointmentId );
	
	Appointments findByExaminationDateAndPatientName(String date,String name);
}


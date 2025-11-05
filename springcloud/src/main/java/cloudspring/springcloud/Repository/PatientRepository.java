package cloudspring.springcloud.Repository;

import cloudspring.springcloud.Model.PatientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public  interface  PatientRepository extends JpaRepository<PatientModel, UUID> {
    boolean existsByEmail(String email);
    boolean existsByemailAndIdNot(String email, UUID id);
}

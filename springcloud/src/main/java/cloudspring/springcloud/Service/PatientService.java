package cloudspring.springcloud.Service;

import cloudspring.springcloud.DTO.PatientResponseDto;
import cloudspring.springcloud.Model.PatientModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private PatientService patientRepository;

    public PatientService(PatientService patientRepository){
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDto> getPatientModel(){
        List<PatientModel> patientModel = patientRepository.findAll();

        List<PatientResponseDto> patientResponseDtos = patientModel.stream()
                .map(Pa)
    }
}

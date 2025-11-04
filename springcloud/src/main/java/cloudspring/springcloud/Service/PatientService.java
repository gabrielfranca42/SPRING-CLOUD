package cloudspring.springcloud.Service;

import cloudspring.springcloud.DTO.PatientRequestDto;
import cloudspring.springcloud.DTO.PatientResponseDto;
import cloudspring.springcloud.Mapper.PatientMapper;
import cloudspring.springcloud.Model.PatientModel;
import cloudspring.springcloud.Repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDto> getPatientModel(){
        List<PatientModel> patientModel = patientRepository.findAll();

        return patientModel.stream()
                .map(PatientMapper::toDTO).toList();



    }

    public PatientResponseDto   createPatient(PatientRequestDto patientRequestDto){
        PatientModel newPatientModel = patientRepository.save(
                PatientMapper.toModel(patientRequestDto));

        return PatientMapper.toDTO(newPatientModel);
    }

}

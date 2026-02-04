package cloudspring.springcloud.Service;

import cloudspring.springcloud.DTO.PatientRequestDto;
import cloudspring.springcloud.DTO.PatientResponseDto;
import cloudspring.springcloud.Exception.EmailAlreadyExistsException;
import cloudspring.springcloud.Exception.PatientNotFoundException;
import cloudspring.springcloud.Mapper.PatientMapper;
import cloudspring.springcloud.Model.PatientModel;
import cloudspring.springcloud.Repository.PatientRepository;
import cloudspring.springcloud.grpc.BillingServiceGrpcClient;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private PatientRepository patientRepository;
    private final BillingServiceGrpcClient billingServiceGrpcClient;

    public PatientService(PatientRepository patientRepository, BillingServiceGrpcClient billingServiceGrpcClient){
        this.patientRepository = patientRepository;
        this.billingServiceGrpcClient =billingServiceGrpcClient;
    }

    public List<PatientResponseDto> getPatientModel(){
        List<PatientModel> patientModel = patientRepository.findAll();

        return patientModel.stream()
                .map(PatientMapper::toDTO).toList();



    }

    public PatientResponseDto   createPatient(PatientRequestDto patientRequestDto){
        if(patientRepository.existsByEmail(patientRequestDto.getEmail())){
            throw new EmailAlreadyExistsException("A patient with this email " +
                     " already exists" + patientRequestDto.getEmail());

        }


        PatientModel newPatientModel = patientRepository.save(
                PatientMapper.toModel(patientRequestDto));

        billingServiceGrpcClient.createBillingAccount(newPatientModel.getId().toString(), newPatientModel.getName(),newPatientModel.getEmail());


        return PatientMapper.toDTO(newPatientModel);
    }

    public PatientResponseDto updatePatient(UUID id,PatientRequestDto patientRequestDto) {

        PatientModel patientModel = patientRepository.findById(id).orElseThrow(
                () -> new PatientNotFoundException("Patient not foud with ID:", id));

        if (patientRepository.existsByemailAndIdNot(patientRequestDto.getEmail(),id)) {
            throw new EmailAlreadyExistsException("A patient with this email " +
                    " already exists" + patientRequestDto.getEmail());
        }

        patientModel.setName(patientRequestDto.getName());
        patientModel.setAddress(patientRequestDto.getAddress());
        patientModel.setEmail(patientRequestDto.getEmail());
        patientModel.setDateOfBirth(LocalDate.parse(patientRequestDto.getDateOfBirth()));

        PatientModel updatedPatientModel = patientRepository.save(patientModel);
        return PatientMapper.toDTO(updatedPatientModel);

    }

    public void deletePatientModel(UUID id) {

        PatientModel patient = patientRepository.findById(id).orElseThrow(
                () -> new PatientNotFoundException("Patient not found with ID:", id)
        );

            patientRepository.delete(patient);
    }
}

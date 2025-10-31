package cloudspring.springcloud.Mapper;

import cloudspring.springcloud.DTO.PatientResponseDto;
import cloudspring.springcloud.Model.PatientModel;

public class PatientMapper {
    public static PatientResponseDto toDTO(PatientModel patientModel){
        PatientResponseDto patientDto = new PatientResponseDto();
        patientDto.setId(patientModel.getId().toString());
        patientDto.setName(patientModel.getName());
        patientDto.setAddress(patientModel.getAddress());
        patientDto.setEmail(patientModel.getEmail());
        patientDto.setDateOfBirth(patientModel.getDateOfBirth().toString());
        return patientDto;



    }
}

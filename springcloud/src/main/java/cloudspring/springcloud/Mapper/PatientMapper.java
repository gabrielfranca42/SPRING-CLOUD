package cloudspring.springcloud.Mapper;

import cloudspring.springcloud.DTO.PatientRequestDto;
import cloudspring.springcloud.DTO.PatientResponseDto;
import cloudspring.springcloud.Model.PatientModel;

import java.time.LocalDate;

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

    public static PatientModel toModel(PatientRequestDto patientRequestDto){
        PatientModel patientModel = new PatientModel();
        patientModel.setName(patientRequestDto.getName());
        patientModel.setAddress(patientRequestDto.getAddress());
        patientModel.setEmail(patientRequestDto.getEmail());
        patientModel.setDateOfBirth(patientRequestDto.getDateOfBirth());
        patientModel.setRegisterDate(LocalDate.parse(patientRequestDto.getRegisteredDate()));
        return patientModel;
    }

}

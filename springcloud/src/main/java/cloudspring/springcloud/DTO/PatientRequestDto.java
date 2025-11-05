package cloudspring.springcloud.DTO;

import cloudspring.springcloud.DTO.Validators.CreatedPatientValidationGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class PatientRequestDto {
    public PatientRequestDto(String name) {
        this.name = name;
    }

    @NotBlank(message = "Name is required")
    @Size(max = 100,message = "Name cannot exceed 100 characters")
    private String name;

    @NotBlank
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Date of birth is required")
    private String registeredDate;

    @NotBlank(groups = CreatedPatientValidationGroup.class, message = "Date of birth is required")
    private String dateOfBirth;

    public PatientRequestDto(String name, String email, String address, String registeredDate,String dateOfBirth) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.registeredDate = registeredDate;
        this.dateOfBirth = dateOfBirth;
    }


    public PatientRequestDto(){

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}

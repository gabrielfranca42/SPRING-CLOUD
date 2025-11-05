package cloudspring.springcloud.Controller;

import cloudspring.springcloud.DTO.PatientRequestDto;
import cloudspring.springcloud.DTO.PatientResponseDto;
import cloudspring.springcloud.DTO.Validators.CreatedPatientValidationGroup;
import cloudspring.springcloud.Service.PatientService;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.Builder;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;



@RestController
@RequestMapping("/patients")
public class PatientController {
    private PatientService patientService;

    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDto>> getPatients(){
        List<PatientResponseDto> patientsModel = patientService.getPatientModel();
        return ResponseEntity.ok().body(patientsModel);
    }

    @PostMapping
    public ResponseEntity<PatientResponseDto> createPatientModel(@Validated({Default.class, CreatedPatientValidationGroup.class})@RequestBody PatientRequestDto patientRequestDto){
      PatientResponseDto patientResponseDto = patientService.createPatient(patientRequestDto);

      return ResponseEntity.ok().body(patientResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDto> updatePatientModel(@PathVariable UUID id, @Validated({Builder.Default.class}) @RequestBody PatientRequestDto patientRequestDto){

        PatientResponseDto patientResponseDto = patientService.updatePatient(id,
                patientRequestDto);

        return ResponseEntity.ok().body(patientResponseDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatientModel(@PathVariable UUID id){
        patientService.deletePatientModel(id);
        return ResponseEntity.noContent().build();
    }
}

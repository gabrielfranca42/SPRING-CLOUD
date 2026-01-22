package cloudspring.springcloud.Controller;

import cloudspring.springcloud.DTO.PatientRequestDto;
import cloudspring.springcloud.DTO.PatientResponseDto;
import cloudspring.springcloud.DTO.Validators.CreatedPatientValidationGroup;
import cloudspring.springcloud.Service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

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
@Tag(name = "Patient", description = "API PARA MANEJO DE PARTIENTS")
public class PatientController {
    private PatientService patientService;

    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    @GetMapping
    @Operation(summary = "Get Patients")
    public ResponseEntity<List<PatientResponseDto>> getPatients(){
        List<PatientResponseDto> patientsModel = patientService.getPatientModel();
        return ResponseEntity.ok().body(patientsModel);
    }

    @PostMapping
    @Operation(summary = "Create a new Patient")
    public ResponseEntity<PatientResponseDto> createPatientModel(@Validated({Default.class, CreatedPatientValidationGroup.class})@RequestBody PatientRequestDto patientRequestDto){
      PatientResponseDto patientResponseDto = patientService.createPatient(patientRequestDto);

      return ResponseEntity.ok().body(patientResponseDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update Patient")
    public ResponseEntity<PatientResponseDto> updatePatientModel(@PathVariable UUID id, @Validated({Builder.Default.class}) @RequestBody PatientRequestDto patientRequestDto){

        PatientResponseDto patientResponseDto = patientService.updatePatient(id,
                patientRequestDto);

        return ResponseEntity.ok().body(patientResponseDto);

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete Patient")
    public ResponseEntity<Void> deletePatientModel(@PathVariable UUID id){
        patientService.deletePatientModel(id);
        return ResponseEntity.noContent().build();
    }
}

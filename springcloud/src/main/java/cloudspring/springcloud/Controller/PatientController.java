package cloudspring.springcloud.Controller;

import cloudspring.springcloud.DTO.PatientRequestDto;
import cloudspring.springcloud.DTO.PatientResponseDto;
import cloudspring.springcloud.Service.PatientService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<PatientResponseDto> createPatientModel(@Valid @RequestBody PatientRequestDto patientRequestDto){
      PatientResponseDto patientResponseDto = patientService.createPatient(patientRequestDto);

      return ResponseEntity.ok().body(patientResponseDto);
    }
}

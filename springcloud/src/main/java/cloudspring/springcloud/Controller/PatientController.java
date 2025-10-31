package cloudspring.springcloud.Controller;

import cloudspring.springcloud.DTO.PatientResponseDto;
import cloudspring.springcloud.Service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

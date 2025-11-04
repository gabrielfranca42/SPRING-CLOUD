package cloudspring.springcloud.Exception;

// Modificado: Alterado o import de LoggerFactory para org.slf4j.LoggerFactory, pois o LoggerFactory do Hibernate Validator não possui o método getLogger compatível.
// Também alterado o import de Logger para org.slf4j.Logger para usar SLF4J, que é padrão no Spring e resolve o erro de resolução do método.
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
// A importação 'MethodValidationException' foi removida por não ser mais necessária
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(
            GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handlerValidationException(MethodArgumentNotValidException ex){

        Map<String, String> errors = new HashMap<>();


        ex.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(),error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String ,String>> handleEmailAlreadyExistsException(
            EmailAlreadyExistsException ex) {

        // Modificado: Alterado log.warning para log.warn, removido o placeholder "{}" pois não há parâmetro, e ajustado a mensagem para "exists" (correção de typo de "exist" para "exists").
        log.warn("Email address already exists");
        Map<String , String> errors = new HashMap<>();
        errors.put("message", "Email address already exists");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlePatientNotFoundException(
            PatientNotFoundException ex){
        log.warn("Pantient not foud {}", ex.getMessage());

        Map<String, String> errors =new HashMap<>();
        errors.put("message", "Patient not foud");
        return  ResponseEntity.badRequest().body(errors);
    }
}

package br.com.ideencep.ideenCep.util;


import br.com.ideencep.ideenCep.exceptions.MethodArgumentNotValidException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratandoErro {

    // Classe de exceção para erro 400 (Bad Request)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    // Classe de exceção para erro 404 (Not Found)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> tratarErro400(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    // Classe de exceção para erro 500 (Internal Server Error)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> tratarErro500(RuntimeException ex) {
        return ResponseEntity.internalServerError().body(ex.getMessage());
    }

}

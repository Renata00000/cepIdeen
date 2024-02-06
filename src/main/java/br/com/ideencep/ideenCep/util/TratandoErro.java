package br.com.ideencep.ideenCep.util;


import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Service
public class TratandoErro {

    // Classe de exceção para erro 400 (Bad Request)
    public static class BadRequestException extends RuntimeException {
        public BadRequestException(String message) {
            super(message);
        }
    }

    // Classe de exceção para erro 404 (Not Found)
    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    // Classe de exceção para erro 500 (Internal Server Error)
    public static class InternalServerErrorException extends RuntimeException {
        public InternalServerErrorException(String message) {
            super(message);
        }
    }

}

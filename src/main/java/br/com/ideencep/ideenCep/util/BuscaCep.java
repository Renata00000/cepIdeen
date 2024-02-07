package br.com.ideencep.ideenCep.util;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

import br.com.ideencep.ideenCep.exceptions.MethodArgumentNotValidException;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;




@Service
public class BuscaCep {
    private static final Logger logger = Logger.getLogger(BuscaCep.class.getName());

    public String buscaEnderecoPorCep(String cep) throws Exception {
        URI uri = URI.create("http://viacep.com.br/ws/" + cep + "/json/");
        return sendRequest(uri);
    }

    private String sendRequest(URI uri) throws Exception {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder().uri(uri);
        HttpRequest request = requestBuilder.build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        if(response == null || response.statusCode() == 500){
            logger.severe("Erro ao consultar ViaCep");
            throw new RuntimeException("Erro interno do servidor ao consultar ViaCep");
        }
        // Verificar o status da resposta
        int statusCode = response.statusCode();
        if (statusCode == 200 && response.body().contains("\"erro\": true")) {
            // Lançar uma exceção indicando que o CEP não foi encontrado
            throw new EntityNotFoundException();
        }
        if (statusCode == 400) {
            // Lançar uma exceção indicando que o formato do CEP é inválido
            throw new BadRequestException("CEP inválido, verifique");
        }

        return response.body();
    }
}

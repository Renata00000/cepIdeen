package br.com.ideencep.ideenCep.util;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;




@Service
public class BuscaCep {
    private static final Logger logger = Logger.getLogger(BuscaCep.class.getName());

    public String buscaEnderecoPorCep(String cep) {
        URI uri = URI.create("http://viacep.com.br/ws/" + cep + "/json/");
        return sendRequest(uri);
    }

    private String sendRequest(URI uri) {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder().uri(uri);
        HttpRequest request = requestBuilder.build();

        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            // Verificar o status da resposta
            int statusCode = response.statusCode();
            if (statusCode == 400) {
                // Lançar uma exceção indicando que o formato do CEP é inválido
                throw new TratandoErro.BadRequestException("CEP inválido, verifique");
            }

            if (response.body().contains("\"erro\": true")) {
                // Lançar uma exceção indicando que o CEP não foi encontrado
                throw new TratandoErro.ResourceNotFoundException("CEP não encontrado");
            }

            return response.body();
        } catch (IOException | InterruptedException ex) {
            logger.severe("Erro ao consultar ViaCep: " + ex.getMessage());
            // Lançar exceção de erro interno do servidor
            throw new TratandoErro.InternalServerErrorException("Erro interno do servidor ao consultar ViaCep");
        }
    }
}

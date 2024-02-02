package br.com.ideencep.ideenCep.util;

import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class BuscaCep {
    public String buscaEnderecoPorCep(String cep) {
        URI uri = URI.create("http://viacep.com.br/ws/"+cep+"/json/");
        return this.sendRequest(uri);
    }

    private String sendRequest(URI uri) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .build();

        try {
            HttpResponse<String> response = HttpClient
                    .newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            return response.body();  // Adicione esta linha para retornar o corpo da resposta
        } catch (Exception ex) {
            throw new RuntimeException("Endereco nao localizado, verifique o CEP digitado");
        }
    }
}





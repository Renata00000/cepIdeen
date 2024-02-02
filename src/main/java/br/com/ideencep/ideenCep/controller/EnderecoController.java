package br.com.ideencep.ideenCep.controller;
import br.com.ideencep.ideenCep.util.BuscaCep;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/endereco")
public class EnderecoController {


    @GetMapping(value = "/{cep}", produces = "application/json")
    public String buscaPorCep(@PathVariable(name = "cep") String cep){
        BuscaCep consultaCep = new BuscaCep();
        return consultaCep.buscaEnderecoPorCep(cep);
    }
}


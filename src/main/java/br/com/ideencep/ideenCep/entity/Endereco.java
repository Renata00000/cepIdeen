package br.com.ideencep.ideenCep.entity;


import jakarta.persistence.Entity;

@Entity
public class Endereco {
    private long id;
    private String cep;
    private String tipoCep;
    private String subTipoCep;
    private String uf;
    private String cidade;
    private String bairro;
    private String endereco;
    private String complemeto;
    private String codigoIBGE;
}


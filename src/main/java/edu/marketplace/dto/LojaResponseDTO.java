package edu.marketplace.dto;

import lombok.Data;

@Data
public class LojaResponseDTO {
    private int id;
    private String nome;
    private String cnpj;
    private String nomeProprietario;
}
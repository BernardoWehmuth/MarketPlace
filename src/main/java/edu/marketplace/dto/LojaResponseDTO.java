package edu.marketplace.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LojaResponseDTO {
    private int id;
    private String nome;
    private String cnpj;
    private String nomeProprietario;
}
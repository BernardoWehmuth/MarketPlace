package edu.marketplace.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponseDTO {
    private int id;
    private String nome;
    private String descricao;
}

package edu.marketplace.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequestDTO {
    @NotBlank(message = "O nome do item é obrigatório")
    private String nome;

    @NotBlank(message = "A descricao é obrigatória")
    private String descricao;
}

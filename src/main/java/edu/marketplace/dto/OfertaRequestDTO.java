package edu.marketplace.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfertaRequestDTO {
    @NotBlank(message = "O id da loja é obrigatório")
    private int lojaId;
    @NotBlank(message = "O id do item é obrigatório")
    private int itemId;
    @NotBlank(message = "O preco da oferta é obrigatório")
    private double preco;
    @NotBlank(message = "A quantidade do item é obrigatória")
    private int quantidade;
}
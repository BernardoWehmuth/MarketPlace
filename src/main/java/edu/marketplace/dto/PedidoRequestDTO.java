package edu.marketplace.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoRequestDTO {
    @NotNull(message = "O id do comprador é obrigatório")
    private int compradorId;

    @NotEmpty(message = "O pedido precisa conter as ofertas")
    private List<PedidoOfertaDTO> ofertas;
}

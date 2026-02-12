package edu.marketplace.dto;

import java.util.List;

import lombok.Data;

@Data
public class PedidoRequestDTO {
    private int compradorId;
    private List<PedidoOfertaDTO> ofertas;
}

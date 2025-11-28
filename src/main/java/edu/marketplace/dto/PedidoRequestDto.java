package edu.marketplace.dto;

import java.util.List;

import lombok.Data;

@Data
public class PedidoRequestDto {
    private int compradorId;
    private List<PedidoOfertaDto> ofertas;
}
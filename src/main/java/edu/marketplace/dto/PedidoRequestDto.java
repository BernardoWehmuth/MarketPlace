package edu.marketplace.dto;

import lombok.Data;

@Data
public class PedidoRequestDto {
    private int compradorId;
    private Iterable<Integer> ofertaIds;
}
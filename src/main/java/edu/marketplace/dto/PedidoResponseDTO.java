package edu.marketplace.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class PedidoResponseDTO {
	private int id;
	private LocalDateTime dataPedido;
	private double valorTotal;
	
	private UsuarioResponseDTO comprador;
	private LojaResponseDTO loja;
	private List<OfertaResponseDTO> itens;
}

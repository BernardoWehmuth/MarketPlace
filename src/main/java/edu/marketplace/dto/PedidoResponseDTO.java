package edu.marketplace.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoResponseDTO {
	private int id;
	private LocalDateTime dataPedido;
	private double valorTotal;
	
	private UsuarioResponseDTO comprador;
	private LojaResponseDTO loja;
	private List<PedidoOfertaResponseDTO> itens;
}

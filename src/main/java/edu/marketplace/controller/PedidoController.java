package edu.marketplace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.marketplace.dto.PedidoRequestDTO;
import edu.marketplace.dto.PedidoResponseDTO;
import edu.marketplace.service.PedidoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/marketplace/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> realizarPedido(@RequestBody @Valid PedidoRequestDTO request) {
        PedidoResponseDTO pedido = pedidoService.realizarPedido(request);
        return ResponseEntity.ok(pedido);
    }

    @GetMapping
    public ResponseEntity<?> listarPedidos() {
        return ResponseEntity.ok(pedidoService.listarPedidos());
    }
    @DeleteMapping("{pedidoId}")
    public ResponseEntity<?> excluirPedido(@PathVariable int pedidoId){
    	try {
    		pedidoService.excluirPedido(pedidoId);
    		return ResponseEntity.ok().body("O pedido de id " + pedidoId + " foi excluido com sucesso");
    	}catch(Exception e) {
    		return ResponseEntity.badRequest().body(e.getMessage());
    	}
    }
}

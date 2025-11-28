package edu.marketplace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.marketplace.dto.PedidoRequestDto;
import edu.marketplace.models.PedidoModel;
import edu.marketplace.service.PedidoService;

@RestController
@RequestMapping("/marketplace/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoModel> realizarPedido(@RequestBody PedidoRequestDto request) {
        PedidoModel pedido = pedidoService.realizarPedido(request.getOfertas(), request.getCompradorId());
        return ResponseEntity.ok(pedido);
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarPedidos() {
        return ResponseEntity.ok(pedidoService.listarPedidos());
    }
    
}

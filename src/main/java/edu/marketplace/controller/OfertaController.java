package edu.marketplace.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.marketplace.dto.OfertaResponseDTO;
import edu.marketplace.models.OfertaModel;
import edu.marketplace.service.OfertaService;

@RestController
@RequestMapping("marketplace/ofertas")
public class OfertaController {

    @Autowired
    private OfertaService ofertaService;

    @PostMapping("/criar")
    public ResponseEntity<?> criarOferta(
            @RequestParam int lojaId,
            @RequestParam int itemId,
            @RequestParam double preco,
            @RequestParam int quantidade) {

        try {
            OfertaModel nova = ofertaService.criarOferta(lojaId, itemId, preco, quantidade);
            return ResponseEntity.ok(nova);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<OfertaResponseDTO>> listarTodas() {
        return ResponseEntity.ok(ofertaService.listarTodasOfertas());
    }
    
    @PutMapping("/atualizarOferta/{id}")
    public ResponseEntity<?> atualizarOferta(
            @PathVariable int id,
            @RequestParam double preco,
            @RequestParam int quantidade) {
        try {
            return ResponseEntity.ok(ofertaService.atualizarOferta(id, preco, quantidade));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/atualizarQuantidade/{ofertaId}")
    public ResponseEntity<?> adicionarQuantidade(@PathVariable int ofertaId, @RequestParam int quantidadeAdicionada) {
    	return ResponseEntity.ok(ofertaService.adicionarQuantidade(ofertaId, quantidadeAdicionada));
    }
}

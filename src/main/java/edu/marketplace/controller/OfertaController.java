package edu.marketplace.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.marketplace.dto.OfertaRequestDTO;
import edu.marketplace.dto.OfertaResponseDTO;
import edu.marketplace.service.OfertaService;

@RestController
@RequestMapping("marketplace/ofertas")
public class OfertaController {

    @Autowired
    private OfertaService ofertaService;

    @PostMapping
    public ResponseEntity<?> criarOferta(@RequestBody OfertaRequestDTO ofertaRequestDto) {
        try {
            OfertaResponseDTO nova = ofertaService.criarOferta(ofertaRequestDto);
            return ResponseEntity.ok(nova);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<OfertaResponseDTO>> listarTodas() {
        return ResponseEntity.ok(ofertaService.listarTodasOfertas());
    }
    
    @PutMapping("/{ofertaId}")
    public ResponseEntity<?> atualizarOferta(
            @PathVariable int ofertaId,
            @RequestParam double preco,
            @RequestParam int quantidade) {
        try {
            return ResponseEntity.ok(ofertaService.atualizarOferta(ofertaId, preco, quantidade));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/adicionarQuantidade/{ofertaId}")
    public ResponseEntity<?> adicionarQuantidade(@PathVariable int ofertaId, @RequestParam int quantidadeAdicionada) {
    	return ResponseEntity.ok(ofertaService.adicionarQuantidade(ofertaId, quantidadeAdicionada));
    }
}

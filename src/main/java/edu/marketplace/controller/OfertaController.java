package edu.marketplace.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/todas")
    public ResponseEntity<List<OfertaModel>> listarTodas() {
        return ResponseEntity.ok(ofertaService.listarTodasOfertas());
    }

    @GetMapping("/loja/{lojaId}")
    public ResponseEntity<?> listarPorLoja(@PathVariable int lojaId) {
        try {
            return ResponseEntity.ok(ofertaService.listarOfertasDaLoja(lojaId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/atualizar/{id}")
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

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluirOferta(@PathVariable int id) {
        try {
            ofertaService.excluirOferta(id);
            return ResponseEntity.ok("Oferta removida com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

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

    @PostMapping("/criarOferta/loja/{lojaId}/item/{itemId}")
    public ResponseEntity<?> criarOferta(
            @PathVariable int lojaId,
            @PathVariable int itemId,
            @RequestParam double preco,
            @RequestParam int quantidade) {

        try {
            OfertaModel nova = ofertaService.criarOferta(lojaId, itemId, preco, quantidade);
            return ResponseEntity.ok(nova);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listarOfertas")
    public ResponseEntity<List<OfertaModel>> listarTodas() {
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

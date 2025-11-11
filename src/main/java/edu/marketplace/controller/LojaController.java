package edu.marketplace.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.marketplace.models.LojaModel;
import edu.marketplace.service.LojaService;
import edu.marketplace.service.OfertaService;


@RestController
@RequestMapping("marketplace/lojas")
public class LojaController {
	@Autowired
	private LojaService lojaService;
	
	@Autowired
	private OfertaService ofertaService;
	
	@GetMapping("/listarLojas")
	public ResponseEntity<List<LojaModel>> listarLojas(){
		return ResponseEntity.ok(lojaService.listarLojas());
	}
	
	@PostMapping("/criarLoja/{usuarioId}")
	public ResponseEntity<?> criarLoja(@PathVariable int usuarioId, @RequestBody LojaModel novaLoja) {
	    try {
	        LojaModel loja = lojaService.criarLoja(usuarioId, novaLoja);
	        return ResponseEntity.ok(loja);
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}
	
	@PutMapping("/anexarProduto/{lojaId}/{itemId}")
	public ResponseEntity<?> anexarProduto(@PathVariable int lojaId, @PathVariable int itemId){
		
		return ResponseEntity.ok(anexarProduto(itemId, lojaId));
	}
	
	@GetMapping("/loja/{lojaId}")
    public ResponseEntity<?> listarPorLoja(@PathVariable int lojaId) {
        try {
            return ResponseEntity.ok(ofertaService.listarOfertasDaLoja(lojaId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

package edu.marketplace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.marketplace.dto.LojaRequestDTO;
import edu.marketplace.dto.LojaResponseDTO;
import edu.marketplace.service.LojaService;


@RestController
@RequestMapping("marketplace/lojas")
public class LojaController {
	@Autowired
	private LojaService lojaService;
	
	@PostMapping("/criar/{usuarioId}")
	public ResponseEntity<?> criarLoja(@PathVariable int usuarioId, @RequestBody LojaRequestDTO novaLoja) {
	    try {
	        LojaResponseDTO loja = lojaService.criarLoja(usuarioId, novaLoja);
	        return ResponseEntity.ok(loja);
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}
	
	@GetMapping("/listar")
	public ResponseEntity<?> listarLojas(){
		return ResponseEntity.ok(lojaService.listarLojas());
	}
	
	
}

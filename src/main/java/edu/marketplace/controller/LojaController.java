package edu.marketplace.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.marketplace.models.LojaModel;
import edu.marketplace.models.UsuarioModel;
import edu.marketplace.repositorys.LojaRepository;
import edu.marketplace.repositorys.UsuarioRepository;
import edu.marketplace.service.LojaService;


@RestController
@RequestMapping("marketplace/lojas")
public class LojaController {
	@Autowired
	private LojaService lojaService;
	
	@Autowired
	private LojaRepository lojaRepository;
	
	@Autowired 
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/listarLojas")
	public ResponseEntity<List<LojaModel>> listarLojas(){
		return ResponseEntity.ok(lojaRepository.findAll());
	}
	
	@PostMapping("/criarLoja/{usuarioId}")
	public ResponseEntity<?> criarLoja(@PathVariable int usuarioId, @RequestBody LojaModel novaLoja){
		try {
			UsuarioModel proprietario = usuarioRepository.findById(usuarioId)
	                .orElse(null);
			
			novaLoja.setProprietario(proprietario);
			LojaModel loja = lojaService.criarLoja(novaLoja);
			if(proprietario == null) {
				return ResponseEntity.status(404).body("Esse usuário não Existe");
			}
			loja.setProprietario(proprietario);
			return ResponseEntity.ok(loja);
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}

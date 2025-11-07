package edu.marketplace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.marketplace.models.UsuarioModel;
import edu.marketplace.repositorys.UsuarioRepository;
import edu.marketplace.service.UsuarioService;

@RestController
@RequestMapping("marketplace/usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/listarUsuarios")
	public ResponseEntity<?> listarUsuarios(){
		return ResponseEntity.ok(usuarioRepository.findAll());
	}
	
	@PostMapping("/criarUsuario")
	public ResponseEntity<?> criarUsuario(@RequestBody UsuarioModel novoUsuario) {
		try {
			UsuarioModel usuario = usuarioService.criarUsuario(novoUsuario);
			return ResponseEntity.ok(usuario);
		} catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
}

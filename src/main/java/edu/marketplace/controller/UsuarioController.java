package edu.marketplace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.marketplace.dto.UsuarioRequestDTO;
import edu.marketplace.dto.UsuarioResponseDTO;
import edu.marketplace.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("marketplace/usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<?> listarUsuarios(){
		return ResponseEntity.ok(usuarioService.listarUsuarios());
	}
	
	@PostMapping
	public ResponseEntity<?> criarUsuario(@RequestBody @Valid UsuarioRequestDTO novoUsuario) {
		try {
			UsuarioResponseDTO usuario = usuarioService.criarUsuario(novoUsuario);
			return ResponseEntity.ok(usuario);
		} catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}

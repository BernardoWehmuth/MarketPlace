package edu.marketplace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.marketplace.models.UsuarioModel;
import edu.marketplace.repositorys.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<UsuarioModel> listarUsuarios(){
		return usuarioRepository.findAll();
	}
	
	public UsuarioModel criarUsuario(UsuarioModel novoUsuario) {
		return usuarioRepository.save(novoUsuario);
	}
}

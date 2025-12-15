package edu.marketplace.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.marketplace.dto.UsuarioRequestDTO;
import edu.marketplace.dto.UsuarioResponseDTO;
import edu.marketplace.models.UsuarioModel;
import edu.marketplace.repositorys.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<UsuarioResponseDTO> listarUsuarios(){
		List<UsuarioModel> usuarios = usuarioRepository.findAll();
		return usuarios.stream()
				.map(this::converterParaResponseDTO)
				.collect(Collectors.toList());
	}
	
	public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO dto) {
		
		UsuarioModel usuarioParaSalvar = new UsuarioModel();
		usuarioParaSalvar.setUsuario(dto.getUsuario());
		usuarioParaSalvar.setNome(dto.getNome());
		usuarioParaSalvar.setSenha(dto.getSenha());
		usuarioParaSalvar.setEmail(dto.getEmail());
		
		usuarioRepository.save(usuarioParaSalvar);
		
		return converterParaResponseDTO(usuarioParaSalvar);
	}
	
	private UsuarioResponseDTO converterParaResponseDTO(UsuarioModel model) {
		UsuarioResponseDTO dto = new UsuarioResponseDTO();
		dto.setId(model.getId());
		dto.setNome(model.getNome());
		dto.setEmail(model.getEmail());

		return dto;
	}
}

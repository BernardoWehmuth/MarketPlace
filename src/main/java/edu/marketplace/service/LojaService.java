package edu.marketplace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.marketplace.models.LojaModel;
import edu.marketplace.models.UsuarioModel;
import edu.marketplace.repositorys.LojaRepository;
import edu.marketplace.repositorys.UsuarioRepository;

@Service
public class LojaService {
	@Autowired
	private LojaRepository lojaRepository;
	
	@Autowired 
	private UsuarioRepository usuarioRepository;
	
	public List<LojaModel> listarLojas(){
		return lojaRepository.findAll();
	}
	
	public LojaModel criarLoja(int usuarioId, LojaModel novaLoja) {
        UsuarioModel proprietario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Esse usuário não existe"));

        novaLoja.setProprietario(proprietario);
        return lojaRepository.save(novaLoja);
    }
}

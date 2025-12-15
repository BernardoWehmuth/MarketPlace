package edu.marketplace.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.marketplace.dto.LojaRequestDTO;
import edu.marketplace.dto.LojaResponseDTO;
import edu.marketplace.models.*;
import edu.marketplace.repositorys.*;

@Service
public class LojaService {

    @Autowired
    private LojaRepository lojaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<LojaResponseDTO> listarLojas() {
    	return lojaRepository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public LojaResponseDTO criarLoja(int usuarioId, LojaRequestDTO dto) {
    	
    	if(lojaRepository.existsByCnpj(dto.getCnpj())) {
    		throw new IllegalArgumentException("Erro: já existe uma loja cadastrada com esse cnpj.");
    	}
    	
        UsuarioModel proprietario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Esse usuário não existe"));

        LojaModel loja = new LojaModel();
        loja.setNome(dto.getNome());
        loja.setCnpj(dto.getCnpj());
        
        
        loja.setProprietario(proprietario);
        
        LojaModel lojaSalva = lojaRepository.save(loja);
        
        return converterParaDTO(lojaSalva);
    }

    public List<OfertaModel> listarProdutos(int lojaId) {
        LojaModel loja = lojaRepository.findById(lojaId)
                .orElseThrow(() -> new IllegalArgumentException("Essa loja não existe"));
        return loja.getOfertas();
    }
    private LojaResponseDTO converterParaDTO(LojaModel model) {
    	LojaResponseDTO dto = new LojaResponseDTO();
        dto.setId(model.getId());
        dto.setNome(model.getNome());
        dto.setCnpj(model.getCnpj());
        
        if (model.getProprietario() != null) {
            dto.setNomeProprietario(model.getProprietario().getNome());
        }
        
        return dto;
    }
}

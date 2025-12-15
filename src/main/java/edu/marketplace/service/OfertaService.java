package edu.marketplace.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.marketplace.dto.OfertaResponseDTO;
import edu.marketplace.models.ItemModel;
import edu.marketplace.models.LojaModel;
import edu.marketplace.models.OfertaModel;
import edu.marketplace.repositorys.ItemRepository;
import edu.marketplace.repositorys.LojaRepository;
import edu.marketplace.repositorys.OfertaRepository;

@Service
public class OfertaService {

    @Autowired
    private OfertaRepository ofertaRepository;

    @Autowired
    private LojaRepository lojaRepository;

    @Autowired
    private ItemRepository itemRepository;

    public OfertaResponseDTO criarOferta(int lojaId, int itemId, double preco, int quantidade) {
        LojaModel loja = lojaRepository.findById(lojaId)
                .orElseThrow(() -> new IllegalArgumentException("Loja não encontrada"));
        ItemModel item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item não encontrado"));

        OfertaModel oferta = new OfertaModel();
        oferta.setLoja(loja);
        oferta.setItem(item);
        oferta.setPreco(preco);
        oferta.setQuantidade(quantidade);

        OfertaModel ofertaSalva = ofertaRepository.save(oferta);
        
        return converterParaDTO(ofertaSalva);
    }

    public List<OfertaResponseDTO> listarOfertasDaLoja(int lojaId) {
        LojaModel loja = lojaRepository.findById(lojaId)
                .orElseThrow(() -> new IllegalArgumentException("Loja não encontrada"));
        
        return loja.getOfertas().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<OfertaResponseDTO> listarTodasOfertas() {
        return ofertaRepository.findAll().stream()
        		.map(this::converterParaDTO)
        		.collect(Collectors.toList());
    }

    public OfertaResponseDTO atualizarOferta(int ofertaId, double novoPreco, int novaQuantidade) {
        OfertaModel oferta = ofertaRepository.findById(ofertaId)
                .orElseThrow(() -> new IllegalArgumentException("Oferta não encontrada"));

        oferta.setPreco(novoPreco);
        oferta.setQuantidade(novaQuantidade);

        OfertaModel ofertaSalva = ofertaRepository.save(oferta);
        
        return converterParaDTO(ofertaSalva);
    }

    public OfertaResponseDTO adicionarQuantidade(int ofertaId, int quantidadeAdicionada) {
        OfertaModel oferta = ofertaRepository.findById(ofertaId)
                .orElseThrow(() -> new IllegalArgumentException("Oferta não encontrada"));

        int novaQuantidade = oferta.getQuantidade() + quantidadeAdicionada;
        oferta.setQuantidade(novaQuantidade);

        OfertaModel ofertaSalva = ofertaRepository.save(oferta);
        
        return converterParaDTO(ofertaSalva);
    }
    
    private OfertaResponseDTO converterParaDTO(OfertaModel model) {
    	OfertaResponseDTO dto = new OfertaResponseDTO();
        dto.setId(model.getId());
        
        dto.setNomeItem(model.getItem().getNome());
        dto.setDescricaoItem(model.getItem().getDescricao());
        
        dto.setPreco(model.getPreco());
        dto.setQuantidade(model.getQuantidade());
        
        return dto;
    }
}
package edu.marketplace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public OfertaModel criarOferta(int lojaId, int itemId, double preco, int quantidade) {
        LojaModel loja = lojaRepository.findById(lojaId)
                .orElseThrow(() -> new IllegalArgumentException("Loja não encontrada"));
        ItemModel item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item não encontrado"));

        OfertaModel oferta = new OfertaModel();
        oferta.setLoja(loja);
        oferta.setItem(item);
        oferta.setPreco(preco);
        oferta.setQuantidade(quantidade);

        return ofertaRepository.save(oferta);
    }

    public List<OfertaModel> listarOfertasDaLoja(int lojaId) {
        LojaModel loja = lojaRepository.findById(lojaId)
                .orElseThrow(() -> new IllegalArgumentException("Loja não encontrada"));
        return loja.getOfertas();
    }

    public List<OfertaModel> listarTodasOfertas() {
        return ofertaRepository.findAll();
    }

    public OfertaModel atualizarOferta(int ofertaId, double novoPreco, int novaQuantidade) {
        OfertaModel oferta = ofertaRepository.findById(ofertaId)
                .orElseThrow(() -> new IllegalArgumentException("Oferta não encontrada"));

        oferta.setPreco(novoPreco);
        oferta.setQuantidade(novaQuantidade);

        return ofertaRepository.save(oferta);
    }

    public OfertaModel adicionarQuantidade(int ofertaId, int quantidadeAdicionada) {
        OfertaModel oferta = ofertaRepository.findById(ofertaId)
                .orElseThrow(() -> new IllegalArgumentException("Oferta não encontrada"));

        int novaQuantidade = oferta.getQuantidade() + quantidadeAdicionada;
        oferta.setQuantidade(novaQuantidade);

        return ofertaRepository.save(oferta);
    }
}

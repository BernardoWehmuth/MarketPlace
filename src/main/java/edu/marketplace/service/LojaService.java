package edu.marketplace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.marketplace.models.*;
import edu.marketplace.repositorys.*;

@Service
public class LojaService {

    @Autowired
    private LojaRepository lojaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OfertaRepository ofertaRepository;

    public List<LojaModel> listarLojas() {
        return lojaRepository.findAll();
    }

    public LojaModel criarLoja(int usuarioId, LojaModel novaLoja) {
        UsuarioModel proprietario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Esse usuário não existe"));

        novaLoja.setProprietario(proprietario);
        return lojaRepository.save(novaLoja);
    }

    public OfertaModel anexarProduto(int itemId, int lojaId, double preco, int quantidade) {
        LojaModel loja = lojaRepository.findById(lojaId)
                .orElseThrow(() -> new IllegalArgumentException("Essa loja não existe"));

        ItemModel item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Esse item não existe"));

        OfertaModel oferta = new OfertaModel();
        oferta.setItem(item);
        oferta.setLoja(loja);
        oferta.setPreco(preco);
        oferta.setQuantidade(quantidade);

        return ofertaRepository.save(oferta);
    }

    public List<OfertaModel> listarProdutos(int lojaId) {
        LojaModel loja = lojaRepository.findById(lojaId)
                .orElseThrow(() -> new IllegalArgumentException("Essa loja não existe"));
        return loja.getOfertas();
    }
}

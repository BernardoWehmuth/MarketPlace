package edu.marketplace.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.marketplace.repositorys.*;
import jakarta.transaction.Transactional;
import edu.marketplace.dto.PedidoOfertaDTO;
import edu.marketplace.models.*;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private OfertaRepository ofertaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseEntity<?> listarPedidos(){
    	return ResponseEntity.ok(pedidoRepository.findAll());
    }
    
    @Transactional
    public PedidoModel realizarPedido(List<PedidoOfertaDTO> ofertasPedido, int compradorId) {
    	UsuarioModel comprador = usuarioRepository.findById(compradorId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
    	
    	PedidoModel pedido = new PedidoModel();
    	pedido.setComprador(comprador);
    	pedido.setDataPedido(LocalDateTime.now());
        
    	List<OfertaModel> ofertasUsadas = new ArrayList<>();
    	double valorTotal = 0;
    	LojaModel loja = null;
       
    	for (PedidoOfertaDTO item : ofertasPedido) {

            OfertaModel oferta = ofertaRepository.findById(item.getOfertaId())
                    .orElseThrow(() -> new IllegalArgumentException("Oferta não encontrada"));

            if (loja == null)
                loja = oferta.getLoja();

            if (oferta.getLoja().getId() != loja.getId())
                throw new IllegalArgumentException("Todas as ofertas devem ser da mesma loja");

            if (oferta.getQuantidade() < item.getQuantidade())
                throw new IllegalArgumentException("Estoque insuficiente da oferta " + oferta.getId());

            oferta.setQuantidade(oferta.getQuantidade() - item.getQuantidade());
            ofertaRepository.save(oferta);

            ofertasUsadas.add(oferta);

            valorTotal += oferta.getPreco() * item.getQuantidade();
        }
        
    	pedido.setOfertas(ofertasUsadas);
        pedido.setLoja(loja);
        pedido.setValorPedido(valorTotal);

        return pedidoRepository.save(pedido);
    }
    
    @Transactional
    public void excluirPedido(int pedidoId) {
        PedidoModel pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedido.getOfertas().clear();

        pedidoRepository.delete(pedido);
    }

}

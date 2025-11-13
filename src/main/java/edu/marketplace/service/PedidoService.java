package edu.marketplace.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.marketplace.repositorys.*;
import edu.marketplace.models.*;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private OfertaRepository ofertaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public PedidoModel realizarPedido(Iterable<Integer> ofertaIds, int compradorId) {
        List<OfertaModel> ofertas = ofertaRepository.findAllById(ofertaIds);
        if (ofertas.isEmpty()) {
            throw new IllegalArgumentException("Nenhuma oferta encontrada para os IDs informados");
        }

        UsuarioModel comprador = usuarioRepository.findById(compradorId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        PedidoModel pedido = new PedidoModel();
        pedido.setComprador(comprador);
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setLoja(ofertas.get(0).getLoja());
        pedido.setOfertas(ofertas);

        return pedidoRepository.save(pedido);
    }
}

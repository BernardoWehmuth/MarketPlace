package edu.marketplace.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.marketplace.dto.LojaResponseDTO;
import edu.marketplace.dto.OfertaResponseDTO;
import edu.marketplace.dto.PedidoOfertaDTO;
import edu.marketplace.dto.PedidoRequestDTO;
import edu.marketplace.dto.PedidoResponseDTO;
import edu.marketplace.dto.UsuarioResponseDTO;
import edu.marketplace.models.*;
import edu.marketplace.repositorys.*;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private OfertaRepository ofertaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<PedidoResponseDTO> listarPedidos(){
        List<PedidoModel> pedidos = pedidoRepository.findAll();
        
        return pedidos.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public PedidoResponseDTO realizarPedido(PedidoRequestDTO request) {
        
        UsuarioModel comprador = usuarioRepository.findById(request.getCompradorId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        
        PedidoModel pedido = new PedidoModel();
        pedido.setComprador(comprador);
        pedido.setDataPedido(LocalDateTime.now());
        
        List<OfertaModel> ofertasUsadas = new ArrayList<>();
        double valorTotal = 0;
        LojaModel loja = null;
        
        for (PedidoOfertaDTO itemDto : request.getOfertas()) {

            OfertaModel oferta = ofertaRepository.findById(itemDto.getOfertaId())
                    .orElseThrow(() -> new IllegalArgumentException("Oferta não encontrada ID: " + itemDto.getOfertaId()));

            if (loja == null) {
                loja = oferta.getLoja();
            }

            if (oferta.getLoja().getId() != loja.getId()) {
                throw new IllegalArgumentException("Não é possível comprar itens de lojas diferentes no mesmo pedido");
            }

            if (oferta.getQuantidade() < itemDto.getQuantidade()) {
                throw new IllegalArgumentException("Estoque insuficiente para o item: " + oferta.getItem().getNome());
            }

            oferta.setQuantidade(oferta.getQuantidade() - itemDto.getQuantidade());
            ofertaRepository.save(oferta);

            ofertasUsadas.add(oferta);

            valorTotal += oferta.getPreco() * itemDto.getQuantidade();
        }
        
        pedido.setOfertas(ofertasUsadas);
        pedido.setLoja(loja);
        pedido.setValorPedido(valorTotal);

        PedidoModel pedidoSalvo = pedidoRepository.save(pedido);
        return converterParaDTO(pedidoSalvo);
    }
    
    @Transactional
    public void excluirPedido(int pedidoId) {
        if (!pedidoRepository.existsById(pedidoId)) {
            throw new IllegalArgumentException("Pedido não encontrado");
        }
        pedidoRepository.deleteById(pedidoId);
    }

    private PedidoResponseDTO converterParaDTO(PedidoModel model) {
        PedidoResponseDTO dto = new PedidoResponseDTO();
        dto.setId(model.getId());
        dto.setDataPedido(model.getDataPedido());
        dto.setValorTotal(model.getValorPedido());

        UsuarioResponseDTO compradorDto = new UsuarioResponseDTO();
        compradorDto.setId(model.getComprador().getId());
        compradorDto.setNome(model.getComprador().getNome());
        compradorDto.setEmail(model.getComprador().getEmail());
        dto.setComprador(compradorDto);

        LojaResponseDTO lojaDto = new LojaResponseDTO();
        lojaDto.setId(model.getLoja().getId());
        lojaDto.setNome(model.getLoja().getNome());
        lojaDto.setCnpj(model.getLoja().getCnpj());
        lojaDto.setNomeProprietario(model.getLoja().getProprietario().getNome());
        dto.setLoja(lojaDto);

        List<OfertaResponseDTO> listaItens = model.getOfertas().stream()
            .map(oferta -> {
                OfertaResponseDTO itemDto = new OfertaResponseDTO();
                itemDto.setId(oferta.getId());
                itemDto.setNomeItem(oferta.getItem().getNome());
                itemDto.setDescricaoItem(oferta.getItem().getDescricao());
                itemDto.setPreco(oferta.getPreco());
                return itemDto;
            }).collect(Collectors.toList());

        dto.setItens(listaItens);

        return dto;
    }
}
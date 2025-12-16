package edu.marketplace.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.marketplace.dto.*;
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

    public List<PedidoResponseDTO> listarPedidos() {
        return pedidoRepository.findAll()
                .stream()
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

        List<PedidoOfertaModel> itensPedido = new ArrayList<>();
        double valorTotal = 0;
        LojaModel loja = null;

        for (PedidoOfertaDTO itemDto : request.getOfertas()) {

            OfertaModel oferta = ofertaRepository.findById(itemDto.getOfertaId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Oferta não encontrada ID: " + itemDto.getOfertaId()));

            if (loja == null) {
                loja = oferta.getLoja();
            }

            if (oferta.getQuantidade() < itemDto.getQuantidade()) {
                throw new IllegalArgumentException(
                        "Estoque insuficiente para o item: " + oferta.getItem().getNome());
            }

            oferta.setQuantidade(oferta.getQuantidade() - itemDto.getQuantidade());

            PedidoOfertaModel itemPedido = new PedidoOfertaModel();
            itemPedido.setPedido(pedido);
            itemPedido.setOferta(oferta);
            itemPedido.setQuantidade(itemDto.getQuantidade());
            itemPedido.setPrecoUnitario(oferta.getPreco());
            itensPedido.add(itemPedido);

            valorTotal += oferta.getPreco() * itemDto.getQuantidade();
        }

        pedido.setLoja(loja);
        pedido.setValorPedido(valorTotal);
        pedido.setItens(itensPedido);

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

        List<PedidoOfertaResponseDTO> itens = model.getItens().stream()
                .map(item -> {
                	PedidoOfertaResponseDTO itemDto = new PedidoOfertaResponseDTO();
                    itemDto.setId(item.getOferta().getId());
                    itemDto.setNomeItem(item.getOferta().getItem().getNome());
                    itemDto.setQuantidade(item.getQuantidade());
                    itemDto.setPrecoUnitario(item.getPrecoUnitario());
                    itemDto.setPrecoTotal(item.getQuantidade() * item.getPrecoUnitario());
                    return itemDto;
                })
                .collect(Collectors.toList());

        dto.setItens(itens);

        return dto;
    }
}

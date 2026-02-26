package edu.marketplace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.marketplace.dto.ItemRequestDTO;
import edu.marketplace.dto.ItemResponseDTO;
import edu.marketplace.models.*;
import edu.marketplace.repositorys.ItemRepository;

@Service
public class ItemService {
	@Autowired
	ItemRepository itemRepository;
	
	public ItemResponseDTO criarItem(ItemRequestDTO dto) {
		if(itemRepository.existsByNome(dto.getNome())) {
    		throw new IllegalArgumentException("Erro: já existe um item cadastrado com esse nome.");
    	}
		ItemModel item = new ItemModel();
		item.setNome(dto.getNome());
		item.setDescricao(dto.getDescricao());

		ItemModel itemSalvo = itemRepository.save(item);

		return converterParaDTO(itemSalvo);
	}
	
	public List<ItemModel> listarItens() {
		return itemRepository.findAll();
	}

	private ItemResponseDTO converterParaDTO(ItemModel model) {
    	ItemResponseDTO dto = new ItemResponseDTO();
        dto.setId(model.getId());
        dto.setNome(model.getNome());
        dto.setDescricao(model.getDescricao());
        
        return dto;
    }
}

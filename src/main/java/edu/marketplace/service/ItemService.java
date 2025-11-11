package edu.marketplace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.marketplace.models.ItemModel;
import edu.marketplace.repositorys.ItemRepository;

@Service
public class ItemService {
	@Autowired
	ItemRepository itemRepository;
	
	public ItemModel criarItem(ItemModel item) {
		return itemRepository.save(item);
	}
	
	public List<ItemModel> listarItens() {
		return itemRepository.findAll();
	}
}

package edu.marketplace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.marketplace.models.ItemModel;
import edu.marketplace.service.ItemService;

@RestController
@RequestMapping("marketplace/itens")
public class ItemController {
	@Autowired
	ItemService itemService;
	
	
	@PostMapping("/criar")
	public ResponseEntity<?> criarItem(@RequestBody ItemModel novoItem){
		try{
			ItemModel item = itemService.criarItem(novoItem);
			return ResponseEntity.ok(item);
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/listar")
	public ResponseEntity<?> listarItens(){
		return ResponseEntity.ok(itemService.listarItens());
	}
	
}

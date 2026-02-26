package edu.marketplace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.marketplace.dto.ItemRequestDTO;
import edu.marketplace.dto.ItemResponseDTO;
import edu.marketplace.service.ItemService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("marketplace/itens")
public class ItemController {
	@Autowired
	ItemService itemService;
	
	
	@PostMapping
	public ResponseEntity<?> criarItem(@RequestBody @Valid ItemRequestDTO novoItem){
		try{
			ItemResponseDTO item = itemService.criarItem(novoItem);
			return ResponseEntity.ok(item);
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping
	public ResponseEntity<?> listarItens(){
		return ResponseEntity.ok(itemService.listarItens());
	}
}

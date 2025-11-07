package edu.marketplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.marketplace.models.LojaModel;
import edu.marketplace.repositorys.LojaRepository;

@Service
public class LojaService {
	@Autowired
	private LojaRepository lojaRepository;
	
	public LojaModel criarLoja(LojaModel novaLoja) {
		return lojaRepository.save(novaLoja);
	}
}

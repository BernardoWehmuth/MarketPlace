package edu.marketplace.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.marketplace.models.LojaModel;

public interface LojaRepository extends JpaRepository<LojaModel, Integer> {
	boolean existsByCnpj(String cnpj);
}

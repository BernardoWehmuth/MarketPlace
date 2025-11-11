package edu.marketplace.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.marketplace.models.OfertaModel;

public interface OfertaRepository extends JpaRepository<OfertaModel, Integer> {
}

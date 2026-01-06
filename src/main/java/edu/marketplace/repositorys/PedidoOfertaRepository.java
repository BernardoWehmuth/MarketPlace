package edu.marketplace.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;


import edu.marketplace.models.PedidoOfertaModel;

public interface PedidoOfertaRepository extends JpaRepository<PedidoOfertaModel, Integer> {
}

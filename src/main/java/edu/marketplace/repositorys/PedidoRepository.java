package edu.marketplace.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.marketplace.models.PedidoModel;

public interface PedidoRepository extends JpaRepository<PedidoModel, Integer> {
}

package edu.marketplace.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.marketplace.models.ItemModel;

public interface ItemRepository extends JpaRepository<ItemModel, Integer> {
    boolean existsByNome(String nome);
}

package edu.marketplace.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.marketplace.models.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {
	boolean existsByUsuario(String usuario);
	boolean existsByEmail(String email);
}

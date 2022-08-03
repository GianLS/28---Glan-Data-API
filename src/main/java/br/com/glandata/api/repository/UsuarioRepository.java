package br.com.glandata.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.glandata.api.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

	Optional<Usuario> findByLoginIgnoreCase(String login);
}

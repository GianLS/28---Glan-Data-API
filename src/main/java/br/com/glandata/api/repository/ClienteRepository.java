package br.com.glandata.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import br.com.glandata.api.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>, RevisionRepository<Cliente, Long, Long>{

	Optional<Cliente> findByEmailOrCpf(String email, String cpf);
}

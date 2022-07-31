package br.com.glandata.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.glandata.api.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	public Page<Produto> findByCategoriaId(Long id, Pageable pageable);
}

package br.com.glandata.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.glandata.api.dto.ProdutoDto;
import br.com.glandata.api.model.Produto;
import br.com.glandata.api.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping("")
	public ResponseEntity<List<ProdutoDto>> listarTodos() {
		return ResponseEntity.ok(produtoService.listarTodos());
	}

	@GetMapping("/listarPaginado")
	public ResponseEntity<Page<ProdutoDto>> listarPaginado(
			@PageableDefault(page = 0, size = 5, sort = "id", direction = Direction.ASC) Pageable pageable) {
		return ResponseEntity.ok(produtoService.listarPaginado(pageable));
	}

	@PostMapping("")
	public ResponseEntity<ProdutoDto> incluir(@RequestBody @Valid Produto produto) {
		return ResponseEntity.ok(produtoService.incluir(produto));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDto> buscarPorId(@PathVariable Long id) {
		return produtoService.buscarPorId(id).map(p -> ResponseEntity.ok(p)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/categoria/{id}")
	public ResponseEntity<Page<ProdutoDto>> buscarPorCategoriaId(@PathVariable Long id,
			@PageableDefault(page = 0, size = 5, sort = "id", direction = Direction.ASC) Pageable pageable) {
		return ResponseEntity.ok(produtoService.buscarPorCategoria(id, pageable));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProdutoDto> atualizar(@PathVariable Long id, @RequestBody @Valid Produto produto) {
		return produtoService.atualizar(id, produto).map(p -> ResponseEntity.ok(p))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		return produtoService.deletar(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}
}

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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Produtos", description = "Gerenciamento dos produtos da API")
@SecurityRequirement(name = "Authorization")
@ApiResponses(value = {
		@ApiResponse(responseCode = "404", description = "O produto informado não foi encontrado", content = @Content),
		@ApiResponse(responseCode = "200", description = "Operação realizada com sucesso", content = @Content)})
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping("")
	@Operation(summary = "Lista todas os produtos cadastrados")
	public ResponseEntity<List<ProdutoDto>> listarTodos() {
		return ResponseEntity.ok(produtoService.listarTodos());
	}

	@GetMapping("/listarPaginado")
	@Operation(summary = "Lista todas os produtos cadastrados com paginação de registros")
	public ResponseEntity<Page<ProdutoDto>> listarPaginado(
			@PageableDefault(page = 0, size = 5, sort = "id", direction = Direction.ASC) Pageable pageable) {
		return ResponseEntity.ok(produtoService.listarPaginado(pageable));
	}

	@PostMapping("")
	@Operation(summary = "Cadastra um novo produto (com validação)")
	public ResponseEntity<ProdutoDto> incluir(@RequestBody @Valid Produto produto) {
		return ResponseEntity.ok(produtoService.incluir(produto));
	}

	@GetMapping("/{id}")
	@Operation(summary = "Busca um produto pelo ID")
	public ResponseEntity<ProdutoDto> buscarPorId(@PathVariable Long id) {
		return produtoService.buscarPorId(id).map(p -> ResponseEntity.ok(p)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/categoria/{id}")
	@Operation(summary = "Busca os produtos pelo ID da categoria")
	public ResponseEntity<Page<ProdutoDto>> buscarPorCategoriaId(@PathVariable Long id,
			@PageableDefault(page = 0, size = 5, sort = "id", direction = Direction.ASC) Pageable pageable) {
		return ResponseEntity.ok(produtoService.buscarPorCategoria(id, pageable));
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualiza um produto")
	public ResponseEntity<ProdutoDto> atualizar(@PathVariable Long id, @RequestBody @Valid Produto produto) {
		return produtoService.atualizar(id, produto).map(p -> ResponseEntity.ok(p))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta um produto")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		return produtoService.deletar(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}
}

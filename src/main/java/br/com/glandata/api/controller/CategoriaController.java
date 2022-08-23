package br.com.glandata.api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

import br.com.glandata.api.dto.CategoriaDto;
import br.com.glandata.api.model.Categoria;
import br.com.glandata.api.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/categorias")
@Tag(name = "Categorias", description = "Gerenciamento das categorias de produtos da API")
@SecurityRequirement(name = "Authorization")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping("")
	@Cacheable(value = "listaCategorias")
	@Operation(summary = "Lista todas as categorias cadastradas")
	public ResponseEntity<List<CategoriaDto>> listarTodas() {
		List<CategoriaDto> categorias = categoriaService.listarTodas();
		categorias
				.forEach(c -> c.add(linkTo(methodOn(CategoriaController.class).buscarPorId(c.getId())).withSelfRel()));
		return ResponseEntity.ok(categorias);
	}

	@GetMapping("/listarPaginado")
	@Operation(summary = "Lista todas as categorias cadastradas com paginação de registro")
	public ResponseEntity<Page<CategoriaDto>> listarPaginado(
			@PageableDefault(page = 0, size = 5, sort = "id", direction = Direction.ASC) Pageable pageable) {
		return ResponseEntity.ok(categoriaService.listarPaginado(pageable));
	}

	@PostMapping("")
	@CacheEvict(value = "listaCategorias")
	@Operation(summary = "Cadastra uma nova categoria (com validação)")
	public ResponseEntity<CategoriaDto> incluir(@RequestBody @Valid Categoria categoria) {
		Optional<Categoria> categoriaOptional = categoriaService.buscaPorNome(categoria.getNome());
		if (categoriaOptional.isPresent()) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(categoriaService.incluir(categoria));
	}

	@GetMapping("/{id}")
	@Operation(summary = "Busca uma categoria a partir do ID")
	public ResponseEntity<CategoriaDto> buscarPorId(@PathVariable Long id) {
		return categoriaService.buscarPorId(id).map(c -> {
			c.add(linkTo(methodOn(CategoriaController.class).listarTodas()).withRel("Lista de Categorias"));
			return ResponseEntity.ok(c);
		}).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	@CacheEvict(value = "listaCategorias")
	@Operation(summary = "Atualiza uma categoria")
	public ResponseEntity<CategoriaDto> atualizar(@PathVariable Long id, @RequestBody @Valid Categoria categoria) {
		return categoriaService.atualizar(id, categoria).map(c -> ResponseEntity.ok(c))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	@CacheEvict(value = "listaCategorias")
	@Operation(summary = "Deleta uma categoria")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		return new ResponseEntity<>(categoriaService.deletar(id));
	}

}

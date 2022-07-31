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

import br.com.glandata.api.dto.CategoriaDto;
import br.com.glandata.api.model.Categoria;
import br.com.glandata.api.service.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping("")
	public ResponseEntity<List<CategoriaDto>> listarTodas() {
		return ResponseEntity.ok(categoriaService.listarTodas());
	}

	@GetMapping("/listarPaginado")
	public ResponseEntity<Page<CategoriaDto>> listarPaginado(
			@PageableDefault(page = 0, size = 5, sort = "id", direction = Direction.ASC) Pageable pageable) {
		return ResponseEntity.ok(categoriaService.listarPaginado(pageable));
	}
	
	@PostMapping("")
	public ResponseEntity<CategoriaDto> incluir(@RequestBody @Valid Categoria categoria) {
		return ResponseEntity.ok(categoriaService.incluir(categoria));
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoriaDto> buscarPorId(@PathVariable Long id) {
		return categoriaService.buscarPorId(id).map(c -> ResponseEntity.ok(c))
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<CategoriaDto> atualizar(@PathVariable Long id, @RequestBody @Valid Categoria categoria) {
		return categoriaService.atualizar(id, categoria).map(c -> ResponseEntity.ok(c))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		Integer deletar = categoriaService.deletar(id);
		return deletar == 1 ? ResponseEntity.noContent().build()
				: deletar == 0 ? ResponseEntity.notFound().build() : ResponseEntity.badRequest().build();
	}

}

package br.com.glandata.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.glandata.api.dto.CategoriaDto;
import br.com.glandata.api.model.Categoria;
import br.com.glandata.api.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	
	@Autowired
	private ModelMapper modelMapper;

	public List<CategoriaDto> listarTodas() {
		return categoriaRepository.findAll().stream().map(c -> modelMapper.map(c, CategoriaDto.class)).collect(Collectors.toList());
	}
	
	public Page<CategoriaDto> listarPaginado(Pageable pageable) {
		return categoriaRepository.findAll(pageable).map(c -> modelMapper.map(c, CategoriaDto.class));
	}

	public CategoriaDto incluir(@Valid Categoria categoria) {
		return modelMapper.map(categoriaRepository.save(categoria), CategoriaDto.class);
	}

	public Optional<CategoriaDto> buscarPorId(Long id) {
		return categoriaRepository.findById(id).map(c -> modelMapper.map(c, CategoriaDto.class));
	}

	public Optional<CategoriaDto> atualizar(Long id, Categoria categoria) {
		return this.buscarPorId(id).map(c -> {
			modelMapper.map(categoria, c);
			categoriaRepository.save(modelMapper.map(c, Categoria.class));
			return c;
		});
	}

	public Integer deletar(Long id) {
		return categoriaRepository.findById(id).map(c -> {
			if(c.getProdutos().isEmpty()) {
				categoriaRepository.delete(c);
				return 1;	
			}
			return -1;
		}).orElse(0);
	}
}

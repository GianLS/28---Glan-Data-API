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

import br.com.glandata.api.dto.ProdutoDto;
import br.com.glandata.api.model.Produto;
import br.com.glandata.api.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<ProdutoDto> listarTodos() {
		return produtoRepository.findAll().stream().map(p -> modelMapper.map(p, ProdutoDto.class))
				.collect(Collectors.toList());
	}

	public Page<ProdutoDto> listarPaginado(Pageable pageable) {
		return produtoRepository.findAll(pageable).map(p -> modelMapper.map(p, ProdutoDto.class));
	}

	public ProdutoDto incluir(@Valid Produto produto) {
		return modelMapper.map(produtoRepository.save(produto), ProdutoDto.class);
	}

	public Optional<ProdutoDto> buscarPorId(Long id) {
		return produtoRepository.findById(id).map(p -> modelMapper.map(p, ProdutoDto.class));
	}
	
	public Page<ProdutoDto> buscarPorCategoria(Long id, Pageable pageable) {
		return produtoRepository.findByCategoriaId(id, pageable).map(p -> modelMapper.map(p, ProdutoDto.class));
	}

	public Optional<ProdutoDto> atualizar(Long id, @Valid Produto produto) {
		return this.buscarPorId(id).map(p -> {
			modelMapper.map(produto, p);
			produtoRepository.save(modelMapper.map(p, Produto.class));
			return p;
		});
	}

	public boolean deletar(Long id) {
		return produtoRepository.findById(id).map(p -> {
			produtoRepository.delete(p);
			return true;
		}).orElse(false);
	}

	
}

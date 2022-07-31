package br.com.glandata.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.glandata.api.model.Cliente;
import br.com.glandata.api.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public List<Cliente> listarTodos() {
		return clienteRepository.findAll();
	}

	public Page<Cliente> listarPaginado(Pageable pageable) {
		return clienteRepository.findAll(pageable);
	}
	
	public Cliente incluir(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public Optional<Cliente> buscarPorId(Long id) {
		return clienteRepository.findById(id);
	}

	public Optional<Cliente> atualizar(Long id, Cliente cliente) {
		return this.buscarPorId(id).map(c -> {
			c.setNome(cliente.getNome());
			c.setCpf(cliente.getCpf());
			c.setDataNascimento(cliente.getDataNascimento());
			return clienteRepository.save(c);
		});
	}

	public Boolean deletar(Long id) {
		return this.buscarPorId(id).map(c -> {
			clienteRepository.delete(c);
			return true;
		}).orElse(false);
	}
}

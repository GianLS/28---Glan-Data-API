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

import br.com.glandata.api.model.Cliente;
import br.com.glandata.api.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	/**
	 * Lista todos os clientes
	 */
	@GetMapping("")
	public ResponseEntity<List<Cliente>> listarTodos() {
		return ResponseEntity.ok(clienteService.listarTodos());
	}

	/**
	 * Lista todos os cliente de forma paginada e retorna de acordo com as
	 * configurações solicitadas
	 * 
	 * @param pageable Configurações de páginação (Int page, Int size, String
	 *                 nomeAtributo, Direction direcao)
	 * @return Lista de clientes de acordo com a configuração de página solicitada
	 */
	@GetMapping("/listarPaginado")
	public ResponseEntity<Page<Cliente>> listarPaginado(
			@PageableDefault(page = 0, size = 5, sort = "id", direction = Direction.ASC) Pageable pageable) {
		return ResponseEntity.ok(clienteService.listarPaginado(pageable));
	}

	/**
	 * Insere um novo cliente
	 * 
	 * @param cliente
	 * @return
	 */
	@PostMapping("")
	public ResponseEntity<Cliente> incluir(@RequestBody @Valid Cliente cliente) {
		return ResponseEntity.ok(clienteService.incluir(cliente));
	}

	/**
	 * Busca um cliente pelo id
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
		return clienteService.buscarPorId(id).map(c -> ResponseEntity.ok(c)).orElse(ResponseEntity.notFound().build());
	}

	/**
	 * Edita os dados de um cliente
	 * 
	 * @param id      ID do cliente recebido no header
	 * @param cliente Corpo da mensagem com as propriedades de um objeto do tipo
	 *                Cliente
	 * @return O objeto do tipo Cliente atualizado
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody @Valid Cliente cliente) {
		return clienteService.atualizar(id, cliente).map(c -> ResponseEntity.ok(c))
				.orElse(ResponseEntity.notFound().build());

	}

	/**
	 * Delete um cliente a partir do ID
	 * 
	 * @param id Id do cliente a ser deletado
	 * @return Status 204 em caso de sucesso ou 404 se não encontrar o registro
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		return clienteService.deletar(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}
}

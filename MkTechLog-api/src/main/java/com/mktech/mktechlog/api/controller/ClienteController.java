package com.mktech.mktechlog.api.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.mktech.mktechlog.domain.repository.ClienteRepository;
import com.mktech.mktechlog.domain.service.CatalogoClienteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mktech.mktechlog.domain.model.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

	private ClienteRepository clienteRepository;
	private CatalogoClienteService catalogoClienteService;

	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}

	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> listarClienteId(@PathVariable Long clienteId) {
		return clienteRepository.findById(clienteId)
				.map(cliente -> ResponseEntity.ok(cliente))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente){
		return catalogoClienteService.salvar(cliente);
	}

	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizarClienteId(@PathVariable Long clienteId,
													  @Valid @RequestBody Cliente cliente){
		if (clienteRepository.existsById(clienteId)){
			cliente.setId(clienteId);
			cliente =  catalogoClienteService.salvar(cliente);
			return ResponseEntity.ok(cliente);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> deletarCliente(@PathVariable Long clienteId){

		if (!clienteRepository.existsById(clienteId)){
			return ResponseEntity.notFound().build();
		}
		catalogoClienteService.excluir(clienteId);
		return ResponseEntity.noContent().build();
	}
}

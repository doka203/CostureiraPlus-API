package com.cefet.CostureiraPlus.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cefet.CostureiraPlus.dto.PedidoDTO;
import com.cefet.CostureiraPlus.dto.UsuarioDTO;
import com.cefet.CostureiraPlus.dto.VisitaDTO;
import com.cefet.CostureiraPlus.service.PedidoService;
import com.cefet.CostureiraPlus.service.UsuarioService;
import com.cefet.CostureiraPlus.service.VisitaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuário", description = "Operações relacionadas a usuários.")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private VisitaService VisitaService;


	@GetMapping("/{id}")
	@Operation(summary = "Buscar usuário por ID", description = "Retorna os dados de um usuário específico.")
	public ResponseEntity<UsuarioDTO> findById(
			@Parameter(description = "ID do usuario a ser buscado", example = "1")
			@PathVariable Long id) {
		UsuarioDTO usuarioDTO = usuarioService.findById(id);
		return ResponseEntity.ok(usuarioDTO);
	}

	@GetMapping
	@Operation(summary = "Listar todos os usuários", description = "Retorna a lista de todos os usuários cadastrados.")
	public ResponseEntity<List<UsuarioDTO>> findAll() {
		List<UsuarioDTO> usuarioDTOs = usuarioService.findAll();
		return ResponseEntity.ok(usuarioDTOs);
	}

	@PostMapping
	@Operation(summary = "Criar usuário", description = "Cria um novo usuário.")
	public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioDTO usuarioDTO) {
		UsuarioDTO usuarioNovo = usuarioService.insert(usuarioDTO);
		return ResponseEntity.status(201).body(usuarioNovo);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar usuário", description = "Atualiza usuário cadastrado.")
	public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
		UsuarioDTO usuarioAtualizado = usuarioService.update(id, usuarioDTO);
		return ResponseEntity.ok(usuarioAtualizado);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Apagar usuário", description = "Apaga um usuário específico.")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		usuarioService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}/pedidos")
	@Operation(summary = "Listar todas os pedidos de um usuário", description = "Retorna a lista de todas os pedidos de um usuário cadastrado.")
	public ResponseEntity<List<PedidoDTO>> listarPedidosdoUsuario(@PathVariable Long id) {
		List<PedidoDTO> pedidos = pedidoService.findPedidosByClienteId(id);
		return ResponseEntity.ok(pedidos);
	}
	
	@GetMapping("/{id}/visitas")
	@Operation(summary = "Listar visitas do usuário", description = "Retorna a lista de todas as visitas de um usuário cadastrado.")
	public ResponseEntity<List<VisitaDTO>> listarVisitasdoUsuario(@PathVariable Long id) {
		List<VisitaDTO> visitas = VisitaService.findVisitasByClienteId(id);
		return ResponseEntity.ok(visitas);
	}
}

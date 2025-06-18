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

import com.cefet.CostureiraPlus.dto.UsuarioDTO;
import com.cefet.CostureiraPlus.service.UsuarioService;

public class UsuarioController {

    @Autowired
	private UsuarioService usuarioService;

	@GetMapping("/{id}")
	//@Operation(summary = "Buscar usuario por ID", description = "Retorna os dados de um usuario específico.")
	public ResponseEntity<UsuarioDTO> findById(
			//@Parameter(description = "ID do usuario a ser buscado", example = "1")
			@PathVariable Long id) {
		UsuarioDTO usuarioDTO = usuarioService.findById(id);
		return ResponseEntity.ok(usuarioDTO);
	}

	@GetMapping
	//@Operation(summary = "Listar todas os usuarios", description = "Retorna a lista de todas os usuarios cadastrados.")
	public ResponseEntity<List<UsuarioDTO>> findAll() {
		List<UsuarioDTO> usuarioDTOs = usuarioService.findAll();
		return ResponseEntity.ok(usuarioDTOs);
	}

	@PostMapping
	public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioDTO usuarioDTO) {
		UsuarioDTO usuarioNovo = usuarioService.insert(usuarioDTO);
		return ResponseEntity.status(201).body(usuarioNovo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
		UsuarioDTO usuarioAtualizado = usuarioService.update(id, usuarioDTO);
		return ResponseEntity.ok(usuarioAtualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		usuarioService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}

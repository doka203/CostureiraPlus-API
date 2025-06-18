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

import com.cefet.CostureiraPlus.dto.VisitaDTO;
import com.cefet.CostureiraPlus.service.VisitaService;

public class VisitaController {

    @Autowired
	private VisitaService visitaService;

	@GetMapping("/{id}")
	//@Operation(summary = "Buscar visita por ID", description = "Retorna os dados de uma visita específica.")
	public ResponseEntity<VisitaDTO> findById(
			//@Parameter(description = "ID da visita a ser buscada", example = "1")
			@PathVariable Long id) {
		VisitaDTO visitaDTO = visitaService.findById(id);
		return ResponseEntity.ok(visitaDTO);
	}

	@GetMapping
	//@Operation(summary = "Listar todas os usuarios", description = "Retorna a lista de todas os usuarios cadastrados.")
	public ResponseEntity<List<VisitaDTO>> findAll() {
		List<VisitaDTO> visitaDTOs = visitaService.findAll();
		return ResponseEntity.ok(visitaDTOs);
	}

	@PostMapping
	public ResponseEntity<VisitaDTO> create(@RequestBody VisitaDTO visitaDTO) {
		VisitaDTO visitaNovo = visitaService.insert(visitaDTO);
		return ResponseEntity.status(201).body(visitaNovo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<VisitaDTO> update(@PathVariable Long id, @RequestBody VisitaDTO visitaDTO) {
		VisitaDTO visitaAtualizado = visitaService.update(id, visitaDTO);
		return ResponseEntity.ok(visitaAtualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		visitaService.delete(id);
		return ResponseEntity.noContent().build();
	}
}

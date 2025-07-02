package com.cefet.CostureiraPlus.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cefet.CostureiraPlus.dto.VisitaDTO;
import com.cefet.CostureiraPlus.service.VisitaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/visitas")
@Tag(name = "Visita", description = "Operações relacionadas a visitas.")
public class VisitaController {

	@Autowired
	private VisitaService visitaService;

	@GetMapping("/{id}")
	@Operation(summary = "Buscar visita por ID", description = "Retorna os dados de uma visita específica.")
	public ResponseEntity<VisitaDTO> findById(
			@Parameter(description = "ID da visita a ser buscada", example = "1")
			@PathVariable Long id) {
		VisitaDTO visitaDTO = visitaService.findById(id);
		return ResponseEntity.ok(visitaDTO);
	}

	@GetMapping
	@Operation(summary = "Listar todas as visitas", description = "Retorna a lista de todas as visitas cadastradas.")
	public ResponseEntity<List<VisitaDTO>> findAll() {
		List<VisitaDTO> visitaDTOs = visitaService.findAll();
		return ResponseEntity.ok(visitaDTOs);
	}

	@PostMapping
	@Operation(summary = "Criar visita", description = "Cria uma nova visita.")
	public ResponseEntity<VisitaDTO> create(@RequestBody VisitaDTO visitaDTO) {
		VisitaDTO visitaNovo = visitaService.insert(visitaDTO);
		return ResponseEntity.status(201).body(visitaNovo);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar uma visita", description = "Atualiza uma visita cadastrada.")
	public ResponseEntity<VisitaDTO> update(@PathVariable Long id, @RequestBody VisitaDTO visitaDTO) {
		VisitaDTO visitaAtualizado = visitaService.update(id, visitaDTO);
		return ResponseEntity.ok(visitaAtualizado);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Apagar visita", description = "Apaga uma visita cadastrada.")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		visitaService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/agenda")
	@Operation(summary = "Buscar agenda por período", description = "Busca agenda de um período específico.")
	public ResponseEntity<List<VisitaDTO>> getAgendaPorPeriodo(
			@RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
			@RequestParam("fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,
			@RequestParam(value = "clienteId", required = false) Long clienteId) {

		List<VisitaDTO> visitas = visitaService.findByFiltros(dataInicio, dataFim, clienteId);
		return ResponseEntity.ok(visitas);
	}
}

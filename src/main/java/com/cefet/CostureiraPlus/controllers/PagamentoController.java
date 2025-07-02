package com.cefet.CostureiraPlus.controllers;

import java.time.LocalDate;
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

import com.cefet.CostureiraPlus.dto.PagamentoDTO;
import com.cefet.CostureiraPlus.service.PagamentoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/pagamentos")
@Tag(name = "Pagamento", description = "Operações relacionadas a pagamentos.")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pagamento por ID", description = "Retorna os dados de um pagamento específico.")
    public ResponseEntity<PagamentoDTO> findById(
            @Parameter(description = "ID do pagamento a ser buscado", example = "1")
            @PathVariable Long id) {
        PagamentoDTO pagamentoDTO = pagamentoService.findById(id);
        return ResponseEntity.ok(pagamentoDTO);
    }

    @GetMapping
    @Operation(summary = "Listar todas os pagamentos", description = "Retorna a lista de todos os pagamentos cadastrados.")
    public ResponseEntity<List<PagamentoDTO>> findAll() {
        List<PagamentoDTO> pagamentoDTOs = pagamentoService.findAll();
        return ResponseEntity.ok(pagamentoDTOs);
    }

    @PostMapping
    @Operation(summary = "Criar novo pagamento", description = "Cria um novo pagamento.")
    public ResponseEntity<PagamentoDTO> create(@RequestBody PagamentoDTO pagamentoDTO) {
        PagamentoDTO pagamentoNovo = pagamentoService.insert(pagamentoDTO);
        return ResponseEntity.status(201).body(pagamentoNovo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar pagamento", description = "Atualiza um pagamento já existente.")
    public ResponseEntity<PagamentoDTO> update(@PathVariable Long id, @RequestBody PagamentoDTO pagamentoDTO) {
        PagamentoDTO pagamentoAtualizado = pagamentoService.update(id, pagamentoDTO);
        return ResponseEntity.ok(pagamentoAtualizado);
    }

    @PutMapping("/{id}/registrar-pagamento")
    @Operation(summary = "Registrar pagamento", description = "Registra um pagamento recorrente.")
    public ResponseEntity<PagamentoDTO> registrarPagamento(@PathVariable Long id,
            @RequestBody LocalDate dataPagamento) {
        PagamentoDTO pagamentoAtualizado = pagamentoService.registrarPagamento(id, dataPagamento);
        return ResponseEntity.ok(pagamentoAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Apagar pagamento", description = "Apaga um pagamento.")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pagamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

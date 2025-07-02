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

import com.cefet.CostureiraPlus.dto.LembreteDTO;
import com.cefet.CostureiraPlus.dto.PagamentoDTO;
import com.cefet.CostureiraPlus.dto.PedidoDTO;
import com.cefet.CostureiraPlus.service.LembreteService;
import com.cefet.CostureiraPlus.service.PagamentoService;
import com.cefet.CostureiraPlus.service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/pedidos")
@Tag(name = "Pedido", description = "Operações relacionadas a pedido.")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private LembreteService lembreteService;
    @Autowired
    private PagamentoService pagamentoService;


    @GetMapping("/{id}")
    @Operation(summary = "Buscar pedido por ID", description = "Retorna os dados de um pedido específico.")
    public ResponseEntity<PedidoDTO> findById(
            @Parameter(description = "ID do pedido a ser buscado", example = "1")
            @PathVariable Long id) {
        PedidoDTO pedidoDTO = pedidoService.findById(id);
        return ResponseEntity.ok(pedidoDTO);
    }

    @GetMapping
    @Operation(summary = "Listar todas os pedidos", description = "Retorna a lista de todos os pedidos cadastrados.")
    public ResponseEntity<List<PedidoDTO>> findAll() {
        List<PedidoDTO> pedidoDTOs = pedidoService.findAll();
        return ResponseEntity.ok(pedidoDTOs);
    }

    @PostMapping
    @Operation(summary = "Criar pedido", description = "Cria um novo pedido.")
    public ResponseEntity<PedidoDTO> create(@RequestBody PedidoDTO pedidoDTO) {
        PedidoDTO pedidoNovo = pedidoService.insert(pedidoDTO);
        return ResponseEntity.status(201).body(pedidoNovo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar pedido", description = "Atualiza um pedido específico.")
    public ResponseEntity<PedidoDTO> update(@PathVariable Long id, @RequestBody PedidoDTO pedidoDTO) {
        PedidoDTO pedidoAtualizado = pedidoService.update(id, pedidoDTO);
        return ResponseEntity.ok(pedidoAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar pedido", description = "Deleta um pedido.")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/lembretes")
    @Operation(summary = "Listar os lembretes de pedido", description = "Retorna a lista de todos os lembretes cadastrados de um pedido.")
    public ResponseEntity<List<LembreteDTO>> listarLembretesdoPedido(@PathVariable Long id) {
        List<LembreteDTO> lembretes = lembreteService.findLembretesByPedidoId(id);
        return ResponseEntity.ok(lembretes);
    }

    @GetMapping("/{id}/pagamentos")
    @Operation(summary = "Listar pagamentos de pedido", description = "Retorna a lista de todos pagamentos de um pedido cadastrado.")
    public ResponseEntity<List<PagamentoDTO>> listarPagamentosdoPedido(@PathVariable Long id) {
        List<PagamentoDTO> pagamentos = pagamentoService.findPagamentosByPedidoId(id);
        return ResponseEntity.ok(pagamentos);
    }

    @PutMapping("/{id}/cancelar")
    @Operation(summary = "Cancelar pedido", description = "Cancela um pedido.")
    public ResponseEntity<Void> cancelarPedido(@PathVariable Long id) {
        pedidoService.cancelar(id);
        return ResponseEntity.ok().build();
    }
}

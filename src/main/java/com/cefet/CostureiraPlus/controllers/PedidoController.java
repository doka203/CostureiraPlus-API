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
import com.cefet.CostureiraPlus.service.PedidoService;

@RestController
@RequestMapping("/Pedidos")
//@Tag(name = "Pedido", description = "Operações relacionadas a pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/{id}")
    //@Operation(summary = "Buscar pedido por ID", description = "Retorna os dados de um pedido específico")
    public ResponseEntity<PedidoDTO> findById(
            //@Parameter(description = "ID do pedido a ser buscado", exemple = "1")
            @PathVariable Long id) {
        PedidoDTO pedidoDTO = pedidoService.findById(id);
        return ResponseEntity.ok(pedidoDTO);
    }

    @GetMapping
    //@Operation(summary = "Listar todas os pedidos", description = "Retorna a lista de todos os pedidos cadastrados.")
    public ResponseEntity<List<PedidoDTO>> findAll() {
        List<PedidoDTO> pedidoDTOs = pedidoService.findAll();
        return ResponseEntity.ok(pedidoDTOs);
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> create(@RequestBody PedidoDTO pedidoDTO) {
        PedidoDTO pedidoNovo = pedidoService.insert(pedidoDTO);
        return ResponseEntity.status(201).body(pedidoNovo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> update(@PathVariable Long id, @RequestBody PedidoDTO pedidoDTO) {
        PedidoDTO pedidoAtualizado = pedidoService.update(id, pedidoDTO);
        return ResponseEntity.ok(pedidoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

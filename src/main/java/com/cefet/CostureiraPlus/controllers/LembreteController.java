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
import com.cefet.CostureiraPlus.service.LembreteService;

@RestController
@RequestMapping("/lembretes")
// @Tag(name = "Lembrete", description = "Operações relacionadas a lembretes")
public class LembreteController {

    @Autowired
    private LembreteService lembreteService;

    @GetMapping("/{id}")
    // @Operation(summary = "Buscar lembrete por ID", description = "Retorna os
    // dados de um lembrete específico")
    public ResponseEntity<LembreteDTO> findById(
            // @Parameter(description = "ID do lembrete a ser buscado", exemple = "1")
            @PathVariable Long id) {
        LembreteDTO lembreteDTO = lembreteService.findById(id);
        return ResponseEntity.ok(lembreteDTO);
    }

    @GetMapping
    // @Operation(summary = "Listar todas os lembretes", description = "Retorna a
    // lista de todos os lembretes cadastrados.")
    public ResponseEntity<List<LembreteDTO>> findAll() {
        List<LembreteDTO> lembreteDTOs = lembreteService.findAll();
        return ResponseEntity.ok(lembreteDTOs);
    }

    @PostMapping
    public ResponseEntity<LembreteDTO> create(@RequestBody LembreteDTO lembreteDTO) {
        LembreteDTO lembreteNovo = lembreteService.insert(lembreteDTO);
        return ResponseEntity.status(201).body(lembreteNovo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LembreteDTO> update(@PathVariable Long id, @RequestBody LembreteDTO lembreteDTO) {
        LembreteDTO lembreteAtualizado = lembreteService.update(id, lembreteDTO);
        return ResponseEntity.ok(lembreteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        lembreteService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

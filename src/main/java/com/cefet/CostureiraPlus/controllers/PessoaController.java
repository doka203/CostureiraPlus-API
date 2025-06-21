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

import com.cefet.CostureiraPlus.dto.PessoaDTO;
import com.cefet.CostureiraPlus.dto.UsuarioDTO;
import com.cefet.CostureiraPlus.service.PessoaService;
import com.cefet.CostureiraPlus.service.UsuarioService;

@RestController
@RequestMapping("/pessoas")
// @Tag(name = "Pessoa", description = "Operações relacionadas a pessoa")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;
	@Autowired
	private UsuarioService usuarioService;


	@GetMapping("/{id}")
	// @Operation(summary = "Buscar pessoa por ID", description = "Retorna os dados
	// de uma pessoa específica.")
	public ResponseEntity<PessoaDTO> findById(
			// @Parameter(description = "ID da pessoa a ser buscada", example = "1")
			@PathVariable Long id) {
		PessoaDTO pessoaDTO = pessoaService.findById(id);
		return ResponseEntity.ok(pessoaDTO);
	}

	@GetMapping
	// @Operation(summary = "Listar todas as pessoas", description = "Retorna a
	// lista de todas as pessoas cadastradas.")
	public ResponseEntity<List<PessoaDTO>> findAll() {
		List<PessoaDTO> pessoaDTOs = pessoaService.findAll();
		return ResponseEntity.ok(pessoaDTOs);
	}

	@PostMapping
	public ResponseEntity<PessoaDTO> create(@RequestBody PessoaDTO pessoaDTO) {
		PessoaDTO pessoaNovo = pessoaService.insert(pessoaDTO);
		return ResponseEntity.status(201).body(pessoaNovo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PessoaDTO> update(@PathVariable Long id, @RequestBody PessoaDTO pessoaDTO) {
		PessoaDTO pessoaAtualizado = pessoaService.update(id, pessoaDTO);
		return ResponseEntity.ok(pessoaAtualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		pessoaService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}/usuarios")
    public ResponseEntity<List<UsuarioDTO>> listarUsuariosdaPessoa(@PathVariable Long id) {
        List<UsuarioDTO> usuarios = usuarioService.findUsuariosByPessoaId(id);
        return ResponseEntity.ok(usuarios);
    }
}

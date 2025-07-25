package com.cefet.CostureiraPlus.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.cefet.CostureiraPlus.entities.Usuario;
import com.cefet.CostureiraPlus.security.UsuarioDetails;
import com.cefet.CostureiraPlus.service.PessoaService;
import com.cefet.CostureiraPlus.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/pessoas")
@Tag(name = "Pessoa", description = "Operações relacionadas a pessoa.")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	@Operation(summary = "Listar todas as pessoas", description = "Retorna a lista de todas as pessoas cadastradas.")
	public ResponseEntity<List<PessoaDTO>> findAll() {
		List<PessoaDTO> pessoaDTOs = pessoaService.findAll();
		return ResponseEntity.ok(pessoaDTOs);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Buscar pessoa por ID", description = "Retorna os dados de uma pessoa específica.")
	public ResponseEntity<PessoaDTO> findById(
			@Parameter(description = "ID da pessoa a ser buscada", example = "1")
			@PathVariable Long id) {
		PessoaDTO pessoaDTO = pessoaService.findById(id);
		return ResponseEntity.ok(pessoaDTO);
	}

	@PostMapping
	@Operation(summary = "Criar pessoa", description = "Cria uma nova pessoa.")
	public ResponseEntity<PessoaDTO> create(@RequestBody PessoaDTO pessoaDTO) {
		PessoaDTO pessoaNovo = pessoaService.insert(pessoaDTO);
		return ResponseEntity.status(201).body(pessoaNovo);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar pessoa", description = "Atualiza uma pessoa específica.")
	public ResponseEntity<PessoaDTO> update(@PathVariable Long id, @RequestBody PessoaDTO pessoaDTO) {
		PessoaDTO pessoaAtualizado = pessoaService.update(id, pessoaDTO);
		return ResponseEntity.ok(pessoaAtualizado);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Apagar pessoa", description = "Apaga uma pessoa específica.")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		pessoaService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}/usuarios")
	@Operation(summary = "Listar usuários da pessoa", description = "Retorna usuários de uma pessoa específica.")
	public ResponseEntity<List<UsuarioDTO>> listarUsuariosdaPessoa(@PathVariable Long id) {
		List<UsuarioDTO> usuarios = usuarioService.findUsuariosByPessoaId(id);
		return ResponseEntity.ok(usuarios);
	}

	@GetMapping("/email/{email}")
	@Operation(summary = "Buscar pessoa por e-mail", description = "Retorna os dados de uma pessoa específica.")
	public ResponseEntity<PessoaDTO> findByEmail(@PathVariable String email) {
		PessoaDTO pessoaDTO = pessoaService.findByEmail(email);
		return ResponseEntity.ok(pessoaDTO);
	}

	@PutMapping("/email/{email}")
	@Operation(summary = "Atualizar pessoa por e-mail", description = "Atualiza uma pessoa específica.")
	public ResponseEntity<PessoaDTO> updateByEmail(@PathVariable String email, @RequestBody PessoaDTO pessoaDTO) {
		PessoaDTO pessoaAtualizado = pessoaService.updateByEmail(email, pessoaDTO);
		return ResponseEntity.ok(pessoaAtualizado);
	}

	@DeleteMapping("/email/{email}")
	@Operation(summary = "Apagar pessoa por e-mail", description = "Apaga uma pessoa específica por e-mail.")
	public ResponseEntity<Void> deleteByEmail(@PathVariable String email) {
		pessoaService.deleteByEmail(email);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/cpf/{cpf}")
	@Operation(summary = "Buscar pessoa por CPF", description = "Retorna os dados de uma pessoa específica.")
	public ResponseEntity<PessoaDTO> findByCpf(@PathVariable String cpf) {
		PessoaDTO pessoaDTO = pessoaService.findByCpf(cpf);
		return ResponseEntity.ok(pessoaDTO);
	}

	@PutMapping("/cpf/{cpf}")
	@Operation(summary = "Atualizar pessoa por CPF", description = "Atualiza uma pessoa específica por CPF.")
	public ResponseEntity<PessoaDTO> updateByCpf(@PathVariable String cpf, @RequestBody PessoaDTO pessoaDTO) {
		PessoaDTO pessoaAtualizado = pessoaService.updateByCpf(cpf, pessoaDTO);
		return ResponseEntity.ok(pessoaAtualizado);
	}

	@DeleteMapping("/cpf/{cpf}")
	@Operation(summary = "Apagar pessoa por CPF", description = "Apaga uma pessoa específica.")
	public ResponseEntity<Void> deleteByCpf(@PathVariable String cpf) {
		pessoaService.deleteByCpf(cpf);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/meus-clientes")
    @Operation(summary = "Lista os clientes da costureira logada")
    public ResponseEntity<List<PessoaDTO>> getMeusClientes() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsuarioDetails usuarioDetails = (UsuarioDetails) authentication.getPrincipal();
        long costureiraId = usuarioDetails.getUsuario().getId();

        List<PessoaDTO> clientes = pessoaService.findClientesDaCostureira(costureiraId);
        return ResponseEntity.ok(clientes);
    }

	@PostMapping("/meus-clientes")
    @Operation(summary = "Adiciona um novo cliente para a costureira logada")
    public ResponseEntity<PessoaDTO> addMeuCliente(@RequestBody PessoaDTO dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsuarioDetails usuarioDetails = (UsuarioDetails) authentication.getPrincipal();
        Usuario costureira = usuarioDetails.getUsuario();

        PessoaDTO novoCliente = pessoaService.insert(dto, costureira);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
    }
}

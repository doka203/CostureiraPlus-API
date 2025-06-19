package com.cefet.CostureiraPlus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefet.CostureiraPlus.dto.PessoaDTO;
import com.cefet.CostureiraPlus.entities.Pessoa;
import com.cefet.CostureiraPlus.repositories.PessoaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    // Buscar todos
    public List<PessoaDTO> findAll() {
        List<Pessoa> listaPessoa = pessoaRepository.findAll();
        return listaPessoa.stream().map(PessoaDTO::new).toList();
    }

    // Buscar por ID
    public PessoaDTO findById(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa com ID: " + id + " não encontrada."));
        return new PessoaDTO(pessoa);
    }

    // Inserir Pessoa
    public PessoaDTO insert(PessoaDTO dto) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(dto.getNome());
        pessoa.setCpf(dto.getCpf());
        pessoa.setEndereco(dto.getEndereco());
        pessoa.setTelefone(dto.getTelefone());
        pessoa.setEmail(dto.getEmail());
        Pessoa pessoaSalvo = pessoaRepository.save(pessoa);
        return new PessoaDTO(pessoaSalvo);
    }

    // Atualizar Tipo
    public PessoaDTO update(Long id, PessoaDTO dto) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa com ID: " + id + " não encontrada."));
        pessoa.setNome(dto.getNome());
        pessoa.setCpf(dto.getCpf());
        pessoa.setEndereco(dto.getEndereco());
        pessoa.setTelefone(dto.getTelefone());
        pessoa.setEmail(dto.getEmail());
        Pessoa pessoaAtualizado = pessoaRepository.save(pessoa);
        return new PessoaDTO(pessoaAtualizado);
    }

    // Remover por ID
    public void delete(Long id) {
        if (!pessoaRepository.existsById(id)) {
            throw new EntityNotFoundException("Pessoa não encontrada com ID: " + id);
        }
        pessoaRepository.deleteById(id);
    }
}

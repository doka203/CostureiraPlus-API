package com.cefet.CostureiraPlus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefet.CostureiraPlus.dto.LembreteDTO;
import com.cefet.CostureiraPlus.entities.Lembrete;
import com.cefet.CostureiraPlus.repositories.LembreteRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LembreteService {

    @Autowired
    private LembreteRepository lembreteRepository;

    // Buscar todos
    public List<LembreteDTO> findAll() {
        List<Lembrete> listaLembretes = lembreteRepository.findAll();
        return listaLembretes.stream().map(LembreteDTO::new).toList();
    }

    //Buscar por ID
    public LembreteDTO findById(Long id) {
        Lembrete lembrete = lembreteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lembrete com ID: " + id + "não encontrado."));
        return new LembreteDTO(lembrete);
    }

    //Inserir Lembrete
    public LembreteDTO insert(LembreteDTO dto) {
        Lembrete lembrete = new Lembrete();
        lembrete.setDescricao(dto.getDescricao());
        lembrete.setData(dto.getData());
        lembrete.setHora(dto.getHora());
        lembrete.setStatus(dto.getStatus());
        lembrete.setPedido(dto.getPedido());
        Lembrete lembreteSalvo = lembreteRepository.save(lembrete);
        return new LembreteDTO(lembreteSalvo);
    }

    //Atualizar Lembrete 
    public LembreteDTO update(Long id, LembreteDTO dto) {
        Lembrete lembrete = lembreteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lembrete com ID: " + id + " não encontrado."));
        lembrete.setDescricao(dto.getDescricao());
        lembrete.setData(dto.getData());
        lembrete.setHora(dto.getHora());
        lembrete.setStatus(dto.getStatus());
        lembrete.setPedido(dto.getPedido());
        Lembrete lembreteAtualizado = lembreteRepository.save(lembrete);
        return new LembreteDTO(lembreteAtualizado);
    }

    //Remover por iD
    public void delete(Long id) {
        if (!lembreteRepository.existsById(id)) {
            throw new EntityNotFoundException("Lembrete não encontrada com ID: " + id);
        }
        lembreteRepository.deleteById(id);
    }
}

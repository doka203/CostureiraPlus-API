package com.cefet.CostureiraPlus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cefet.CostureiraPlus.dto.VisitaDTO;
import com.cefet.CostureiraPlus.entities.Visita;
import com.cefet.CostureiraPlus.repositories.VisitaRepository;

import jakarta.persistence.EntityNotFoundException;

public class VisitaService {

    @Autowired
    private VisitaRepository visitaRepository;

    //Buscar todos
    public List<VisitaDTO> findAll(){
        List<Visita> listaVisitas = visitaRepository.findAll();
        return listaVisitas.stream().map(VisitaDTO::new).toList();
    }

    //Buscar por iD
    public VisitaDTO findById(long id){
        Visita visita = visitaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Visita com ID: " + id + " não encontrada."));
        return new VisitaDTO(visita);
    }

    //Inserir Visita
    public VisitaDTO insert(VisitaDTO dto){
        Visita visita = new Visita();
        visita.setData(dto.getData());
        visita.setHora(dto.getHora());
        visita.setDescricao(dto.getDescricao());
        Visita visitaSalvo = visitaRepository.save(visita);
        return new VisitaDTO(visitaSalvo);
    }

    //Atualizar Visita
    public VisitaDTO update(Long id, VisitaDTO dto){
        Visita visita = visitaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Visita com ID: " + id + " não encontrada."));
        visita.setData(dto.getData());
        visita.setHora(dto.getHora());
        visita.setDescricao(dto.getDescricao());
        Visita visitaAtualizada = visitaRepository.save(visita);
        return new VisitaDTO(visitaAtualizada);
    }

    //Remove por iD
    public void delete(Long id) {
         if (!visitaRepository.existsById(id)) {
             throw new EntityNotFoundException("Visita não encontrada com ID: " + id);
         }
         visitaRepository.deleteById(id);
     }  
}

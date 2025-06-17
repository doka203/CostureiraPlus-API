package com.cefet.CostureiraPlus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cefet.CostureiraPlus.dto.UsuarioDTO;
import com.cefet.CostureiraPlus.entities.Usuario;
import com.cefet.CostureiraPlus.repositories.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Buscar todos
    public List<UsuarioDTO> findAll(){
        List<Usuario> listaUsuarios = usuarioRepository.findAll();
        return listaUsuarios.stream().map(UsuarioDTO::new).toList();
    }

    //Buscar por iD
    public UsuarioDTO findById(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                 .orElseThrow(() -> new EntityNotFoundException("Usuario com ID: " + id + " não encontrado."));
        return new UsuarioDTO(usuario);
    }

    //Inserir Usuario
    public UsuarioDTO insert(UsuarioDTO dto){
        Usuario usuario = new Usuario();
        usuario.setLogin(dto.getLogin());
        usuario.setSenha(dto.getSenha());
        usuario.setTipo(dto.getTipo());
        usuario.setPessoa(dto.getPessoa());
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return new UsuarioDTO(usuarioSalvo);
    }

    //Atualizar Usuario
    public UsuarioDTO update(Long id, UsuarioDTO dto){
        Usuario usuario = usuarioRepository.findById(id)
                 .orElseThrow(() -> new EntityNotFoundException("Usuario com ID: " + id + " não encontrado."));
        usuario.setLogin(dto.getLogin());
        usuario.setSenha(dto.getSenha());
        usuario.setTipo(dto.getTipo());
        usuario.setPessoa(dto.getPessoa());
        Usuario usuarioAtualizado = usuarioRepository.save(usuario);
        return new UsuarioDTO(usuarioAtualizado);
    }

    //Remover por iD
    public void delete(Long id) {
         if (!usuarioRepository.existsById(id)) {
             throw new EntityNotFoundException("Usuário não encontrada com ID: " + id);
         }
         usuarioRepository.deleteById(id);
     }  
}

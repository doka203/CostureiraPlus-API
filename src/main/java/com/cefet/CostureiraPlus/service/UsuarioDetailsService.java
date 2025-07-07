package com.cefet.CostureiraPlus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cefet.CostureiraPlus.entities.Usuario;
import com.cefet.CostureiraPlus.repositories.UsuarioRepository;
import com.cefet.CostureiraPlus.security.UsuarioDetails;

@Service
public class UsuarioDetailsService implements UserDetailsService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioDetails loadUserByUsername(String login) throws UsernameNotFoundException{
        Usuario usuario = usuarioRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + login));
        return new UsuarioDetails(usuario);
    }

}

package br.com.ricartedev.usuario.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.ricartedev.usuario.data.DetalheUsuarioData;
import br.com.ricartedev.usuario.models.UsuarioModel;
import br.com.ricartedev.usuario.repositories.UsuarioRepository;

@Component
public class DetalheUsuarioService implements UserDetailsService {

  private final UsuarioRepository repository;
  
public DetalheUsuarioService(UsuarioRepository repository) {
    this.repository = repository;
  }

@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
  Optional<UsuarioModel> usuario = repository.findByLogin(username);
  if (usuario.isEmpty()) {
    throw new UsernameNotFoundException("Usuario [" + username + "] não encontrado.");
  }

  return new DetalheUsuarioData(usuario);
  
  }
}
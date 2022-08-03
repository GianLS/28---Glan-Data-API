package br.com.glandata.api.security;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.glandata.api.model.Usuario;
import br.com.glandata.api.repository.UsuarioRepository;

@Transactional
@Repository
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.findByLoginIgnoreCase(login);

		if (usuario.isPresent())
			return usuario.get();

		throw new UsernameNotFoundException("Dados inválidos");
	}
}

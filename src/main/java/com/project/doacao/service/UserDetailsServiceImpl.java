package com.project.doacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.doacao.model.Pai;
import com.project.doacao.repository.PaiRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private PaiRepository paiRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Pai pai = paiRepository.findByEmail(username).get();
		
		if(pai == null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}
		
		return new User(pai.getEmail(), pai.getSenha(), pai.getAuthorities());
	}

}

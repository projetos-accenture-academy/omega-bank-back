package com.gama.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gama.model.Usuario;
import com.gama.repository.UsuarioRepository;



public class AppUserDetailService {/* implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String id)
	{
		Usuario user = userRepository.findById(Integer.parseInt(id)).orElse(null);
		if (user == null) {
            throw new UsernameNotFoundException(id.toString());
        }
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getSenha(), authorities);
	}
	*/
	
}

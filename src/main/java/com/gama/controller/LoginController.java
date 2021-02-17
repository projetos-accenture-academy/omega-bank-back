package com.gama.controller;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gama.model.Login;
import com.gama.model.dto.SessionDTO;
import com.gama.model.Usuario;
import com.gama.repository.UsuarioRepository;
import com.gama.security.JWTConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private UsuarioRepository userRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@PostMapping
	public SessionDTO logar(Login login) throws Exception {

		if (login == null || login.getLogin().isEmpty() || login.getSenha().isEmpty()) {
			throw new RuntimeException("Login e senha são requeridos");
		}

		Optional<Usuario> optuser = userRepository.findByLogin(login.getLogin());

		Usuario usuario = optuser.get();

		boolean senhaOk = encoder.matches(login.getSenha(),usuario.getSenha());

		if (!senhaOk) {
			throw new RuntimeException("Senha inválida para o login: " + login.getLogin());
		}

		// tempo do token = 1 horas
		long tempoToken = 1 * 60 * 60 * 1000;
		SessionDTO sessao = new SessionDTO();
		sessao.setTokenExpeditionTime(new Date(System.currentTimeMillis()));
		sessao.setTokenExpirationTime(new Date(System.currentTimeMillis() + tempoToken));
		
		sessao.setLogin(usuario.getLogin());

		sessao.setToken(JWTConstants.PREFIX + getJWTToken(sessao));

		return sessao;
	}
	//como vc gerenciaria a nivel de banco o role de um usuario
	private String getJWTToken(SessionDTO sessao) {
		String role = "ROLE_USER";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(role);

		String token = Jwts.builder().setSubject(sessao.getLogin())
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(sessao.getTokenExpeditionTime()).setExpiration(sessao.getTokenExpirationTime())
				.signWith(SignatureAlgorithm.HS512, JWTConstants.KEY.getBytes()).compact();

		return token;
	}
}

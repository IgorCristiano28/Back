package com.igor.backend.service;

import java.util.Optional;

import com.igor.backend.model.entity.Usuario;

public interface UsuarioService {
	
	Usuario autenticar(String email, String senha);
	
	Usuario salvarUsuario(Usuario usuario);
	
	void validarEmail(String email);

	//metodo pra buscar usuario por id, passando o id para o metodo pra retorna o usuario
		Optional<Usuario> obterPorId(Long id);

}

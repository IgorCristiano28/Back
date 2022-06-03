package com.igor.minhasfinancas.service;

import java.util.Optional;

import com.igor.minhasfinancas.model.entity.Usuario;

public interface UsuarioService {
	
	Usuario autenticar(String email, String senha);
	
	Usuario salvarUsuario(Usuario usuario);
	
	void validarEmail(String email);

	//metodo pra buscar usuario por id, passando o id para o metodo e ele vai retorna o usuario
		Optional<Usuario> obterPorId(Long id);

}

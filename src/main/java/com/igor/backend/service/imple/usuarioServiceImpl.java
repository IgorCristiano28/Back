package com.igor.backend.service.imple;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.igor.backend.exception.ErroAutenticacao;
import com.igor.backend.exception.RegraNegocioException;
import com.igor.backend.model.entity.Usuario;
import com.igor.backend.model.repository.UsuarioRepository;
import com.igor.backend.service.UsuarioService;

@Service
public class usuarioServiceImpl implements UsuarioService{
	
	private UsuarioRepository repository;
	
	public usuarioServiceImpl(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Usuario autenticar(String email, String senha) {
		Optional<Usuario> usuario = repository.findByEmail(email);
		
		if(!usuario.isPresent()) {
			throw new ErroAutenticacao ("Usuario não encontrado para o email informado.");
			
		}
		
		if(usuario.get().getSenha().equals(senha)) {
			throw new ErroAutenticacao ("Senha inválida.");
			
		}
		return usuario.get();
	}

	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
        validarEmail(usuario.getEmail());
		return repository.save(usuario);
	}

	@Override
	public void validarEmail(String email) {
		boolean existe = repository.existsByEmail(email);
		if(existe) {
			throw new RegraNegocioException("Já existe um usuário cadastrado com este email.");
			
		}
		
	}

	@Override
	public Optional<Usuario> obterPorId(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}
	

}

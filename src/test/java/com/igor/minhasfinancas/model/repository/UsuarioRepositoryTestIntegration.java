package com.igor.minhasfinancas.model.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.igor.minhasfinancas.model.entity.Usuario;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioRepositoryTestIntegration {
	
	@Autowired
	UsuarioRepository repository;
	
	@Test 
	public void deveVerificarAExistenciaDeUmEmail() {
		//cenario
		Usuario usuario = Usuario.builder().nome("Igor Cristiano").email("igor_cristiano@hotmail.com").senha("123456").build();
		repository.save(usuario);
		
		
		//açao/execucao
		boolean result = repository.existsByEmail("igor_cristiano@hotmail.com");
		
		
		//verificacao
		Assertions.assertThat(result).isTrue();
		
	}
	
	@Test
	public void deveRetornaFalsoQuandoNaoHouverUsuarioCadastradoComEmail() {
		//cenario
		repository.deleteAll();
		
		//acao
		boolean result =repository.existsByEmail("igor_cristiano@hotmail.com");
		
		//verificacao
		Assertions.assertThat(result).isFalse();
		
	}
	
	@Test
	public void devePersistirUmUsuarioNabaseDeDados() {
		//cenario
		Usuario usuario = criarUsuario();
				
		
		//acao
		Usuario usuarioSalvo = repository.save(usuario);
		
		//verificacao
		Assertions.assertThat(usuarioSalvo.getId()).isNotNull();
			
	}
	
	
	@Test
	public void deveBuscarUmUsuarPorEmail() {
		//cenario a classe não pode ter id, se não lanca excesao propriedade do entitymanager
		Usuario usuario = criarUsuario();
		
		//verificao
		Optional<Usuario> result = repository.findByEmail("igor_cristiano@hotmail.com");
		
		Assertions.assertThat(result.isPresent()).isTrue();
		
	}
	
	@Test
	public void deveRetornaVazioAoBuscarUsuarioPorEmailQuandoNaoExisteNaBase() {
		//cenario
		
		
		//verificao
		Optional<Usuario> result = repository.findByEmail("igor_cristiano@hotmail.com");
		
		Assertions.assertThat(result.isPresent()).isFalse();
		
	}
	
	public static Usuario criarUsuario() {
		return   Usuario 
				.builder()
				.email("igor_cristiano@hotmail.com")
				.senha("1234567")
				.build();
		
		
	}
	
	
}

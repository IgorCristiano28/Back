package com.igor.minhasfinancas.model.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.igor.minhasfinancas.model.entity.Usuario;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioRepositoryTest {
	
	@Autowired
	UsuarioRepository repository;
	
	@Autowired
	TestEntityManager entityManager;

	@Test  @Order(1)
	public void deveVerificarAExistenciaDeUmEmail() {
		//cenario
		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);
		
		//açao/execucao
		boolean result = repository.existsByEmail("usuario@email.com");
		
		
		//verificacao
		Assertions.assertThat(result).isTrue();
		
	}
	
	@Test @Order(2)
	public void deveRetornaFalsoQuandoNaoHouverUsuarioCadastradoComEmail() {
		//cenario
		//repository.deleteAll();
		
		//acao
		boolean result =repository.existsByEmail("usuario@email.com");
		
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
		entityManager.persist(usuario);
		
		//verificao
		Optional<Usuario> result = repository.findByEmail("usuario@email.com");
		
		Assertions.assertThat(result.isPresent()).isTrue();
		
	}
	
	@Test
	public void deveRetornaVazioAoBuscarUsuarioPorEmailQuandoNaoExisteNaBase() {
		//cenario
		
		
		//verificao
		Optional<Usuario> result = repository.findByEmail("usuario@email.com");
		
		Assertions.assertThat(result.isPresent()).isFalse();
		
	}
	
	public static Usuario criarUsuario() {
		return   Usuario 
				.builder()
				.email("usuario@email.com")
				.senha("senha")
				.build();
		
		
	}
	
	
}

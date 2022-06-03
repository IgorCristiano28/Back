package com.igor.minhasfinancas.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.igor.minhasfinancas.exception.ErroAutenticacao;
import com.igor.minhasfinancas.exception.RegraNegocioException;
import com.igor.minhasfinancas.model.entity.Usuario;
import com.igor.minhasfinancas.model.repository.UsuarioRepository;
import com.igor.minhasfinancas.service.imple.usuarioServiceImpl;


@ExtendWith(SpringExtension.class)
@Profile("test")
public class usuarioServiceTest {
	
	@SpyBean
	usuarioServiceImpl service;
	
	//cria um mock que é um bean gerenciavel
	@MockBean
	UsuarioRepository repository;
		
	/*
	 * @BeforeEach public void setUp() { //repository =
	 * Mockito.mock(UsuarioRepository.class); service =
	 * Mockito.spy(usuarioServiceImpl.class);
	 * 
	 * //service = new usuarioServiceImpl(repository);
	 * 
	 * }
	 */
	
	
	@Test 
	public void deveSalvarUmUsuario() {
		assertThrows(RegraNegocioException.class, () ->{
		
		//cenario
		Mockito.doNothing().when(service).validarEmail(Mockito.anyString());
		Usuario usuario = Usuario.builder()
				.id(1l)
				.nome("nome")
				.email("email.com.br")
				.senha("senh").build();
		
		Mockito.when(repository.save(Mockito.any(Usuario.class))).thenReturn(usuario);
		
		//acao
		Usuario usuarioSalvo = service.salvarUsuario(new Usuario());
		
		
		//verificacao
		Assertions.assertThat(usuarioSalvo).isNotNull();
		Assertions.assertThat(usuarioSalvo.getId()).isEqualTo(1l);
		Assertions.assertThat(usuarioSalvo.getNome()).isEqualTo("nome");
		Assertions.assertThat(usuarioSalvo.getEmail()).isEqualTo("email@email.com");
		Assertions.assertThat(usuarioSalvo.getSenha()).isEqualTo("senha");
		
		});
		
	}
	
	@Test 
	public void naoDeveSalvarUmUsuarioComEmailJaCadastrado() {
		assertThrows(RegraNegocioException.class, () ->{
		
		//cenario
		String email = "email@email.com";
		Usuario usuario =  Usuario.builder().email("email@email.com").build();
		Mockito.doThrow(RegraNegocioException.class).when(service).validarEmail("email@email.com");
		
		//acao
		service.salvarUsuario(usuario);
		
		//verificcao
		Mockito.verify(repository,Mockito.never()).save(usuario);
		
		});
	}
	
	
	@Test 
	public void deveAutenticarUmUsuarioComSucesso() {
		assertThrows(RegraNegocioException.class, () ->{
		//cenario
		String email = "email@email.com";
		String senha =  "senha";
		
		Usuario usuario = Usuario.builder().email(email).senha(senha).id(1l).build();
		Mockito.when(repository.findByEmail(email)).thenReturn(Optional.of(usuario));
		
		//acao
		Usuario result = service.autenticar(email, senha);
		
		//verificacao
		Assertions.assertThat(result).isNotNull();
		});
	}
	
	@Test  
	public void deveLancarErroQuandoNaoEncontrarUsuarioCadastradoComEmailInformado() {
		assertDoesNotThrow(() -> {
		//cenario
		Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
		
		//acao
		service.autenticar("email@email.com","senha");
		});
	}
	
	@Test 
	public void develancarErroQuandoSenhaNaoBater() {
		assertDoesNotThrow(() -> {
		String senha = "senha";
		Usuario usuario = Usuario.builder().email("email@email.com").senha(senha).id(1l).build();
		Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(usuario));
		
		//acao
		Throwable exception = Assertions.catchThrowable( () -> service.autenticar("email@email.com", "123"));
		Assertions.assertThat(exception).isInstanceOf(ErroAutenticacao.class).hasMessage("Senha Inválida.");
		});
	}
		
		
	
	

	@Test
	public void deveValidarEmail() {
		assertDoesNotThrow(() -> {
		//cenario
		  //UsuarioRepository usuarioRepositoryMock = Mockito.mock(UsuarioRepository.class);
			Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);
		
		//acao
		service.validarEmail("email@email.com");
			
	});
}
	@Test 
	public void deveLancarErroAoValidarEmailQuandoExistirEmailcadastrado() {
		
		assertDoesNotThrow(() -> {
		//cenario
		//Usuario usuario = Usuario.builder().nome("usuario").email("email@email.com").build();
		//repository.save(usuario);
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);   
		
		
		//acao
		Throwable exception = Assertions.catchThrowable ( () -> service.autenticar("email@email.com", "senha"));
		
		
		//verificação
		Assertions.assertThat(exception)
		.isInstanceOf(ErroAutenticacao.class)
		.hasMessage("Usuario não encontrado para o email informado.");
		
	});
	
}
}

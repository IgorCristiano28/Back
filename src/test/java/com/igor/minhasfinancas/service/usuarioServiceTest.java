package com.igor.minhasfinancas.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.igor.minhasfinancas.exception.ErroAutenticacao;
import com.igor.minhasfinancas.exception.RegraNegocioException;
import com.igor.minhasfinancas.model.entity.Usuario;
import com.igor.minhasfinancas.model.repository.UsuarioRepository;
import com.igor.minhasfinancas.service.imple.usuarioServiceImpl;
//@Profile("test")
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class usuarioServiceTest {
	
	@Autowired
	UsuarioService servicee;
	
	@SpyBean
	usuarioServiceImpl service;
	
	//cria um mock que é um bean gerenciavel
	@Autowired
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
				.nome("Igor Cristiano Sousa de Jesus")
				.email("igorcristiano2888@gmail.com")
				.senha("123456").build();
		
		//Mockito.when(repository.save(Mockito.any(Usuario.class))).thenReturn(usuario);
		
		//acao
		Usuario usuarioSalvo = service.salvarUsuario(new Usuario());
		
		
		//verificacao
		Assertions.assertThat(usuarioSalvo).isNotNull();
		Assertions.assertThat(usuarioSalvo.getId()).isEqualTo(1l);
		Assertions.assertThat(usuarioSalvo.getNome()).isEqualTo("Igor Cristiano");
		Assertions.assertThat(usuarioSalvo.getEmail()).isEqualTo("igorcristiano2888@gmail.com");
		Assertions.assertThat(usuarioSalvo.getSenha()).isEqualTo("123456");
		
		});
		
	}
	
	@Test 
	public void naoDeveSalvarUmUsuarioComEmailJaCadastrado() {
		assertThrows(RegraNegocioException.class, () ->{
		
		//cenario
		String email = "igorcristiano288@gmail.com";
		Usuario usuario =  Usuario.builder().email("igorcristiano288@gmail.com").build();
		Mockito.doThrow(RegraNegocioException.class).when(service).validarEmail("igorcristiano28@gmail.com");
		
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
		String email = "igorcristiano28@gmail.com";
		String senha =  "123456";
		
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
		service.autenticar("igorcristiano28@gmail.com","123456");
		});
	}
	
	@Test 
	public void develancarErroQuandoSenhaNaoBater() {
		assertDoesNotThrow(() -> {
		String senha = "123456";
		Usuario usuario = Usuario.builder().email("igorcristiano28@gmail.com").senha(senha).id(1l).build();
		Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(usuario));
		
		//acao
		Throwable exception = Assertions.catchThrowable( () -> service.autenticar("igorcristiano28@gmail.com", "123"));
		Assertions.assertThat(exception).isInstanceOf(ErroAutenticacao.class).hasMessage("Senha Inválida.");
		});
	}
		
		
	@Test
	public void deveValidarEmailNaBase() {
		assertDoesNotThrow(() -> {
		
		//cenario
		repository.deleteAll();
		 
		//acao
		service.validarEmail("igorcristiano28@gmail.com");
		});		
	
}
	
	
	

	@Test
	public void deveValidarEmail() {
		assertDoesNotThrow(() -> {
		//cenario
		  //UsuarioRepository usuarioRepositoryMock = Mockito.mock(UsuarioRepository.class);
			Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);
		
		//acao
		service.validarEmail("igorcristiano28@gmail.com");
			
	});
}
	
	@Test 
	public void deveLancarErroAoValidarEmailQuandoExistirEmailcadastradoNaBase() {
		assertThrows(RegraNegocioException.class,() -> {
		
		//cenario
		Usuario usuario = Usuario.builder().nome("Igor Cristiano").email("igorcristiano28@gmail.com").build();
		repository.save(usuario);
		  
		
		
		//acao
		service.autenticar("igorcristiano28@gmail.com", "123456");
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
		Throwable exception = Assertions.catchThrowable ( () -> service.autenticar("igorcristiano28@gmail.com", "123456"));
		
		
		//verificação
		Assertions.assertThat(exception)
		.isInstanceOf(ErroAutenticacao.class)
		.hasMessage("Usuario não encontrado para o email informado.");
		
	});
	
}
}

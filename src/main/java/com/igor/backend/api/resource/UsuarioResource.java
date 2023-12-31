package com.igor.backend.api.resource;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igor.backend.api.dto.UsuarioDTO;
import com.igor.backend.exception.ErroAutenticacao;
import com.igor.backend.exception.RegraNegocioException;
import com.igor.backend.model.entity.Usuario;
import com.igor.backend.service.LancamentoService;
import com.igor.backend.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioResource {
	
	//é o controler
	private UsuarioService service;
	private LancamentoService lancamentoService;
	
	public UsuarioResource(UsuarioService service) {
		this.service = service;
		
	}
	
	@PostMapping("/autenticar")
	public ResponseEntity autenticar (@RequestBody UsuarioDTO dto) {
		
		try {
		Usuario usuarioAutenticado = service.autenticar(dto.getEmail(), dto.getSenha());
		    return ResponseEntity.ok(usuarioAutenticado);
		}catch (ErroAutenticacao e) {
		return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody UsuarioDTO dto) {
		
		Usuario usuario = Usuario.builder()
				.nome(dto.getNome())
				.email(dto.getEmail())
				.senha(dto.getSenha()).build();
		
		try {
			Usuario usuarioSalvo = service.salvarUsuario(usuario);
			return new ResponseEntity(usuarioSalvo,HttpStatus.CREATED);
			
		}catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
			
		}
	}
		
		
		@GetMapping("{id}/saldo")
		public ResponseEntity obterSaldo(@PathVariable ("id") Long id) {
			Optional<Usuario> usuario = service.obterPorId(id);
			
			if(!usuario.isPresent()) {
			  return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
			
			BigDecimal saldo = lancamentoService.obterSaldoPorUsuario(id);
			return ResponseEntity.ok(saldo);
			
		}
		
		@Autowired
		public UsuarioResource(LancamentoService lancamentoService, UsuarioService usuarioService){
		     this.lancamentoService = lancamentoService;
		     this.service = usuarioService;
		}
		
	
	

}

package com.igor.minhasfinancas.api.resource;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igor.minhasfinancas.model.entity.TipoViagemChecklistCliente;
import com.igor.minhasfinancas.service.TipoViagemChecklistClienteService;

@RestController
@RequestMapping("/api/tipo-viagem")
public class TipoViagemChecklistClienteController {
	
	private final TipoViagemChecklistClienteService tipoViagemService;

    @Autowired
    public TipoViagemChecklistClienteController(TipoViagemChecklistClienteService tipoViagemService) {
        this.tipoViagemService = tipoViagemService;
    }

    @PostMapping("/criar")
    public ResponseEntity<TipoViagemChecklistCliente> criarTipoViagem(@RequestBody String nomeTipoViagem) {
        TipoViagemChecklistCliente novoTipoViagem = tipoViagemService.criarTipoViagem(nomeTipoViagem);
        return new ResponseEntity<>(novoTipoViagem, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<TipoViagemChecklistCliente>> buscarTodosTiposViagem() {
        List<TipoViagemChecklistCliente> tiposViagem = tipoViagemService.buscarTodosTiposViagem();
        return ResponseEntity.ok(tiposViagem);
    }

    @GetMapping("/{codigoTipoViagem}")
    public ResponseEntity<TipoViagemChecklistCliente> buscarTipoViagemPorId(@PathVariable UUID codigoTipoViagem) {
        TipoViagemChecklistCliente tipoViagem = tipoViagemService.buscarTipoViagemPorId(codigoTipoViagem);
        return ResponseEntity.ok(tipoViagem);
    }
    
    @DeleteMapping("/{codigoTipoViagem}")
    public ResponseEntity<Void> excluirTipoViagemPorCodigo(@PathVariable UUID codigoTipoViagem) {
        tipoViagemService.excluirTipoViagemPorCodigo(codigoTipoViagem);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

package com.igor.minhasfinancas.service.imple;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igor.minhasfinancas.model.entity.TipoViagemChecklistCliente;
import com.igor.minhasfinancas.model.repository.TipoViagemChecklistClienteRepository;
import com.igor.minhasfinancas.service.TipoViagemChecklistClienteService;

@Service
public class TipoViagemChecklistClienteServiceImpl implements TipoViagemChecklistClienteService {

    private final TipoViagemChecklistClienteRepository tipoViagemRepository;

    @Autowired
    public TipoViagemChecklistClienteServiceImpl(TipoViagemChecklistClienteRepository tipoViagemRepository) {
        this.tipoViagemRepository = tipoViagemRepository;
    }

    @Override
    @Transactional
    public TipoViagemChecklistCliente criarTipoViagem(String nomeTipoViagem) {
        TipoViagemChecklistCliente tipoViagem = new TipoViagemChecklistCliente();
        tipoViagem.setNomeTipoViagem(nomeTipoViagem);

        return tipoViagemRepository.save(tipoViagem);
    }
    
    @Override
    public List<TipoViagemChecklistCliente> buscarTodosTiposViagem() {
        return tipoViagemRepository.findAll();
    }

    @Override
    public TipoViagemChecklistCliente buscarTipoViagemPorId(UUID codigoTipoViagem) {
        return tipoViagemRepository.findById(codigoTipoViagem)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de Viagem não encontrado"));
    }
    
    
    @Override
    @Transactional
    public void excluirTipoViagemPorCodigo(UUID codigoTipoViagem) {
        tipoViagemRepository.deleteByCodigoTipoViagem(codigoTipoViagem);
    }
}

package com.igor.minhasfinancas.service.imple;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igor.minhasfinancas.exception.TipoViagemNotFoundException;
import com.igor.minhasfinancas.model.entity.TipoViagemChecklistCliente;
import com.igor.minhasfinancas.model.repository.TipoViagemChecklistClienteRepository;
import com.igor.minhasfinancas.service.TipoViagemChecklistClienteService;

@Service
public class TipoViagemChecklistClienteServiceImpl implements TipoViagemChecklistClienteService {

    private final TipoViagemChecklistClienteRepository tipoViagemRepository;
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(TipoViagemChecklistClienteServiceImpl.class);

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
        Optional<TipoViagemChecklistCliente> tipoViagemOptional = tipoViagemRepository.findById(codigoTipoViagem);

        if (tipoViagemOptional.isPresent()) {
            return tipoViagemOptional.get();
        } else {
            throw new TipoViagemNotFoundException("Tipo de Viagem não encontrada para o ID: " + codigoTipoViagem);
        }
    }
    
    
    @Override
    @Transactional
    public void excluirTipoViagemPorCodigo(UUID codigoTipoViagem) {
        TipoViagemChecklistCliente tipoViagem = tipoViagemRepository.findById(codigoTipoViagem)
                .orElseThrow(() -> new TipoViagemNotFoundException(codigoTipoViagem));

        tipoViagemRepository.delete(tipoViagem);
    }
}

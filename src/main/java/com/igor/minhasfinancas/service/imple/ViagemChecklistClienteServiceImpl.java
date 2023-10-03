package com.igor.minhasfinancas.service.imple;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.igor.minhasfinancas.api.dto.ChecklistCustomizadoDTO;
import com.igor.minhasfinancas.api.dto.ChecklistDTO;
import com.igor.minhasfinancas.api.dto.ItinerarioDTO;
import com.igor.minhasfinancas.api.dto.TipoViagemDTO;
import com.igor.minhasfinancas.api.dto.ViagemChecklistClienteDTO;
import com.igor.minhasfinancas.api.dto.ViagemDTO;
import com.igor.minhasfinancas.exception.TipoViagemNotFoundException;
import com.igor.minhasfinancas.exception.ViagemNotFoundException;
import com.igor.minhasfinancas.model.entity.Checklist;
import com.igor.minhasfinancas.model.entity.ChecklistCustomizado;
import com.igor.minhasfinancas.model.entity.Itinerario;
import com.igor.minhasfinancas.model.entity.TipoViagemChecklistCliente;
import com.igor.minhasfinancas.model.entity.ViagemChecklistCliente;
import com.igor.minhasfinancas.model.repository.ChecklistCustomizadoRepository;
import com.igor.minhasfinancas.model.repository.ChecklistRepository;
import com.igor.minhasfinancas.model.repository.ItinerarioRepository;
import com.igor.minhasfinancas.model.repository.TipoViagemChecklistClienteRepository;
import com.igor.minhasfinancas.model.repository.ViagemChecklistClienteRepository;
import com.igor.minhasfinancas.service.ViagemChecklistClienteService;

@Service
public class ViagemChecklistClienteServiceImpl implements ViagemChecklistClienteService {

    private final ViagemChecklistClienteRepository viagemChecklistClienteRepository;
    private final TipoViagemChecklistClienteRepository tipoViagemChecklistClienteRepository;
    private final ItinerarioRepository itinerarioRepository;
    private final ChecklistRepository checklistRepository;
    private final ChecklistCustomizadoRepository checklistCustomizadoRepository;

    @Autowired
    public ViagemChecklistClienteServiceImpl(
            ViagemChecklistClienteRepository viagemChecklistClienteRepository,
            TipoViagemChecklistClienteRepository tipoViagemChecklistClienteRepository,
            ItinerarioRepository itinerarioRepository,
            ChecklistRepository checklistRepository,
            ChecklistCustomizadoRepository checklistCustomizadoRepository) {
        this.viagemChecklistClienteRepository = viagemChecklistClienteRepository;
        this.tipoViagemChecklistClienteRepository = tipoViagemChecklistClienteRepository;
        this.itinerarioRepository = itinerarioRepository;
        this.checklistRepository = checklistRepository;
        this.checklistCustomizadoRepository = checklistCustomizadoRepository;
    }

    @Override
    public ViagemChecklistCliente criarViagemChecklist(ViagemChecklistClienteDTO viagemChecklistDTO) {
        ViagemChecklistCliente viagemChecklist = new ViagemChecklistCliente();
        viagemChecklist.setNomeViagemChecklist(viagemChecklistDTO.getNomeViagemChecklist());
        viagemChecklist.setDataViagemChecklistIda(viagemChecklistDTO.getDataViagemChecklistIda());
        viagemChecklist.setDataViagemChecklistVolta(viagemChecklistDTO.getDataViagemChecklistVolta());
        viagemChecklist.setCodigoIdentificacaoPessoa(viagemChecklistDTO.getCodigoIdentificacaoPessoa());
        viagemChecklist.setDataHoraInclusaoRegistro(LocalDateTime.now());
        
     
        
        UUID tipoViagemId = viagemChecklistDTO.getTipoViagemId();
        if (tipoViagemId != null) {
            TipoViagemChecklistCliente tipoViagem = tipoViagemChecklistClienteRepository.getOne(tipoViagemId);
            viagemChecklist.setTipoViagem(tipoViagem);
        } else {
            // Lida com o caso em que o ID do tipo de viagem é nulo, isso pode ser um erro ou um comportamento esperado
            // lança uma exceção, definir um valor padrão ou realizar outra ação adequada.
        	throw new TipoViagemNotFoundException(null);
        }

        return viagemChecklistClienteRepository.save(viagemChecklist);
    }

    @Override
    public List<ViagemChecklistCliente> buscarTodasViagensChecklist() {
        return viagemChecklistClienteRepository.findAll();
    }
    
    @Override
    public ViagemDTO buscarViagemPorId(UUID id) {
        ViagemChecklistCliente viagem = viagemChecklistClienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viagem não encontrada"));

        // Mapear a entidade ViagemChecklistCliente para ViagemDTO
        ViagemDTO viagemDTO = new ViagemDTO();
        viagemDTO.setCodigoViagemChecklist(viagem.getCodigoViagemChecklist());
        viagemDTO.setNomeViagemChecklist(viagem.getNomeViagemChecklist());
        viagemDTO.setDataViagemChecklistIda(viagem.getDataViagemChecklistIda());
        viagemDTO.setDataViagemChecklistVolta(viagem.getDataViagemChecklistVolta());
        viagemDTO.setCodigoIdentificacaoPessoa(viagem.getCodigoIdentificacaoPessoa());

        // Mapear o TipoViagemChecklistCliente para TipoViagemDTO
        TipoViagemChecklistCliente tipoViagem = viagem.getTipoViagem();
        TipoViagemDTO tipoViagemDTO = new TipoViagemDTO();
        tipoViagemDTO.setCodigoTipoViagem(tipoViagem.getCodigoTipoViagem());
        tipoViagemDTO.setNomeTipoViagem(tipoViagem.getNomeTipoViagem());

        viagemDTO.setTipoViagem(tipoViagemDTO);

        // Mapear os Itinerarios para ItinerarioDTOs
        List<ItinerarioDTO> itinerarioDTOs = new ArrayList<>();
        for (Itinerario itinerario : viagem.getItinerarios()) {
            ItinerarioDTO itinerarioDTO = new ItinerarioDTO();
            itinerarioDTO.setCodigoChecklistItinerario(itinerario.getCodigoChecklistItinerario());
            // Mapear os outros campos do ItinerarioDTO
            itinerarioDTOs.add(itinerarioDTO);
        }
        viagemDTO.setItinerarios(itinerarioDTOs);

        return viagemDTO;
    }






    
    @Override
	public ViagemChecklistCliente atualizarViagem(UUID id, ViagemChecklistClienteDTO viagemChecklistDTO) {
    	 ViagemChecklistCliente viagemChecklist = viagemChecklistClienteRepository.findById(id)
                 .orElseThrow(() -> new RuntimeException("Viagem não encontrada"));

         // Atualize os campos da viagem com base nos dados do DTO
         viagemChecklist.setNomeViagemChecklist(viagemChecklistDTO.getNomeViagemChecklist());
         viagemChecklist.setDataViagemChecklistIda(viagemChecklistDTO.getDataViagemChecklistIda());
         viagemChecklist.setDataViagemChecklistVolta(viagemChecklistDTO.getDataViagemChecklistVolta());
         viagemChecklist.setCodigoIdentificacaoPessoa(viagemChecklistDTO.getCodigoIdentificacaoPessoa());
         viagemChecklist.setDataHoraManutencaoRegistro(LocalDateTime.now());

        
         UUID tipoViagemId = viagemChecklistDTO.getTipoViagemId();
         if (tipoViagemId != null) {
             TipoViagemChecklistCliente tipoViagem = tipoViagemChecklistClienteRepository.getOne(tipoViagemId);
             viagemChecklist.setTipoViagem(tipoViagem);
         } else {
             // Lida com o caso em que o ID do tipo de viagem é nulo, isso pode ser um erro ou um comportamento esperado
             // lança uma exceção, definir um valor padrão ou realizar outra ação adequada.
         	throw new TipoViagemNotFoundException(null);
         }

         // Salve a viagem atualizada no banco de dados
         ViagemChecklistCliente viagemAtualizada = viagemChecklistClienteRepository.save(viagemChecklist);
         return viagemAtualizada;
	}

	@Override
	@Transactional
	public void excluirViagem(UUID codigoViagemChecklist) {
		// Verifique se a viagem com o ID especificado existe
	    Optional<ViagemChecklistCliente> viagemOptional = viagemChecklistClienteRepository.findById(codigoViagemChecklist);
	    
	    if (viagemOptional.isEmpty()) {
	        throw new ViagemNotFoundException("Viagem não encontrada com o ID: " + codigoViagemChecklist);
	    }

	    // Obtenha a viagem do Optional
	    ViagemChecklistCliente viagem = viagemOptional.get();

	    // Verifique se a viagem está associada a um tipo de viagem
	    TipoViagemChecklistCliente tipoViagem = viagem.getTipoViagem();
	    if (tipoViagem != null) {
	        // Se a viagem estiver associada a um tipo de viagem, atualize-a para que não haja mais referência
	        viagem.setTipoViagem(null);
	    }

	    // Exclua a viagem com o ID especificado
	    viagemChecklistClienteRepository.deleteById(codigoViagemChecklist);
		
	}
    
	/*
	 * @Override public Itinerario criarItinerario(UUID codigoViagemChecklist,
	 * ItinerarioDTO itinerarioDTO) { ViagemChecklistCliente viagemChecklist =
	 * viagemChecklistClienteRepository.findById(codigoViagemChecklist)
	 * .orElseThrow(() -> new RuntimeException("Viagem não encontrada"));
	 * 
	 * Itinerario itinerario = new Itinerario();
	 * itinerario.setCodigoSiglaPaisOrigem(itinerarioDTO.getCodigoSiglaPaisOrigem())
	 * ;
	 * itinerario.setCodigoSiglaPaisDestino(itinerarioDTO.getCodigoSiglaPaisDestino(
	 * ));
	 * itinerario.setCodigoLocalidadeDiretorioNacionalEnderecoOrigem(itinerarioDTO.
	 * getCodigoLocalidadeDiretorioNacionalEnderecoOrigem());
	 * itinerario.setCodigoLocalidadeDiretorioNacionalEnderecoDestino(itinerarioDTO.
	 * getCodigoLocalidadeDiretorioNacionalEnderecoDestino());
	 * 
	 * itinerario.setViagemChecklist(viagemChecklist);
	 * 
	 * return itinerarioRepository.save(itinerario); }
	 */

    @Override
    public Checklist criarChecklist(UUID codigoViagemChecklist, ChecklistDTO checklistDTO) {
    	 ViagemChecklistCliente viagemChecklist = viagemChecklistClienteRepository.findById(codigoViagemChecklist)
    	            .orElseThrow(() -> new RuntimeException("Viagem não encontrada"));

    	    Checklist checklist = new Checklist();
    	    checklist.setCodigoExternoCategoria(checklistDTO.getCodigoExternoCategoria());
    	    checklist.setCodigoExternoItemChecklist(checklistDTO.getCodigoExternoItemChecklist());
    	    checklist.setDataHoraInclusaoRegistro(new Date());
    	    checklist.setDataHoraManutencaoRegistro(new Date());
    	    checklist.setIndicadorItemChecklist(checklistDTO.getIndicadorItemChecklist());

    	    // Associe o checklist à viagem
    	    checklist.setViagemChecklist(viagemChecklist);

    	    return checklistRepository.save(checklist);
    }

    @Override
    public ChecklistCustomizado criarChecklistCustomizado(UUID codigoViagemChecklist, ChecklistCustomizadoDTO checklistCustomizadoDTO) {
        ViagemChecklistCliente viagemChecklist = viagemChecklistClienteRepository.findById(codigoViagemChecklist)
                .orElseThrow(() -> new RuntimeException("Viagem não encontrada"));

        ChecklistCustomizado checklistCustomizado = new ChecklistCustomizado();
        checklistCustomizado.setDescricaoChecklistCustomizado(checklistCustomizadoDTO.getDescricaoChecklistCustomizado());
        checklistCustomizado.setDataHoraInclusaoRegistro(new Date());
        checklistCustomizado.setDataHoraManutencaoRegistro(new Date());

        // Associe o checklist customizado à viagem
        checklistCustomizado.setViagemChecklist(viagemChecklist);

        return checklistCustomizadoRepository.save(checklistCustomizado);
    }

	@Override
	public TipoViagemChecklistCliente buscarTipoViagemPorId(UUID tipoViagemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Itinerario criarItinerario(UUID codigoViagemChecklist, ItinerarioDTO itinerarioDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ViagemDTO> buscarTodasViagensChecklistPessoa(String codigoIdentificacaoPessoa) {
	    List<ViagemChecklistCliente> viagens = viagemChecklistClienteRepository.findByCodigoIdentificacaoPessoa(codigoIdentificacaoPessoa);
	    List<ViagemDTO> viagensDTO = new ArrayList<>();

	    for (ViagemChecklistCliente viagem : viagens) {
	        ViagemDTO viagemDTO = new ViagemDTO();
	        viagemDTO.setCodigoViagemChecklist(viagem.getCodigoViagemChecklist());
	        viagemDTO.setNomeViagemChecklist(viagem.getNomeViagemChecklist());
	        viagemDTO.setDataViagemChecklistIda(viagem.getDataViagemChecklistIda());
	        viagemDTO.setDataViagemChecklistVolta(viagem.getDataViagemChecklistVolta());
	        viagemDTO.setCodigoIdentificacaoPessoa(viagem.getCodigoIdentificacaoPessoa());

	        TipoViagemChecklistCliente tipoViagem = viagem.getTipoViagem();
	        TipoViagemDTO tipoViagemDTO = new TipoViagemDTO();
	        tipoViagemDTO.setCodigoTipoViagem(tipoViagem.getCodigoTipoViagem());
	        tipoViagemDTO.setNomeTipoViagem(tipoViagem.getNomeTipoViagem());

	        viagemDTO.setTipoViagem(tipoViagemDTO);

	        List<ItinerarioDTO> itinerarioDTOs = new ArrayList<>();
	        for (Itinerario itinerario : viagem.getItinerarios()) {
	            ItinerarioDTO itinerarioDTO = new ItinerarioDTO();
	            itinerarioDTO.setCodigoChecklistItinerario(itinerario.getCodigoChecklistItinerario());
	            // Mapear os outros campos do ItinerarioDTO
	            itinerarioDTOs.add(itinerarioDTO);
	        }
	        viagemDTO.setItinerarios(itinerarioDTOs);

	        viagensDTO.add(viagemDTO);
	    }

	    return viagensDTO;
	}
	
	

}

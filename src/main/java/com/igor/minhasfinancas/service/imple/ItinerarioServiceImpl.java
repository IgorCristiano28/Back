package com.igor.minhasfinancas.service.imple;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igor.minhasfinancas.api.dto.ItinerarioDTO;
import com.igor.minhasfinancas.api.dto.ViagemChecklistClienteDTO;
import com.igor.minhasfinancas.api.dto.ViagemDTO;
import com.igor.minhasfinancas.exception.ItinerarioNotFoundException;
import com.igor.minhasfinancas.exception.TipoViagemNotFoundException;
import com.igor.minhasfinancas.exception.ViagemNotFoundException;
import com.igor.minhasfinancas.model.entity.Itinerario;
import com.igor.minhasfinancas.model.entity.TipoViagemChecklistCliente;
import com.igor.minhasfinancas.model.entity.ViagemChecklistCliente;
import com.igor.minhasfinancas.model.repository.ItinerarioRepository;
import com.igor.minhasfinancas.model.repository.ViagemChecklistClienteRepository;
import com.igor.minhasfinancas.service.ItinerarioService;
import com.igor.minhasfinancas.service.ViagemChecklistClienteService;

@Service
public class ItinerarioServiceImpl implements ItinerarioService {
	
	    private final ItinerarioRepository itinerarioRepository;
	    private final ViagemChecklistClienteRepository viagemChecklistClienteRepository;
	    private final ViagemChecklistClienteService viagemChecklistClienteService;

	    @Autowired
	    public ItinerarioServiceImpl(
	            ViagemChecklistClienteRepository viagemChecklistClienteRepository,
	            ItinerarioRepository itinerarioRepository,
	            ViagemChecklistClienteService viagemChecklistClienteService
	    ) {
	        this.viagemChecklistClienteRepository = viagemChecklistClienteRepository;
	        this.itinerarioRepository = itinerarioRepository;
	        this.viagemChecklistClienteService = viagemChecklistClienteService;
	    }

	    @Override
	    public ItinerarioDTO criarItinerario(ItinerarioDTO itinerarioDTO) {
	        // Implemente a lógica para criar um itinerário a partir do DTO
	        Itinerario itinerario = new Itinerario();
	        BeanUtils.copyProperties(itinerarioDTO, itinerario);
	        // Configurar outros atributos do itinerário, se necessário
	        Itinerario itinerarioSalvo = itinerarioRepository.save(itinerario);
	        ItinerarioDTO itinerarioSalvoDTO = new ItinerarioDTO();
	        BeanUtils.copyProperties(itinerarioSalvo, itinerarioSalvoDTO);
	        return itinerarioSalvoDTO;
	    }

			@Override
			public Itinerario criarItinerarioAPartirDeViagem(UUID viagemId, ItinerarioDTO itinerarioDTO) {
			    if (viagemId == null || itinerarioDTO == null) {
			        throw new IllegalArgumentException("viagemId e itinerarioDTO não podem ser nulos.");
			    }

			    // Verifique se a viagem existe
			    ViagemDTO viagem = viagemChecklistClienteService.buscarViagemPorId(viagemId);

			    if (viagem == null) {
			        throw new ViagemNotFoundException("Viagem com ID " + viagemId + " não encontrada.");
			    }

			    // Crie o itinerário a partir do DTO
			    Itinerario itinerario = new Itinerario();
			    itinerario.setCodigoSiglaPaisOrigem(itinerarioDTO.getCodigoSiglaPaisOrigem());
			    itinerario.setCodigoSiglaPaisDestino(itinerarioDTO.getCodigoSiglaPaisDestino());
			    itinerario.setCodigoLocalidadeDiretorioNacionalEnderecoOrigem(itinerarioDTO.getCodigoLocalidadeDiretorioNacionalEnderecoOrigem());
			    itinerario.setCodigoLocalidadeDiretorioNacionalEnderecoDestino(itinerarioDTO.getCodigoLocalidadeDiretorioNacionalEnderecoDestino());

			    // Ajuste a data e hora, se necessário
			    itinerario.setDataHoraInclusaoRegistro(LocalDateTime.now()); // ou itinerarioDTO.getDataHoraInclusaoRegistro(), se for fornecida

			    // Associe o itinerário à viagem
			    itinerario.setCodigoViagemChecklist(viagem.getCodigoViagemChecklist());

			    // Salve o novo itinerário no banco de dados usando o repositório ItinerarioRepository
			    return itinerarioRepository.save(itinerario);
			}

		@Override
		public Itinerario atualizarItinerario(UUID itinerarioId, ItinerarioDTO itinerarioDTO) {
			// Verifique se o itinerário com o ID fornecido existe
		    Itinerario itinerario = itinerarioRepository.findById(itinerarioId)
		            .orElseThrow(() -> new RuntimeException("Itinerário não encontrado"));

		    // Verifique se cada campo no DTO é fornecido e atualize apenas os campos não nulos
		    if (itinerarioDTO.getCodigoSiglaPaisOrigem() != null) {
		        itinerario.setCodigoSiglaPaisOrigem(itinerarioDTO.getCodigoSiglaPaisOrigem());
		    }
		    if (itinerarioDTO.getCodigoSiglaPaisDestino() != null) {
		        itinerario.setCodigoSiglaPaisDestino(itinerarioDTO.getCodigoSiglaPaisDestino());
		    }
		    if (itinerarioDTO.getCodigoLocalidadeDiretorioNacionalEnderecoOrigem() != null) {
		        itinerario.setCodigoLocalidadeDiretorioNacionalEnderecoOrigem(itinerarioDTO.getCodigoLocalidadeDiretorioNacionalEnderecoOrigem());
		    }
		    if (itinerarioDTO.getCodigoLocalidadeDiretorioNacionalEnderecoDestino() != null) {
		        itinerario.setCodigoLocalidadeDiretorioNacionalEnderecoDestino(itinerarioDTO.getCodigoLocalidadeDiretorioNacionalEnderecoDestino());
		    }
		    
		    itinerario.setDataHoraManutencaoRegistro(LocalDateTime.now());
		    // Continue atualizando outros campos conforme necessário

		    // Salve o itinerário atualizado no banco de dados
		    Itinerario itinerarioAtualizado = itinerarioRepository.save(itinerario);

		    
		    return itinerarioAtualizado; 
		}

		@Override
		public void excluirItinerario(UUID codigoChecklistItinerario) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public ItinerarioDTO buscarItinerarioPorId(UUID codigoChecklistItinerario) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<ItinerarioDTO> buscarItinerariosPorViagem(UUID codigoViagemChecklist) {
			// TODO Auto-generated method stub
			return null;
		}

	   
	}
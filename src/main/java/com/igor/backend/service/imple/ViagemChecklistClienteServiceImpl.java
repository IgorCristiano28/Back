
package com.igor.backend.service.imple;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.igor.backend.api.dto.ChecklistCrudDTO;
import com.igor.backend.api.dto.ChecklistCustomizadoDTO;
import com.igor.backend.api.dto.ChecklistDTO;
import com.igor.backend.api.dto.ChecklistDTOCMS;
import com.igor.backend.api.dto.ChecklistItemDTO;
import com.igor.backend.api.dto.ItinerarioDTO;
import com.igor.backend.api.dto.TipoViagemDTO;
import com.igor.backend.api.dto.ViagemChecklistClienteDTO;
import com.igor.backend.api.dto.ViagemDTO;
import com.igor.backend.exception.ViagemNotFoundException;
import com.igor.backend.model.entity.Checklist;
import com.igor.backend.model.entity.ChecklistApiResponse;
import com.igor.backend.model.entity.ChecklistCustomizado;
import com.igor.backend.model.entity.ChecklistEntry;
import com.igor.backend.model.entity.ChecklistItem;
import com.igor.backend.model.entity.Entry;
import com.igor.backend.model.entity.Itinerario;
import com.igor.backend.model.entity.TipoViagemChecklistCliente;
import com.igor.backend.model.entity.ViagemChecklistCliente;
import com.igor.backend.model.repository.ChecklistCustomizadoRepository;
import com.igor.backend.model.repository.ChecklistRepository;
import com.igor.backend.model.repository.ItinerarioRepository;
import com.igor.backend.model.repository.TipoViagemChecklistClienteRepository;
import com.igor.backend.model.repository.ViagemChecklistClienteRepository;
import com.igor.backend.service.ChecklistService;
import com.igor.backend.service.ViagemChecklistClienteService;

@Service
public class ViagemChecklistClienteServiceImpl implements ViagemChecklistClienteService {

	@Value("${api.url}") // Use esta anotação para injetar a URL da API a partir do arquivo de propriedades.
    private String apiUrl;
	
    private final ViagemChecklistClienteRepository viagemChecklistClienteRepository;
    private final TipoViagemChecklistClienteRepository tipoViagemChecklistClienteRepository;
    private final ItinerarioRepository itinerarioRepository;
    private final ChecklistRepository checklistRepository;
    private final ChecklistCustomizadoRepository checklistCustomizadoRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    
    
    @Autowired
    public ViagemChecklistClienteServiceImpl(
            ViagemChecklistClienteRepository viagemChecklistClienteRepository,
            TipoViagemChecklistClienteRepository tipoViagemChecklistClienteRepository,
            ItinerarioRepository itinerarioRepository,
            ChecklistRepository checklistRepository,
            ChecklistCustomizadoRepository checklistCustomizadoRepository,
            RestTemplate restTemplate,
            ObjectMapper objectMapper) {
        this.viagemChecklistClienteRepository = viagemChecklistClienteRepository;
        this.tipoViagemChecklistClienteRepository = tipoViagemChecklistClienteRepository;
        this.itinerarioRepository = itinerarioRepository;
        this.checklistRepository = checklistRepository;
        this.checklistCustomizadoRepository = checklistCustomizadoRepository;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
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
        	//throw new TipoViagemNotFoundException(null);
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
            itinerarioDTOs.add(itinerarioDTO);
        }
        viagemDTO.setItinerarios(itinerarioDTOs);

        List<ChecklistDTO> checklistDTOs = new ArrayList<>();
        for (Checklist checklist : viagem.getChecklists()) {
            if (checklist.getIndicadorItemChecklist() != null && checklist.getIndicadorItemChecklist() == 1) {
                ChecklistDTO checklistDTO = new ChecklistDTO();
                checklistDTO.setCodigoChecklist(checklist.getCodigoChecklist());
                checklistDTO.setCodigoExternoCategoria(checklist.getCodigoExternoCategoria());
                checklistDTO.setCodigoExternoItemChecklist(checklist.getCodigoExternoItemChecklist());
                checklistDTO.setDataHoraInclusaoRegistro(checklist.getDataHoraInclusaoRegistro());
                checklistDTO.setDataHoraManutencaoRegistro(checklist.getDataHoraManutencaoRegistro());
                checklistDTO.setIndicadorItemChecklist(
                        checklist.getIndicadorItemChecklist() != null && checklist.getIndicadorItemChecklist() == 1
                );
                checklistDTOs.add(checklistDTO);
            }
        }
        viagemDTO.setChecklists(checklistDTOs);

        if (viagemDTO.getChecklists() != null && !viagemDTO.getChecklists().isEmpty()) {
            String codigoExternoCategoria = viagemDTO.getChecklists().get(0).getCodigoExternoCategoria();
            String codigoExternoItemChecklist = viagemDTO.getChecklists().get(0).getCodigoExternoItemChecklist();
            List<ChecklistDTOCMS> checklistDTOCMSList = consultarCMS();

            // Filtrar os checklists da API que correspondem ao códigoExternoCategoria e codigoExternoItemChecklist
            List<ChecklistDTOCMS> checklistsFiltrados = checklistDTOCMSList.stream()
                    .filter(checklistDTO ->
                            checklistDTO.getChecklistUid().equals(codigoExternoCategoria) &&
                                    checklistDTO.getChecklistItems() != null &&
                                    checklistDTO.getChecklistItems().stream()
                                            .anyMatch(item ->
                                                    item.getChecklistItemUid().equals(codigoExternoItemChecklist)
                                            )
                    )
                    .collect(Collectors.toList());

            // Adicionar diretamente à lista se a condição for atendida
            viagemDTO.setChecklistsCMS(checklistsFiltrados);
        } else {
            viagemDTO.setChecklistsCMS(new ArrayList<>());
        }

        return viagemDTO;
    }

   

	/*
	 * private ChecklistDTO mapChecklistDTOFromCMS(ChecklistDTOCMS checklistDTOCMS)
	 * { ChecklistDTO checklistDTO = new ChecklistDTO();
	 * checklistDTO.setUid(checklistDTOCMS.getUid());
	 * 
	 * // Verifica se há itens de checklist na resposta da API if
	 * (checklistDTOCMS.getChecklistItems() != null &&
	 * !checklistDTOCMS.getChecklistItems().isEmpty()) { // Pega o uid do primeiro
	 * item do checklist (ajuste conforme a estrutura real da sua API)
	 * checklistDTO.setChecklistItemUid(checklistDTOCMS.getChecklistItems().get(0).
	 * getUid()); } else { // Define um valor padrão ou lida com o caso em que não
	 * há itens de checklist checklistDTO.setChecklistItemUid("N/A"); }
	 * 
	 * // Define a data de atualização (ajuste conforme a estrutura real da sua API)
	 * checklistDTO.setUpdatedAt(checklistDTOCMS.getUpdatedAt());
	 * 
	 * // Mapeie outros campos conforme necessário
	 * 
	 * return checklistDTO; }
	 */

    
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
         	//throw new TipoViagemNotFoundException(null);
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

	@Override
	public List<ChecklistDTOCMS> consultarCMS() {
	    HttpHeaders headers = new HttpHeaders();
	    headers.set("api_key", "blt8fdf83f8000a1761");
	    headers.set("access_token", "cs56e50dee82937b8425559811");

	    HttpEntity<String> entity = new HttpEntity<>(headers);

	    ResponseEntity<ChecklistApiResponse> response = new RestTemplate().exchange(
	            apiUrl,
	            HttpMethod.GET,
	            entity,
	            ChecklistApiResponse.class
	    );

	    if (response.getStatusCode().equals(HttpStatus.OK)) {
	        ChecklistApiResponse apiResponse = response.getBody();
	        if (apiResponse != null && apiResponse.getEntries() != null && !apiResponse.getEntries().isEmpty()) {
	            return apiResponse.getEntries();
	        } else {
                throw new RuntimeException("Resposta da API está vazia ou mal formatada.");
            }
        } else {
            throw new RuntimeException("Erro ao consultar a API: " + response.getStatusCodeValue());
        }
    }
	
	
	private ChecklistItemDTO mapToChecklistItemDTO(ChecklistItem item) {
        // Mapeie os dados do item da API para o seu DTO
        // Por exemplo:
        ChecklistItemDTO checklistItemDTO = new ChecklistItemDTO();
        checklistItemDTO.setUid(item.getUid());
        checklistItemDTO.setContentTypeUid(item.getContentTypeUid());
        
        return checklistItemDTO;
    }

	@Override
	public Checklist criarChecklist(UUID codigoViagemChecklist, ChecklistDTO checklistDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
	
	

package com.igor.minhasfinancas.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.igor.backend.api.dto.ItinerarioDTO;
import com.igor.backend.api.dto.ViagemDTO;
import com.igor.backend.exception.ViagemNotFoundException;
import com.igor.backend.model.entity.Itinerario;
import com.igor.backend.model.repository.ItinerarioRepository;
import com.igor.backend.service.ViagemChecklistClienteService;
import com.igor.backend.service.imple.ItinerarioServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ItinerarioServiceImplTest {

    @InjectMocks
    private ItinerarioServiceImpl itinerarioService;

    @Mock
    private ItinerarioRepository itinerarioRepository;

    @Mock
    private ViagemChecklistClienteService viagemChecklistClienteService;
    
    

    @Test
    public void testCriarItinerario() {
        // Crie um objeto ItinerarioDTO simulado para o teste
        ItinerarioDTO itinerarioDTO = new ItinerarioDTO();
        itinerarioDTO.setCodigoSiglaPaisOrigem("BR");
        itinerarioDTO.setCodigoSiglaPaisDestino("US");

        // Crie um objeto Itinerario simulado para o retorno do repositório
        Itinerario itinerarioSalvo = new Itinerario();
        itinerarioSalvo.setCodigoSiglaPaisOrigem("BR");
        itinerarioSalvo.setCodigoSiglaPaisDestino("US");

        // Simule o comportamento do repositório
        when(itinerarioRepository.save(any(Itinerario.class))).thenReturn(itinerarioSalvo);

        // Execute o método de serviço
        ItinerarioDTO resultado = itinerarioService.criarItinerario(itinerarioDTO);

        // Verifique se o método save do repositório foi chamado
        verify(itinerarioRepository, times(1)).save(any(Itinerario.class));

        // Verifique se os valores foram copiados corretamente para o DTO de retorno
        assertEquals("BR", resultado.getCodigoSiglaPaisOrigem());
        assertEquals("US", resultado.getCodigoSiglaPaisDestino());
    }

    @Test
    public void testAtualizarItinerario() {
        UUID itinerarioId = UUID.randomUUID();
        ItinerarioDTO itinerarioDTO = new ItinerarioDTO();
        itinerarioDTO.setCodigoSiglaPaisOrigem("BR");
        itinerarioDTO.setCodigoSiglaPaisDestino("US");

        // Simule o comportamento do repositório para buscar o itinerário existente
        Itinerario itinerarioExistente = new Itinerario();
        itinerarioExistente.setCodigoChecklistItinerario(itinerarioId);
        itinerarioExistente.setCodigoSiglaPaisOrigem("BR");
        itinerarioExistente.setCodigoSiglaPaisDestino("CA");

        when(itinerarioRepository.findById(itinerarioId)).thenReturn(Optional.of(itinerarioExistente));

        // Simule o comportamento do repositório ao salvar o itinerário atualizado
        when(itinerarioRepository.save(any(Itinerario.class))).thenAnswer(invocation -> {
            Itinerario itinerarioAtualizado = invocation.getArgument(0);
            return itinerarioAtualizado;
        });

        // Execute o método de serviço
        Itinerario itinerarioAtualizado = itinerarioService.atualizarItinerario(itinerarioId, itinerarioDTO);

        // Verifique se o itinerário foi atualizado corretamente
        assertEquals("US", itinerarioAtualizado.getCodigoSiglaPaisDestino());
    }

    @Test
    public void testExcluirItinerario() {
        UUID itinerarioId = UUID.randomUUID();

        // Simule o comportamento do repositório para buscar o itinerário existente
        Itinerario itinerarioExistente = new Itinerario();
        itinerarioExistente.setCodigoChecklistItinerario(itinerarioId);

        when(itinerarioRepository.findById(itinerarioId)).thenReturn(Optional.of(itinerarioExistente));

        // Execute o método de serviço
        itinerarioService.excluirItinerario(itinerarioId);

        // Verifique se o método delete do repositório foi chamado
        verify(itinerarioRepository, times(1)).delete(itinerarioExistente);
    }
    
    @Test
    public void testCriarItinerarioAPartirDeViagem() {
        // Criar um UUID simulado para a viagem
        UUID viagemId = UUID.randomUUID();

        // Criar um objeto DTO simulado para o itinerário
        ItinerarioDTO itinerarioDTO = new ItinerarioDTO();
        itinerarioDTO.setCodigoSiglaPaisOrigem("BR");
        itinerarioDTO.setCodigoSiglaPaisDestino("US");

        // Criar um objeto DTO simulado para a viagem
        ViagemDTO viagemDTO = new ViagemDTO();
        viagemDTO.setCodigoViagemChecklist(viagemId);

        // Configurar o comportamento do serviço de viagem simulado
        when(viagemChecklistClienteService.buscarViagemPorId(viagemId)).thenReturn(viagemDTO);

        // Criar um objeto simulado para o itinerário após a criação
        Itinerario itinerarioSalvo = new Itinerario();
        itinerarioSalvo.setCodigoSiglaPaisOrigem("BR");
        itinerarioSalvo.setCodigoSiglaPaisDestino("US");

        // Configurar o comportamento do repositório simulado
        when(itinerarioRepository.save(any(Itinerario.class))).thenReturn(itinerarioSalvo);

        // Executar o método de serviço
        Itinerario itinerarioResultado = itinerarioService.criarItinerarioAPartirDeViagem(viagemId, itinerarioDTO);

        // Verificar se o serviço de viagem foi chamado com o ID da viagem
        verify(viagemChecklistClienteService, times(1)).buscarViagemPorId(viagemId);

        // Verificar se o método save do repositório foi chamado
        verify(itinerarioRepository, times(1)).save(any(Itinerario.class));

        // Verificar se o itinerário retornado é o esperado
        assertEquals("BR", itinerarioResultado.getCodigoSiglaPaisOrigem());
        assertEquals("US", itinerarioResultado.getCodigoSiglaPaisDestino());
    }
    
    @Test(expected = ViagemNotFoundException.class)
    public void testCriarItinerarioAPartirDeViagemViagemNaoEncontrada() {
        // Criar um UUID simulado para a viagem
        UUID viagemId = UUID.randomUUID();

        // Configurar o comportamento do serviço de viagem simulado para retornar null (viagem não encontrada)
        when(viagemChecklistClienteService.buscarViagemPorId(viagemId)).thenReturn(null);

        // Executar o método de serviço, o que deve resultar em uma ViagemNotFoundException
        itinerarioService.criarItinerarioAPartirDeViagem(viagemId, new ItinerarioDTO());
    }
    
	/*
	 * @Test public void testCriarItinerarioAPartirDeViagemNaoEncontrada() { //
	 * Preparação do cenário UUID viagemId = UUID.randomUUID(); ItinerarioDTO
	 * itinerarioDTO = new ItinerarioDTO();
	 * 
	 * // Configurando o comportamento do serviço de viagem para lançar
	 * ViagemNotFoundException
	 * when(viagemChecklistClienteService.buscarViagemPorId(viagemId)).thenThrow(
	 * ViagemNotFoundException.class);
	 * 
	 * // Execução e verificação assertThrows(ViagemNotFoundException.class, () -> {
	 * itinerarioService.criarItinerarioAPartirDeViagem(viagemId, itinerarioDTO);
	 * });
	 * 
	 * // Verifique se o método buscarViagemPorId foi chamado
	 * verify(viagemChecklistClienteService, times(1)).buscarViagemPorId(viagemId);
	 * 
	 * // Certifique-se de que o repositório de itinerário não tenha sido chamado
	 * verifyZeroInteractions(itinerarioRepository); }
	 */
}

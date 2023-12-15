package com.igor.minhasfinancas.service.imple;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.igor.backend.api.dto.ChecklistDTO;
import com.igor.backend.exception.ChecklistNotFoundException;
import com.igor.backend.exception.ViagemNotFoundException;
import com.igor.backend.model.entity.Checklist;
import com.igor.backend.model.entity.ViagemChecklistCliente;
import com.igor.backend.model.repository.ChecklistRepository;
import com.igor.backend.model.repository.ViagemChecklistClienteRepository;
import com.igor.backend.service.imple.ChecklistServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ChecklistServiceImplTest {
	
	@Mock
    private ViagemChecklistClienteRepository viagemChecklistClienteRepository;

    @Mock
    private ChecklistRepository checklistRepository;

    @InjectMocks
    private ChecklistServiceImpl checklistService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriarChecklistAPartirDeViagem() {
    	 // Criar um UUID simulado para a viagem
        UUID viagemId = UUID.randomUUID();

        // Criar um objeto DTO simulado para o checklist
        ChecklistDTO checklistDTO = new ChecklistDTO();
        checklistDTO.setCodigoExternoCategoria("Categoria");
        checklistDTO.setCodigoExternoItemChecklist("Item");
        checklistDTO.setIndicadorItemChecklist(true);

        // Criar um objeto ViagemChecklistCliente simulado para o mock
        ViagemChecklistCliente viagemMock = new ViagemChecklistCliente();
        // Configure o objeto viagemMock conforme necessário

        // Configurar o comportamento do mock para o método findById
        when(viagemChecklistClienteRepository.findById(eq(viagemId))).thenReturn(Optional.of(viagemMock));

        // Criar um objeto Checklist simulado após a criação
        Checklist checklistSalvo = new Checklist();
        checklistSalvo.setCodigoExternoCategoria("Categoria");
        checklistSalvo.setCodigoExternoItemChecklist("Item");
        checklistSalvo.setIndicadorItemChecklist((short) 1);

        // Configurar o comportamento do mock para o método save
        when(checklistRepository.save(any(Checklist.class))).thenReturn(checklistSalvo);

        // Executar o método de serviço
        Checklist checklistResultado = checklistService.criarChecklistAPartirDeViagem(viagemId, checklistDTO);

        // Verificar se o método findById do repositório foi chamado
        verify(viagemChecklistClienteRepository, times(1)).findById(eq(viagemId));

        // Verificar se o método save do repositório foi chamado
        verify(checklistRepository, times(1)).save(any(Checklist.class));

        // Verificar se o itinerário retornado não é nulo e tem os valores esperados
        assertNotNull(checklistResultado);
        assertEquals("Categoria", checklistResultado.getCodigoExternoCategoria());
        assertEquals("Item", checklistResultado.getCodigoExternoItemChecklist());
        assertEquals((short) 1, checklistResultado.getIndicadorItemChecklist());
    }


    @Test
    void testCriarChecklistComViagemNaoEncontrada() {
        UUID viagemId = UUID.randomUUID();
        ChecklistDTO checklistDTO = new ChecklistDTO();

        // Mock do repositório de viagem que retorna um Optional vazio
        when(viagemChecklistClienteRepository.findById(viagemId)).thenReturn(Optional.empty());

        // Verifica se ViagemNotFoundException é lançada quando a viagem não é encontrada
        assertThrows(ViagemNotFoundException.class,
                () -> checklistService.criarChecklistAPartirDeViagem(viagemId, checklistDTO));

        // Verifica que o método do repositório não foi chamado
        verify(checklistRepository, never()).save(any());
    }
    
    @Test
    void testAtualizarChecklist() {
        // Criar um UUID simulado para o checklist
        UUID checklistId = UUID.randomUUID();

        // Criar um objeto DTO simulado para o checklist
        ChecklistDTO checklistDTO = new ChecklistDTO();
        checklistDTO.setCodigoExternoCategoria("NovaCategoria");
        checklistDTO.setCodigoExternoItemChecklist("NovoItem");
        checklistDTO.setIndicadorItemChecklist(true);

        // Criar um objeto Checklist simulado para o mock
        Checklist checklistExistente = new Checklist();
        checklistExistente.setCodigoExternoCategoria("Categoria");
        checklistExistente.setCodigoExternoItemChecklist("Item");
        checklistExistente.setIndicadorItemChecklist((short) 1);

        // Configurar o comportamento do mock para o método findById
        when(checklistRepository.findById(eq(checklistId))).thenReturn(Optional.of(checklistExistente));

        // Configurar o comportamento do mock para o método save
        when(checklistRepository.save(any(Checklist.class))).thenReturn(checklistExistente);

        // Executar o método de serviço
        Checklist checklistAtualizado = checklistService.atualizarChecklist(checklistId, checklistDTO);

        // Verificar se o método findById do repositório foi chamado
        verify(checklistRepository).findById(eq(checklistId));

        // Verificar se o método save do repositório foi chamado
        verify(checklistRepository).save(any(Checklist.class));

        // Verificar se o checklist retornado não é nulo e tem os valores esperados
        assertNotNull(checklistAtualizado);
        assertEquals("NovaCategoria", checklistAtualizado.getCodigoExternoCategoria());
        assertEquals("NovoItem", checklistAtualizado.getCodigoExternoItemChecklist());
        assertEquals((short) 1, checklistAtualizado.getIndicadorItemChecklist());
    }

    @Test
    void testExcluirChecklist() {
        // Criar um UUID simulado para o checklist
        UUID checklistId = UUID.randomUUID();

        // Configurar o comportamento do mock para o método findById
        when(checklistRepository.findById(eq(checklistId))).thenReturn(Optional.empty());

        // Verificar se ChecklistNotFoundException é lançada quando o checklist não é encontrado
        assertThrows(ChecklistNotFoundException.class, () -> checklistService.excluirChecklist(checklistId));

        // Verificar se o método findById do repositório foi chamado
        verify(checklistRepository).findById(eq(checklistId));

        // Verificar que o método delete do repositório não foi chamado
        verify(checklistRepository, never()).delete(any(Checklist.class));
    }
}

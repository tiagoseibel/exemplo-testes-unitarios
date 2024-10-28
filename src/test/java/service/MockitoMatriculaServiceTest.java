package service;

import domain.Estudante;
import domain.Matricula;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class MockitoMatriculaServiceTest {

    @InjectMocks
    MatriculaOnlineService service;

    @Mock
    ProcessarPagamentoService processarPagamentoService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveMatricular() {

        Estudante estudante = new Estudante();
        estudante.setId(5L);

        when(processarPagamentoService.processarPagamentoPix(estudante, 1500)).thenReturn("PGOK");
        Matricula m = service.registrarMatricula(estudante, "CC1");

        verify(processarPagamentoService, times(1)).processarPagamentoPix(estudante, 1500);

        Assertions.assertEquals(1500, m.getValorMensalidade());
    }

    @Test
    void naoDeveMatricular() {

        Estudante estudante = new Estudante();
        estudante.setId(5L);
        when(processarPagamentoService.processarPagamentoPix(estudante, 1500)).thenReturn("PGOK");

        Assertions.assertThrows(RuntimeException.class, () -> service.registrarMatricula(estudante, "XXX"));
    }
}

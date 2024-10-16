import domain.Estudante;
import domain.Matricula;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.MatriculaOnlineService;
import service.ProcessarPagamentoService;

public class MatriculaServiceTeste {

    MatriculaOnlineService service = new MatriculaOnlineService();

    @BeforeEach
    public void init() {
        ProcessarPagamentoService processarPagamentoService = new ProcessarPagamentoService();
        service.setProcessarPagamentoService(processarPagamentoService);
    }

    @Test
    void deveMatricular() {

        Estudante estudante = new Estudante();
        estudante.setId(5L);

        Matricula m = service.registrarMatricula(estudante, "CC1");
        Assertions.assertEquals(1500, m.getValorMensalidade());
    }

    @Test
    void naoDeveMatricular() {

        Estudante estudante = new Estudante();
        estudante.setId(5L);

        Assertions.assertThrows(RuntimeException.class, () -> service.registrarMatricula(estudante, "XXX"));
    }
}

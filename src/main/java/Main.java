import domain.Estudante;
import domain.Matricula;
import service.MatriculaOnlineService;
import service.ProcessarPagamentoService;

public class Main {
    public static void main(String[] args) {
        ProcessarPagamentoService processarPagamentoService = new ProcessarPagamentoService();
        MatriculaOnlineService service = new MatriculaOnlineService();
        service.setProcessarPagamentoService(processarPagamentoService);

        Estudante estudante = new Estudante();
        estudante.setId(5L);

        Matricula m = service.registrarMatricula(estudante, "CC1");
        System.out.println(m.getValorMensalidade());
    }
}

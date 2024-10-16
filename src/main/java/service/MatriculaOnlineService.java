package service;

import domain.Estudante;
import domain.Matricula;

import java.util.logging.Logger;

public class MatriculaOnlineService {
    private static final Logger logger = Logger.getLogger(MatriculaOnlineService.class.getName());

    private ProcessarPagamentoService processarPagamentoService;
    public Matricula registrarMatricula(Estudante estudante, String curso) {
        logger.info("iniciando registro de matricula");
        Matricula matricula = new Matricula();
        matricula.setEstudante(estudante);
        matricula.setCurso(curso);

        double valor;
        switch (curso) {
            case "CC1": valor = 1500; break;
            case "EE1": valor = 1600; break;
            case "SI1": valor = 1700; break;
            default: {
                throw new RuntimeException("Curso nao informado");
            }
        }
        matricula.setValorMensalidade(valor);
        String codPagamento = processarPagamentoService.processarPagamentoPix(estudante, valor);
        if (!codPagamento.equals("PGOK")) {
            throw new RuntimeException("Erro ao processar pagamento!");
        }
        logger.info("concluindo matricula");
        return matricula;
    }

    public ProcessarPagamentoService getProcessarPagamentoService() {
        return processarPagamentoService;
    }

    public void setProcessarPagamentoService(ProcessarPagamentoService processarPagamentoService) {
        this.processarPagamentoService = processarPagamentoService;
    }

}

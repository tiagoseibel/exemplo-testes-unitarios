package service;

import domain.Estudante;

import java.util.logging.Logger;

public class ProcessarPagamentoService {
    private static final Logger logger = Logger.getLogger(ProcessarPagamentoService.class.getName());
    public String processarPagamentoPix(Estudante estudante, double valor) {
        logger.info("iniciando pagamento");
        logger.info("concluindo pagamento");
        return "PGOK";
    }
}

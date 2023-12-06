import java.time.LocalDateTime;
import java.util.ArrayList;

import Enums.Turno;

public abstract class UsoDeVaga {

    // Atributos
    protected static final double FRACAO_USO = 0.25;
    protected static final double VALOR_FRACAO = 4.0;
    protected static final double VALOR_MAXIMO = 50.0;

    protected ArrayList<ServicoAdicional> servicosContratados = new ArrayList<>();
    protected Vaga vaga;
    protected Turno turno;
    protected double valorPago;
    protected Cliente cliente;
    protected LocalDateTime entrada;
    protected LocalDateTime saida;

    /**
     * 
     * @return
     */
    public abstract double calcularValorPago();

    /**
     * 
     * @param saida
     * @return
     */
    public double sair(LocalDateTime saida) {
        if (saida.isAfter(entrada)) {
            this.saida = saida;
            calcularValorPago();
        } else {
            System.out.println("Data inválida.");
        }

        return valorPago;
    }

    /**
     * 
     * @param minutosUsados
     * @return
     */
    protected double calcularValorHorista(long minutosUsados) {
        double valor = (minutosUsados / 15) * FRACAO_USO * VALOR_FRACAO;

        // Adicionar custo de serviços adicionais
        for (ServicoAdicional servico : servicosContratados) {
            valor += calcularCustoServico(servico);
        }

        if (valor > VALOR_MAXIMO) {
            valor = VALOR_MAXIMO;
        }

        this.valorPago = valor;
        return this.valorPago;
    }

    /**
     * 
     * @param servico
     */
    public void adicionarServico(ServicoAdicional servico) {
        servicosContratados.add(servico);
    }

    /**
     * 
     * @param servico
     * @return
     */
    protected double calcularCustoServico(ServicoAdicional servico) {
        return servico.getCusto();
    }

    /**
     * 
     * @return
     */
    public Vaga getVaga() {
        return vaga;
    }

    /**
     * 
     * @return
     */
    public double getValorPago() {
        return valorPago;
    }

    /**
     * 
     * @param mes
     * @param ano
     * @return
     */
    public boolean ehDoMes(int mes, int ano) {
        if (entrada != null) {
            int usoMes = entrada.getMonthValue();
            int usoAno = entrada.getYear();
            return (usoMes == mes && usoAno == ano);
        }

        return false;
    }
}

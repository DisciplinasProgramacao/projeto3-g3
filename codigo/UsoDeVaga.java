import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class UsoDeVaga {

    private static final double FRACAO_USO = 0.25;
    private static final double VALOR_FRACAO = 4.0;
    private static final double VALOR_MAXIMO = 50.0;
    private ArrayList<String> servicosContratados = new ArrayList<>();
    private Vaga vaga;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private double valorPago;
    private Cliente cliente;

    public UsoDeVaga(Cliente cliente, Vaga vaga, LocalDateTime entrada) {
        this.cliente = cliente;
        this.vaga = vaga;
        this.entrada = entrada;
    }

    public double sair(LocalDateTime saida) {
        if (saida.isAfter(entrada)) {
            this.saida = saida;
            valorPago();
        } else {
            System.out.println("Data invalida.");
        }
        return valorPago;
    }

    public void adicionarServico(String servico) {
        servicosContratados.add(servico);
    }

    public double valorPago() {
        if (entrada != null && saida != null) {
            Duration duracao = Duration.between(entrada, saida);
            long minutosUsados = duracao.toMinutes();

            // Cálculo do valor com base no tempo de uso da vaga e na fração de uso
            double valor = (minutosUsados / 15) * FRACAO_USO * VALOR_FRACAO;

            if (valor > VALOR_MAXIMO) {
                valor = VALOR_MAXIMO;
            }
            // Adicionar custo de serviços adicionais
            for (String servico : servicosContratados) {
                valor += calcularCustoServico(servico);
            }

            this.valorPago = valor;
        } else {
            this.valorPago = 0.0;
        }
        return this.valorPago;
    }

    public boolean ehDoMes(int mes, int ano) {
        if (entrada != null) {
            int usoMes = entrada.getMonthValue();
            int usoAno = entrada.getYear();

            return (usoMes == mes && usoAno == ano);
        }
        return false;
    }

    private double calcularCustoServico(String servico) {
        for (ServicoAdicional servicoAdicional : ServicoAdicional.values()) {
            if (servicoAdicional.name().equalsIgnoreCase(servico)) {
                return servicoAdicional.getCusto();
            }
        }
        return 0.0;
    }

    public double getValorPago() {
        return valorPago;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public LocalDateTime getEntrLocalDateTime() {
        return entrada;
    }

    public LocalDateTime getSLocalDateTime() {
        return saida;
    }

}

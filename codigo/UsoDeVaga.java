import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class UsoDeVaga {

    private static final double FRACAO_USO = 0.25;
    private static final double VALOR_FRACAO = 4.0;
    private static final double VALOR_MAXIMO = 50.0;
    private ArrayList<ServicoAdicional> servicosContratados = new ArrayList<>();
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
            calcularValorPago();
        } else {
            System.out.println("Data inválida.");
        }
        return valorPago;
    }

    public void adicionarServico(ServicoAdicional servico) {
        servicosContratados.add(servico);
    }

    public double calcularValorPago() {
        if (entrada != null && saida != null) {
            Duration duracao = Duration.between(entrada, saida);
            long minutosUsados = duracao.toMinutes();

            Tempo tipoCliente = cliente.getTipoCliente();
            // Verificar o tipo de cliente usando enum
            if (tipoCliente == Tempo.HORISTA) {
                valorPago = calcularValorHorista(minutosUsados);
            } else if (tipoCliente == Tempo.TURNO) {
                valorPago = calcularValorTurno(minutosUsados);
            } else if (tipoCliente == Tempo.MENSALISTA) {
                valorPago = cliente.getMensalidade(); // Utiliza a mensalidade do cliente
            } else {
                throw new IllegalArgumentException("Tipo de cliente inválido");
            }
        } else {
            valorPago = 0.0;
        }
        return valorPago;
    }

    private double calcularValorHorista(long minutosUsados) {
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

    private double calcularValorTurno(long minutosUsados) {
        if (isHorarioTurno()) {
            return 0.0; // Cliente de turno não paga pelo uso dentro do seu turno
        }

        // Fora do turno, calcular como horista
        return calcularValorHorista(minutosUsados);
    }

    private boolean isHorarioTurno() {
        Turno turnoCliente = cliente.getTurno();
        int horaEntrada = entrada.getHour();
        return (horaEntrada >= 8 && horaEntrada <= 12 && turnoCliente == Turno.MANHA)
                || (horaEntrada > 12 && horaEntrada <= 18 && turnoCliente == Turno.TARDE)
                || (horaEntrada > 18 && horaEntrada <= 23 && turnoCliente == Turno.NOITE);
    }

    public boolean ehDoMes(int mes, int ano) {
        if (entrada != null) {
            int usoMes = entrada.getMonthValue();
            int usoAno = entrada.getYear();
            return (usoMes == mes && usoAno == ano);
        }
        return false;
    }

    private double calcularCustoServico(ServicoAdicional servico) {
        return servico.getCusto();
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

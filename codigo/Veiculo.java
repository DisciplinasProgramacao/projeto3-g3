import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A classe Veiculo representa um veículo que pode estacionar em vagas.
 */
public class Veiculo {
    private String placa;
    private UsoDeVaga[] usos;
    private int ultimaPosicao;

    /**
     * Cria uma instância de Veiculo com a placa especificada.
     *
     * @param placa A placa do veículo.
     */
    public Veiculo(String placa) {
        this.placa = placa;
        this.usos = new UsoDeVaga[1000];
        this.ultimaPosicao = 0;
    }

    /**
     * Estaciona o veículo em uma vaga, se a vaga estiver disponível.
     *
     * @param vaga A vaga onde o veículo deve estacionar.
     */
    public void estacionar(Vaga vaga) {
        if (vaga.disponivel()) {
            usos[ultimaPosicao] = new UsoDeVaga(null, vaga, null);
            ultimaPosicao++;
        }
    }

    /**
     * Remove o veículo de uma vaga e calcula o valor a ser pago com base no tempo de estacionamento.
     * O valor é calculado em frações de 15 minutos, com um limite de R$50.
     *
     * @return O valor a ser pago pelo estacionamento.
     */
    public double sair() {
        double valorTotal = 0;

        for (int i = 0; i < ultimaPosicao; i++) {
            UsoDeVaga uso = usos[i];
            if (uso != null) {
                return uso.sair(null);
            }
        }

        return valorTotal;
    }

    /**
     * Calcula o valor total arrecadado com estacionamento deste veículo.
     *
     * @return O valor total arrecadado.
     */
    public double totalArrecadado() {
        double valorTotal = 0;

        for (int i = 0; i < ultimaPosicao; i++) {
            UsoDeVaga uso = usos[i];
            if (uso != null) {
                valorTotal += uso.valorPago();
            }
        }

        return valorTotal;
    }

    /**
     * Calcula o valor arrecadado no mês especificado.
     *
     * @param mes O número do mês (1 a 12) para o qual se deseja calcular o valor arrecadado.
     * @return O valor arrecadado no mês especificado.
     */
    public double arrecadadoNoMes(int mes) {
        double valorTotal = 0;

        for (int i = 0; i < ultimaPosicao; i++) {
            UsoDeVaga uso = usos[i];
            if (uso != null && uso.ehDoMes(mes, i)) {
                  valorTotal += uso.valorPago();
                }
            }
        return valorTotal;
    }

    /**
     * Calcula o total de usos registrados para este veículo.
     *
     * @return O total de usos registrados.
     */
    public int totalDeUsos() {
        int total = 0;

        for (int i = 0; i < ultimaPosicao; i++) {
            if (usos[i] != null) {
                total++;
            }
        }

        return total;
    }
    
     /**
     * Lista os veículos pela data da última saída em ordem decrescente.
     *
     * @return Uma lista de veículos ordenada pela data da última saída.
     */
    public static List<Veiculo> listarPorDataUltimaSaida(List<Veiculo> veiculos) {
        List<Veiculo> veiculosOrdenados = new ArrayList<>(veiculos);
        Collections.sort(veiculosOrdenados, (v1, v2) -> {
            if (v1.ultimaSaida() == null && v2.ultimaSaida() == null) {
                return 0;
            } else if (v1.ultimaSaida() == null) {
                return 1;
            } else if (v2.ultimaSaida() == null) {
                return -1;
            } else {
                return v2.ultimaSaida().compareTo(v1.ultimaSaida());
            }
        });
        return veiculosOrdenados;
    }

     public static List<Veiculo> listarPorDataUltimaEntrada(List<Veiculo> veiculos) {
        List<Veiculo> veiculosOrdenados = new ArrayList<>(veiculos);
        Collections.sort(veiculosOrdenados, (v1, v2) -> {
            if (v1.ultimaEntrada() == null && v2.ultimaEntrada() == null) {
                return 0;
            } else if (v1.ultimaEntrada() == null) {
                return 1;
            } else if (v2.ultimaEntrada() == null) {
                return -1;
            } else {
                return v2.ultimaEntrada().compareTo(v1.ultimaEntrada());
            }
        });
        return veiculosOrdenados;
    }
      public LocalDateTime ultimaEntrada() {
        LocalDateTime ultimaEntrada = null;
        for (UsoDeVaga uso : usos) {
            if (uso != null && uso.getEntrLocalDateTime() != null) {
                if (ultimaEntrada == null || uso.getEntrLocalDateTime().isAfter(ultimaEntrada)) {
                    ultimaEntrada = uso.getEntrLocalDateTime();
                }
            }
        }
        return ultimaEntrada;
    }

    /**
     * Retorna a data da última saída do veículo.
     *
     * @return A data da última saída do veículo.
     */
    public LocalDateTime ultimaSaida() {
        LocalDateTime ultimaSaida = null;
        for (UsoDeVaga uso : usos) {
            if (uso != null && uso.getSLocalDateTime() != null) {
                if (ultimaSaida == null || uso.getSLocalDateTime().isAfter(ultimaSaida)) {
                    ultimaSaida = uso.getSLocalDateTime();
                }
            }
        }
        return ultimaSaida;
    }
    
    /**
     * Gera uma lista de veículos ordenada pelo valor total pago, em ordem decrescente.
     *
     * @return Uma lista de veículos ordenada pelo valor total pago.
     */
    public static List<Veiculo> gerarListaPorValorPago(List<Veiculo> veiculos) {
        List<Veiculo> veiculosOrdenados = new ArrayList<>(veiculos);
        Collections.sort(veiculosOrdenados, (v1, v2) -> Double.compare(v2.totalArrecadado(), v1.totalArrecadado()));
        return veiculosOrdenados;
    }
}

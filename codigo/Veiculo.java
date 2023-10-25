import java.util.List;
import java.util.ArrayList;

public class Veiculo {

    // Atributos

    private String placa;             // A placa do veículo
    private List<UsoDeVaga> usos;      // Lista de usos de vagas associados ao veículo
    private int qntdVagasUsadas;       // Quantidade de vagas usadas pelo veículo

    // Construtor

    public Veiculo(String placa) {
        this.placa = placa;
        this.qntdVagasUsadas = 0;
        this.usos = new ArrayList<>();
    }

    // Métodos

    /**
     * Estaciona o veículo em uma vaga, se a vaga estiver disponível.
     * 
     * @param vaga A vaga em que o veículo deseja estacionar.
     */
    public void estacionar(Vaga vaga) {
        if (vaga.disponivel()) {
            UsoDeVaga uso = new UsoDeVaga(vaga);
            this.usos.add(uso);
            qntdVagasUsadas++;
        }
    }

    /**
     * Remove o veículo de uma vaga e calcula o valor pago pelo uso da vaga.
     * 
     * @param vaga A vaga da qual o veículo deseja sair.
     * @return O valor pago pelo uso da vaga ou -1 se a vaga não foi encontrada.
     */
    public double sair(Vaga vaga) {
        List<UsoDeVaga> usosARemover = new ArrayList<>();

    for (UsoDeVaga uso : usos) {
        if (uso.getVaga() == vaga) {
            double valorPago = uso.sair();
            usosARemover.add(uso);
            qntdVagasUsadas--;
            return valorPago;
        }
    }
    usos.removeAll(usosARemover);

    return -1;
    }

    /**
     * Calcula o valor total arrecadado com os usos de vagas pelo veículo.
     * 
     * @return O valor total arrecadado.
     */
    public double totalArrecadado() {
        double arrecadacaoTotal = 0.0;
        for (UsoDeVaga uso : usos) {
            arrecadacaoTotal += uso.valorPago();
        }
        return arrecadacaoTotal;
    }

    /**
     * Calcula o valor arrecadado em um determinado mês.
     * 
     * @param mes O mês para o qual deseja calcular a arrecadação.
     * @return O valor arrecadado no mês especificado.
     */
    public double arrecadadoNoMes(int mes) {
        double arrecadacaoNoMes = 0.0;
        for (UsoDeVaga uso : usos) {
            if (uso.ehDoMes(mes)) {
                arrecadacaoNoMes += uso.valorPago();
            }
        }
        return arrecadacaoNoMes;
    }

    /**
     * Obtém o número total de usos de vagas pelo veículo.
     * 
     * @return O número total de usos de vagas.
     */
    public int totalDeUsos() {
        return qntdVagasUsadas;
    }

    // Método equals

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Veiculo) {
            Veiculo veiculo = (Veiculo) obj;
            return placa.equals(veiculo.placa);
        }
        return false;
    }

    // Método toString

    @Override
    public String toString() {
        return "Veículo: " + placa + " | Usos: " + usos.size();
    }
}

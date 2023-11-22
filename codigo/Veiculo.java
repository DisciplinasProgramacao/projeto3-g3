import java.util.List;

import Exceptions.UsoDeVagaException;
import Exceptions.VagaDesocupadaException;
import Exceptions.VagaOcupadaException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Veiculo implements IDataToText {

    // Atributos

    private String placa; // A placa do veículo
    private List<UsoDeVaga> usos; // Lista de usos de vagas associados ao veículo
    private int qntdDeVagasUsadas;
    // Construtor

    public Veiculo(String placa) {
        this.placa = placa;
        this.qntdDeVagasUsadas = 0;
        this.usos = new ArrayList<>();
    }

    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    // Métodos

    /**
     * Estaciona o veículo em uma vaga, se a vaga estiver disponível.
     * 
     * @param vaga A vaga em que o veículo deseja estacionar.
     * @throws VagaOcupadaException
     */
    public void estacionar(Vaga vaga) throws VagaOcupadaException {
        if (vaga.disponivel()) {
            UsoDeVaga uso = new UsoDeVaga(vaga);
            this.usos.add(uso);
        }
    }

    /**
     * Remove o veículo de uma vaga e calcula o valor pago pelo uso da vaga.
     * 
     * @param vaga A vaga da qual o veículo deseja sair.
     * @return O valor pago pelo uso da vaga ou -1 se a vaga não foi encontrada.
     * @throws VagaDesocupadaException
     * @throws UsoDeVagaException
     */
    public double sair(Vaga vaga) throws UsoDeVagaException, VagaDesocupadaException {
        double valorPago = 0.0;

        for (UsoDeVaga uso : usos) {
            if (uso.getVaga() == vaga) {
                valorPago = uso.sair(LocalDateTime.now());
                return valorPago;
            }
        }
        return valorPago;
    }

    /**
     * Calcula o valor total arrecadado com os usos de vagas pelo veículo.
     * 
     * @return O valor total arrecadado.
     */
    public double totalArrecadado() {
        double arrecadacaoTotal = 0.0;
        for (UsoDeVaga uso : usos) {
            if (uso != null) {
                arrecadacaoTotal += uso.getValorPago();
            }
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
                arrecadacaoNoMes += uso.getValorPago();
            }
        }
        return arrecadacaoNoMes;
    }

     /**
     * Calcula o total de usos registrados para este veículo.
     *
     * @return O total de usos registrados.
     */
    public int totalDeUsos() {
        return usos.size();
    }
    

    public void gerarRelatorioComPrioridade(List<UsoDeVaga> usos) {
        Comparator<UsoDeVaga> comparadorDeValorPago = new Comparator<UsoDeVaga>() {
            public int compare(UsoDeVaga usoDeVaga1, UsoDeVaga usoDeVaga2) {
                return Double.compare(usoDeVaga1.getValorPago(), usoDeVaga2.getValorPago());
            }
        };
        Collections.sort(usos, comparadorDeValorPago);
        for (UsoDeVaga uso : usos) {
            System.out.println("Relatorio das vagas usadas - Valor pago" + uso.getValorPago());
        }
    }

      /**
     * Gera uma lista de veículos ordenada pelo valor total pago, em ordem decrescente.
     *
     * @return Uma lista de veículos ordenada pelo valor total pago.
     */
    public static List<Veiculo> gerarListaPorValorPago(List<Veiculo> veiculos) {
        List<Veiculo> veiculosOrdenados = new ArrayList<>(veiculos);
        Collections.sort(veiculosOrdenados, (v1, v2) -> Double.compare(v2.totalArrecadado(), v1.totalArrecadado()));
        System.out.println(veiculosOrdenados);
        return veiculosOrdenados;
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

    @Override
    public String dataToText() {
        return this.placa + ";" + this.usos.size();
    }
}


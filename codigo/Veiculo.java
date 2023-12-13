
import java.time.LocalDateTime;

import java.util.*;

import Enums.Turno;
import Exceptions.*;

public class Veiculo implements IDataToText {
    static FabricaUsoDeVaga fabrica = new FabricaUsoDeVaga();

    // #region atributos
    private String placa;
    private List<UsoDeVaga> usos;
    private Cliente cliente;
    // #endregion

    // #region construtor
    public Veiculo(String placa) {
        this.placa = placa;
        this.usos = new ArrayList<>();
    }
    // #endregion

    // #region getters e setters
    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    // #endregion

    // #region métodos de negócio

    /**
     * Estaciona o veículo em uma vaga, se a vaga estiver disponível.
     * 
     * @param veiculo A vaga em que o veículo deseja estacionar.
     * @throws VagaOcupadaException
     */
    public void estacionar(Vaga vaga, Turno turno) throws VagaOcupadaException {
        if (vaga.disponivel()) {
            UsoDeVaga uso = fabrica.get(cliente.getTipoCliente().desc, vaga);
            usos.add(uso);
            vaga.setDisponivel(false);
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

        UsoDeVaga uso = usos.stream().filter(u-> u.getVaga().equals(vaga) && u.getSaida()==null)
                                    .findFirst().orElse(null);
        if(uso!=null)
            valorPago = uso.sair(LocalDateTime.now());

        return valorPago;
    }

    /**
     * Calcula o valor total arrecadado com os usos de vagas pelo veículo.
     * 
     * @return O valor total arrecadado.
     */
    public double totalArrecadado() {
        return usos.stream()
                .mapToDouble(UsoDeVaga::calcularValorPago)
                .sum();
    }

    /**
     * Calcula o valor arrecadado em um determinado mês.
     * 
     * @param mes O mês para o qual deseja calcular a arrecadação.
     * @return O valor arrecadado no mês especificado.
     */
    public double arrecadadoNoMes(int mes) {
        return usos.stream()
                .filter(uso -> uso.ehDoMes(mes))
                .mapToDouble(UsoDeVaga::calcularValorPago)
                .sum();
    }

    /**
     * Calcula o total de usos registrados para este veículo.
     *
     * @return O total de usos registrados.
     */
    public int totalDeUsos() {
        return usos.size();
    }

    /**
     * Calcula o total de usos registrado no mês selecionado para o veículo.
     * 
     * @param mes mês para o qual deseja calcular o total de usos.
     * @return o total de usos no mês filtrado.
     */
    public int totalDeUsosNoMes(int mes) {
        return (int) usos.stream().filter(uso -> uso.ehDoMes(mes)).count();
    }

    /**
     * Gera um relatório de usos de vaga ordenado pelo comparador especificado.
     * 
     * @param comparador
     * @return O relatório de usos de vaga em formato String.
     */
    public String gerarRelatorio(Comparator<UsoDeVaga> comparador) {
        Collections.sort(usos, comparador);
        StringBuilder relatorio = new StringBuilder("Relatório de Usos de Vaga:\n");

        for (UsoDeVaga uso : usos) {
            relatorio.append(uso.toString()).append("\n");
        }

        return relatorio.toString();

    }
    // #endregion

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

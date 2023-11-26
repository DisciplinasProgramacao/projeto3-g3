
import java.time.LocalDateTime;

import java.util.*;

import Enums.TipoCliente;
import Enums.Turno;
import Exceptions.UsoDeVagaException;
import Exceptions.VagaDesocupadaException;
import Exceptions.VagaOcupadaException;

public class Veiculo implements IDataToText {

    // Atributos

    private String placa; // A placa do veículo
    private List<UsoDeVaga> usos; // Lista de usos de vagas associados ao veículo
    private Cliente cliente;
    private Turno turno;
    // Construtor

    public Veiculo(String placa) {
        this.placa = placa;
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
    public void estacionar(Vaga vaga) {
        if (vaga.disponivel() && cliente !=  null) {
           switch (cliente.tipoDeCliente()){
            case "Horista":
            UsoDeVaga usoHorista = new UsoDeVagaHorista(vaga);
            this.usos.add(usoHorista);
            break;

            case "Mensalista":
            UsoDeVaga usoMensalista = new UsoDeVagaMensalista(vaga);
            this.usos.add(usoMensalista);
            break;

            case "Turno":
            UsoDeVaga usoTurno = new UsoDeVagaTurno(vaga, turno);
            this.usos.add(usoTurno);
            break;

            default:
			  break;
            
           }
            
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

    public List<UsoDeVaga> gerarRelatorio(Comparator<UsoDeVaga> comparador) {
        Collections.sort(usos, comparador);
        return usos;
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

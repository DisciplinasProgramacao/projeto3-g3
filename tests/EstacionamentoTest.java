import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import Exceptions.UsoDeVagaException;
import Exceptions.VagaDesocupadaException;
import Exceptions.VagaOcupadaException;

public class EstacionamentoTest {

    private Estacionamento estacionamento;
    private Cliente[] cliente;
    private Vaga[] vaga;

    @Before
    public void setUp() {
        // Configuração inicial para os testes
        estacionamento = new Estacionamento("Meu Estacionamento", 5, 10);

        Cliente cliente1 = new Cliente("Ana", "1");
        Cliente cliente2 = new Cliente("Julia", "2");
        Cliente cliente3 = new Cliente("Maria", "3");

        estacionamento.addCliente(cliente1);
        estacionamento.addCliente(cliente2);
        estacionamento.addCliente(cliente3);

        Veiculo veiculo1 = new Veiculo("DEF501");
        Veiculo veiculo2 = new Veiculo("XYZ789");
        Veiculo veiculo3 = new Veiculo("ANA131");

        // Adicione os veículos ao estacionamento
        estacionamento.addVeiculo(veiculo1, "1");
        estacionamento.addVeiculo(veiculo2, "2");
        estacionamento.addVeiculo(veiculo3, "3");
    }

    @Test
    public void testGetnome() {
        assertEquals("Meu Estacionamento", estacionamento.getnome());
    }


    @Test
    public void testAddCliente() {
        Cliente cliente1 = new Cliente("Ana", "1");
        estacionamento.addCliente(cliente1);
        assertTrue(temCliente(estacionamento, cliente1));
    }

    // Método auxiliar para verificar se o cliente está no vetor de clientes (id)
    private boolean temCliente(Estacionamento estacionamento, Cliente cliente) {
        for (Cliente c : estacionamento.id) {
            if (c != null && c.equals(cliente)) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void testEstacionar() throws VagaOcupadaException {
        //System.out.println(estacionamento.estacionar("XYZ789"));
        assertTrue(estacionamento.estacionar("DEF501"));
        assertTrue(estacionamento.estacionar("XYZ789"));
        assertTrue(estacionamento.estacionar("ANA131"));
             
    }

    @Test
    public void testSair() throws UsoDeVagaException, VagaDesocupadaException, VagaOcupadaException {
        double valorPago = estacionamento.sair("DEF501");

        assertTrue(valorPago != -1);
        assertTrue(valorPago >= 0);
    }

    @Test
    public void testTotalArrecadado() throws VagaOcupadaException {
        assertTrue(estacionamento.totalArrecadado() >= 0);
    }

    @Test
    public void testArrecadacaoNoMes() throws VagaOcupadaException {
       
        assertTrue(estacionamento.arrecadacaoNoMes(1) >= 0);
    }

    @Test
    public void testValorMedioPorUso() throws VagaOcupadaException {
        estacionamento.estacionar("ABC123");
        estacionamento.estacionar("XYZ789");

        assertTrue(estacionamento.valorMedioPorUso() >= 0);
    }

}

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import Enums.TipoCliente;
import Enums.Turno;
import Exceptions.UsoDeVagaException;
import Exceptions.VagaDesocupadaException;
import Exceptions.VagaOcupadaException;

public class EstacionamentoTest {

    private Estacionamento estacionamento;

    @Before
    public void setUp() {
        estacionamento = new Estacionamento("Meu Estacionamento", 5, 10);

        Cliente cliente1 = new Cliente("Ana", "1", TipoCliente.HORISTA);
        Cliente cliente2 = new Cliente("Julia", "2",TipoCliente.MENSALISTA);
        Cliente cliente3 = new Cliente("Maria", "3", TipoCliente.TURNO);

        estacionamento.addCliente(cliente1);
        estacionamento.addCliente(cliente2);
        estacionamento.addCliente(cliente3);

        Veiculo veiculo1 = new Veiculo("DEF501");
        Veiculo veiculo2 = new Veiculo("XYZ789");
        Veiculo veiculo3 = new Veiculo("ANA131");

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
        Cliente cliente1 = new Cliente("Ana", "1", TipoCliente.HORISTA);
        estacionamento.addCliente(cliente1);
        assertTrue(temCliente(estacionamento, cliente1));
    }

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
        assertTrue(estacionamento.estacionar("DEF501",Turno.MANHA));
        assertTrue(estacionamento.estacionar("XYZ789", Turno.TARDE));
        assertTrue(estacionamento.estacionar("ANA131", Turno.NOITE));

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

        assertTrue(estacionamento.arrecadacaoNoMes(1, 2023) >= 0);
    }

    @Test
    public void testValorMedioPorUso() throws VagaOcupadaException {
        estacionamento.estacionar("ABC123", Turno.MANHA);
        estacionamento.estacionar("XYZ789", Turno.TARDE);

        assertTrue(estacionamento.valorMedioPorUso() >= 0);
    }

}

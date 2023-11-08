import org.junit.Test;

import Exceptions.UsoDeVagaException;
import Exceptions.VagaDesocupadaException;
import Exceptions.VagaOcupadaException;

import org.junit.Before;
import static org.junit.Assert.*;

public class VeiculoTest {

    Veiculo veiculo1, veiculo2;
    Vaga vaga1, vaga2;
    Cliente cliente1, cliente2;

    @Before
    public void setUp() throws Exception {
        veiculo1 = new Veiculo("ABC - 1234");
        veiculo2 = new Veiculo("PUQ - 7269");
        vaga1 = new Vaga(1, 1);
        vaga2 = new Vaga(1, 2);
        cliente1 = new Cliente("Octávio", "3");
        cliente2 = new Cliente("Xulamb", "1");
    }

    @Test
    public void testEstacionarEArrecadado() throws VagaOcupadaException {
        double valorEstacionamento = 10.0;
        veiculo1.estacionar(vaga1);
        assertEquals(valorEstacionamento, veiculo1.totalArrecadado(), 10.0);
    }

    @Test
    public void testSairEArrecadadoNoMes() throws VagaOcupadaException {
        double valorEstacionamento = 10.0;
        int mes = 10;
        veiculo1.estacionar(vaga2);
        vaga2.sair();
        assertEquals(valorEstacionamento, veiculo1.arrecadadoNoMes(mes), 10.0);
    }

    @Test
    public void testTotalDeUsos() throws VagaOcupadaException, UsoDeVagaException, VagaDesocupadaException {
        veiculo1.estacionar(vaga1);
        veiculo1.sair(vaga1);
        veiculo1.estacionar(vaga2);
        veiculo1.sair(vaga2);
        assertEquals(2, veiculo1.totalDeUsos());
    }
}

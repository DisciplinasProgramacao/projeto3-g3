import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class ClienteTest {
    private Cliente cliente;
    private Veiculo veiculoMock;
    private String placaMock;
    private Vaga vaga;
    
    @Before
    public void setUp() {
        cliente = new Cliente("João", "1");
        placaMock = "ABC1234";
        veiculoMock = new Veiculo(placaMock);
        veiculoMock.estacionar(vaga);
        veiculoMock.estacionar(vaga);
        veiculoMock.estacionar(vaga);
    }

    @Test
    public void testAddVeiculo() {
        cliente.addVeiculo(veiculoMock);
        assertEquals(veiculoMock, cliente.possuiVeiculo("ABC1234"));
    }

    @Test
    public void testPossuiVeiculo() {
        cliente.possuiVeiculo(placaMock);
        assertEquals(veiculoMock, cliente.possuiVeiculo("ABC1234"));
    }

     @Test
    public void testNaoPossuiVeiculo() {
        String placaTest = "XYX5678";
        cliente.possuiVeiculo(placaTest);
        assertNull(null, cliente.possuiVeiculo(placaTest));
    }

    @Test
    public void testTotalDeUsos() {
        assertEquals(3, cliente.totalDeUsos());
    }

    @Test
    public void testArrecadadoPorVeiculo() {
        assertEquals(9, cliente.arrecadadoPorVeiculo(placaMock), 0.01);
    }

    @Test
    public void testArrecadadoTotal(String placa) {
        assertEquals(9, cliente.arrecadadoTotal(), 0.01);
    }

    @Test
    public void testArrecadadoNoMes(String placa) {
        assertEquals(9, cliente.arrecadadoNoMes(10), 0.01);
    }
}

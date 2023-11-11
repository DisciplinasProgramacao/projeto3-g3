import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class VeiculoTest {
    private Veiculo veiculo;
    private Vaga vaga;
    
    @Before
    public void setUp() {
        veiculo = new Veiculo("ABC1234");
        vaga = new Vaga(0, 0);
    }

    @Test
    public void testEstacionar() {
        veiculo.estacionar(vaga);
        assertEquals(1, veiculo.totalDeUsos());
    }

    @Test
    public void testSair() {
        veiculo.estacionar(vaga);
        double valorPago = veiculo.sair();
        assertTrue(valorPago >= 0);
    }

    @Test
    public void testTotalArrecadado() {
        veiculo.estacionar(vaga);
        double valorPago = veiculo.sair();
        assertEquals(valorPago, veiculo.totalArrecadado(), 0.001);
    }

    @Test
    public void testArrecadadoNoMes() {
        veiculo.estacionar(vaga);
        double valorPago = veiculo.sair();
        veiculo.estacionar(vaga);
        double valorPagoSegunda = veiculo.sair();
        veiculo.estacionar(vaga);
        double valorPagoTerceiro = veiculo.sair();
        double arrecadadoNoMes = valorPago+valorPagoSegunda+valorPagoTerceiro;
        veiculo.arrecadadoNoMes(3);

        assertEquals(arrecadadoNoMes, veiculo.arrecadadoNoMes(3), 0.001);
    }

    @Test
    public void testTotalDeUsos() {
        veiculo.estacionar(vaga);
        assertEquals(1, veiculo.totalDeUsos());
    }
}

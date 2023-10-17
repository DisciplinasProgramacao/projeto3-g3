import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;


public class VeiculoTest {

    Veiculo veiculo1;
    Veiculo veiculo2;
    Vaga vaga1;
    Vaga vaga2;
    UsoDeVaga usoDeVaga1;
    UsoDeVaga usoDeVaga2;
    Cliente cliente1;
    Cliente cliente2;
    Estacionamento estacionamento1;

    @Before
    public void setUp() throws Exception{
        veiculo1 = new Veiculo("ABC - 1234");
        veiculo2 = new Veiculo("PUQ - 7269");
        vaga1 = new Vaga(1, 1);
        vaga2 = new Vaga(1, 2);
        usoDeVaga1 = new UsoDeVaga(vaga1);
        usoDeVaga2 = new UsoDeVaga(vaga2);
        cliente1 = new Cliente("Octávio", "3");
        cliente2 = new Cliente("Xulamb", "1");
    }
    
    @Test
    public void testEstacionarEArrecadado() {
        // Teste do método estacionar e totalArrecadado
        double valorEstacionamento = 10.0;
        veiculo1.estacionar(vaga1);
        assertEquals(valorEstacionamento, veiculo1.totalArrecadado(), 10.0);
    }

    @Test
    public void testSairEArrecadadoNoMes() {
        // Teste do método sair e arrecadadoNoMes
        double valorEstacionamento = 10.0;
        int mes = 10;
        veiculo1.estacionar(vaga2);
        vaga2.sair();
        assertEquals(valorEstacionamento, veiculo1.arrecadadoNoMes(mes), 10.0);
    }

    @Test
    public void testTotalDeUsos() {
        // Teste do método totalDeUsos
        veiculo1.estacionar(vaga1);
        veiculo2.estacionar(vaga2);
        vaga1.sair();
        vaga2.sair();
        assertEquals(1, veiculo1.totalDeUsos());
        assertEquals(1, veiculo2.totalDeUsos());
    }
}

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
    public void testEstacionar1(){

    }

    @Test
    public void testSair(){

    }

    @Test
    public void testTotalArrecadado() {
        cliente1.addVeiculo(veiculo1);
        veiculo1.estacionar(vaga1);
        veiculo1.sair();
        veiculo2.estacionar(vaga2);
        veiculo2.sair();
        assertEquals(50.0, cliente1.arrecadadoTotal(), 0.01);
    }

    @Test
    public void testArrecadadoNoMes(int mes){

    }

    @Test
    public void testTotalDeUsos(){

    }
}

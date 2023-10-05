import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class ClienteTest {

  Cliente cliente2, cliente1;
  Veiculo veiculo1, veiculo2;
  Vaga vaga1, vaga2;

  @Before
  public void setUp() throws Exception {
    cliente1 = new Cliente("Ana", "1");
    cliente2 = new Cliente();
    veiculo1 = new Veiculo("PUC-2023");
    veiculo2 = new Veiculo("ABC-1234");
    vaga1 = new Vaga(1, 1);
    vaga2 = new Vaga(2, 2);
  }

  @Test
  public void testAddVeiculo() {
    cliente1.addVeiculo(veiculo1);
    assertEquals(1, cliente1.getVeiculos().size());
  }

  @Test
  public void testPossuiVeiculo() {
    cliente1.addVeiculo(veiculo2);
    assertEquals(veiculo2, cliente1.possuiVeiculo("ABC-1234"));
    assertTrue(cliente1.possuiVeiculo("CBA-4321") == null);
  }

  // Os testes abaixo dependem da implementação das classes Veículo, Vaga e
  // UsoVaga e serão adaptados futuramente.
  @Test
  public void testTotalDeUsos() {
    cliente1.addVeiculo(veiculo1);
    veiculo1.estacionar(vaga1);
    veiculo1.sair();
    veiculo1.estacionar(vaga2);
    veiculo1.sair();
    assertEquals(2, cliente1.totalDeUsos());
  }

  @Test
  public void testArrecadadoPorVeiculo() {
    cliente2.addVeiculo(veiculo2);
    veiculo2.estacionar(vaga1);
    veiculo2.sair();
    veiculo2.estacionar(vaga2);
    veiculo2.sair();
    assertEquals(25.0, cliente2.arrecadadoPorVeiculo("ABC-1234"), 0.01);
  }

  @Test
  public void testArrecadadoTotal() {
    cliente1.addVeiculo(veiculo1);
    cliente1.addVeiculo(veiculo2);
    veiculo1.estacionar(vaga1);
    veiculo1.sair();
    veiculo2.estacionar(vaga2);
    veiculo2.sair();
    assertEquals(50.0, cliente1.arrecadadoTotal(), 0.01);
  }

  @Test
  public void testArrecadadoNoMes() {
    cliente1.addVeiculo(veiculo1);
    cliente1.addVeiculo(veiculo2);
    veiculo1.estacionar(vaga1);
    veiculo1.sair();
    veiculo2.estacionar(vaga2);
    veiculo2.sair();
    assertEquals(25.0, cliente1.arrecadadoNoMes(1), 0.0);
  }
}
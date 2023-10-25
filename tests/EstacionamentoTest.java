import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class EstacionamentoTest {

    private Estacionamento estacionamento;
    private Cliente[] cliente;
    private Vaga[] vaga;

    @Before
    public void setUp() {
        // Configuração inicial para os testes
        estacionamento = new Estacionamento("Meu Estacionamento", 5, 10,5);
        cliente = new Cliente[10];
        Cliente cliente1 = new Cliente("Ana", "1");
        Cliente cliente2 = new Cliente("Julia", "2");
        estacionamento.addCliente(cliente1);
        estacionamento.addCliente(cliente2);
        Veiculo veiculo1 = new Veiculo("ABC123");
        Veiculo veiculo2 = new Veiculo("XYZ789");
        cliente1.addVeiculo(veiculo1);
        cliente2.addVeiculo(veiculo2);
    }

    @Test
    public void testGetnome() {
        assertEquals("Meu Estacionamento", estacionamento.getnome());
    }

    @Test
    public void testGetquantFileiras() {
        assertEquals(5, estacionamento.getquantFileiras());
    }

    @Test
    public void testGetvagasPorFileiras() {
        assertEquals(10, estacionamento.getvagasPorFileiras());
    }

    @Test
    public void testAddCliente() {
        Cliente cliente = new Cliente("123", "Cliente Teste");
        estacionamento.addCliente(cliente);

        assertTrue(temCliente(estacionamento, cliente));
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
    public void testEstacionar() {
        Cliente cliente = new Cliente("123", "Cliente Teste");
        estacionamento.addCliente(cliente);
        Veiculo veiculo = new Veiculo("ABC123");
        cliente.addVeiculo(veiculo);
        estacionamento.estacionar("ABC123");
        
        assertTrue(estacionamento.estacionar("ABC123"));
    }

    @Test
    public void testSair() {
        Cliente cliente = new Cliente("123", "Cliente Teste");
        estacionamento.addCliente(cliente);
        Veiculo veiculo = new Veiculo("ABC123");
        cliente.addVeiculo(veiculo);
        estacionamento.estacionar("ABC123");
        double valorPago = estacionamento.sair("ABC123");
   
        assertTrue(valorPago != -1);
        assertTrue(valorPago >= 0);
    }

    @Test
    public void testTotalArrecadado() {
        estacionamento.estacionar("ABC123");
        estacionamento.estacionar("XYZ789");
   
        System.out.println("Total Arrecadado:"+estacionamento.totalArrecadado());
        assertTrue(estacionamento.totalArrecadado() >= 0);
    }

    @Test
    public void testArrecadacaoNoMes() {
        Cliente cliente = new Cliente("123", "Cliente Teste");
        estacionamento.addCliente(cliente);
        Veiculo veiculo = new Veiculo("ABC123");
        cliente.addVeiculo(veiculo);
        estacionamento.estacionar("ABC123");
       
        System.out.println("T:"+estacionamento.arrecadacaoNoMes(1));
        assertTrue(estacionamento.arrecadacaoNoMes(1) >= 0);
    }

    @Test
    public void testValorMedioPorUso() {
        estacionamento.estacionar("ABC123");
        estacionamento.estacionar("XYZ789");
      
        assertTrue(estacionamento.valorMedioPorUso() >= 0);
    }

    @Test
    public void testTop5Clientes() {
      
    }
}

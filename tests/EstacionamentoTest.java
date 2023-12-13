import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        Cliente cliente2 = new Cliente("Julia", "2",TipoCliente.HORISTA);
        Cliente cliente3 = new Cliente("Maria", "3", TipoCliente.TURNOMANHA);
        Cliente cliente4 = new Cliente("Pedro", "4", TipoCliente.HORISTA);
        Cliente cliente5 = new Cliente("Lucas", "5", TipoCliente.MENSALISTA);


        estacionamento.addCliente(cliente1);
        estacionamento.addCliente(cliente2);
        estacionamento.addCliente(cliente3);
        estacionamento.addCliente(cliente4);
        estacionamento.addCliente(cliente5);

        Veiculo veiculo1 = new Veiculo("DEF501");
        Veiculo veiculo2 = new Veiculo("XYZ789");
        Veiculo veiculo3 = new Veiculo("ANA131");
        Veiculo veiculo4 = new Veiculo("XYZ123");
        Veiculo veiculo5 = new Veiculo("ABC456");

        veiculo1.setCliente(cliente1);
        veiculo2.setCliente(cliente2);
        veiculo3.setCliente(cliente3);       
        veiculo4.setCliente(cliente4);
        veiculo5.setCliente(cliente5);

        estacionamento.addVeiculo(veiculo1, "1");
        estacionamento.addVeiculo(veiculo2, "2");
        estacionamento.addVeiculo(veiculo3, "3");
        estacionamento.addVeiculo(veiculo4, "4");
        estacionamento.addVeiculo(veiculo5, "5");

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
        estacionamento.estacionar("DEF501",Turno.MANHA);
        double valorPago = estacionamento.sair("DEF501");

        assertEquals(4.0, valorPago);
    }

    @Test
    public void testTotalArrecadado() throws VagaOcupadaException, UsoDeVagaException, VagaDesocupadaException {
     estacionamento.estacionar("DEF501",Turno.MANHA);
     estacionamento.estacionar("XYZ123",Turno.TARDE);
     estacionamento.estacionar("XYZ789",Turno.MANHA);
     estacionamento.sair("DEF501");
     estacionamento.sair("XYZ123");
     estacionamento.sair("XYZ789");
       
     assertEquals(12.0, estacionamento.totalArrecadado());
    }

    @Test
    public void testArrecadacaoNoMes() throws VagaOcupadaException, UsoDeVagaException, VagaDesocupadaException {
        estacionamento.estacionar("DEF501", Turno.MANHA);
        estacionamento.sair("DEF501");
        assertEquals(4.0,estacionamento.arrecadacaoNoMes(12));
    }        


    @Test
    public void testValorMedioPorUso() throws VagaOcupadaException, UsoDeVagaException, VagaDesocupadaException {
        estacionamento.estacionar("ANA131", Turno.MANHA);
        estacionamento.estacionar("DEF501", Turno.MANHA);
        estacionamento.sair("ANA131");
        estacionamento.sair("DEF501");
        assertEquals(4.0, estacionamento.valorMedioPorUso());
    }

    @Test
    public void testeTop5Clientes() throws VagaOcupadaException, UsoDeVagaException, VagaDesocupadaException{

        estacionamento.estacionar("DEF501", Turno.MANHA);
        estacionamento.estacionar("XYZ123", Turno.TARDE);
        estacionamento.estacionar("ANA131", Turno.TARDE);
        estacionamento.estacionar("XYZ789", Turno.NOITE);
        estacionamento.estacionar("ABC456", Turno.NOITE);
         estacionamento.estacionar("ABC456", Turno.NOITE);

        estacionamento.sair("DEF501");    
        estacionamento.sair("XYZ123");  
        estacionamento.sair("ANA131");   
        estacionamento.sair("XYZ789"); 
        estacionamento.sair("ABC456");
        String top5 = estacionamento.top5Clientes(12); // Assumindo que 1 representa janeiro
        System.out.println(top5);
        
    }

}

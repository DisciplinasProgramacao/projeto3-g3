import org.junit.Test;

import Enums.TipoCliente;
import Enums.Turno;
import Exceptions.UsoDeVagaException;
import Exceptions.VagaDesocupadaException;
import Exceptions.VagaOcupadaException;

import org.junit.Before;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class VeiculoTest {

    Veiculo veiculo1;
    Veiculo veiculo2;
    Veiculo veiculo3;
    Vaga vaga1;
    Vaga vaga2;
    Vaga vaga3;
    UsoDeVaga usoDeVaga1;
    UsoDeVaga usoDeVaga2;
    UsoDeVaga usoDeVaga3;
    Cliente cliente1;
    Cliente cliente2;
    Cliente cliente3;
    Estacionamento estacionamento1;
    Turno turno1;
    Turno turno2;
    Turno turno3;

    @Before
    public void setUp() throws Exception {
        veiculo1 = new Veiculo("ABC - 1234");
        veiculo2 = new Veiculo("PUQ - 7269");
        veiculo3 = new Veiculo("ANA - 123");
        vaga1 = new Vaga(1, 1);
        vaga2 = new Vaga(1, 2);
        vaga3 = new Vaga(3,1);
        cliente1 = new Cliente("Octávio", "3", TipoCliente.HORISTA);
        cliente2 = new Cliente("Xulamb", "1", TipoCliente.TURNONOITE);
        cliente3 = new Cliente("Ana", "2", TipoCliente.MENSALISTA);
        cliente1.addVeiculo(veiculo1);
        cliente2.addVeiculo(veiculo2);
        cliente3.addVeiculo(veiculo3);
        turno1 = Turno.MANHA;
        turno2 = Turno.TARDE;
        turno3 = Turno.NOITE;
        veiculo1.setCliente(cliente1);
        veiculo2.setCliente(cliente2);
        veiculo3.setCliente(cliente3);
    }

    @Test
    public void testEstacionarEArrecadado() throws VagaOcupadaException, UsoDeVagaException, VagaDesocupadaException {
        // Teste do método estacionar e totalArrecadado
        
        veiculo2.estacionar(vaga1, turno1);
        veiculo2.sair(vaga1);
        veiculo2.estacionar(vaga2,turno2);
        veiculo2.sair(vaga2);
        veiculo3.estacionar(vaga3, turno3);
        veiculo3.sair(vaga3);
        veiculo3.estacionar(vaga3, turno3);
        veiculo3.sair(vaga3);
        assertEquals(8.0, veiculo2.totalArrecadado(), 8.0);
    }

    @Test
    public void testSairEArrecadadoNoMes() throws VagaOcupadaException, UsoDeVagaException, VagaDesocupadaException {
        // Teste do método sair e arrecadadoNoMes
        veiculo2.estacionar(vaga1, turno1);
        veiculo2.sair(vaga1);
        veiculo2.estacionar(vaga2,turno2);
        veiculo2.sair(vaga2);

        veiculo3.estacionar(vaga3, turno3);
        veiculo3.sair(vaga3);

        
        assertEquals(8.0, veiculo2.arrecadadoNoMes(12), 8.0);
       
        
    }

    @Test
    public void testTotalDeUsos() throws VagaOcupadaException, UsoDeVagaException, VagaDesocupadaException {
        // Teste do método totalDeUsos
        veiculo1.estacionar(vaga3, turno3);
        veiculo1.sair(vaga3);
        veiculo1.estacionar(vaga2, turno1);
        veiculo1.sair(vaga2);
        assertEquals(2, veiculo1.totalDeUsos());
    }

     @Test
    public void testGerarRelatorioComPrioridade() throws VagaOcupadaException, UsoDeVagaException, VagaDesocupadaException {
        veiculo3.estacionar(vaga3, turno2);
        veiculo3.sair(vaga3);
        veiculo3.estacionar(vaga1, turno3);
        veiculo3.sair(vaga1);

       System.out.println(veiculo3.gerarRelatorio(Comparator.comparing(UsoDeVaga::getEntrada)));

      
    }
}

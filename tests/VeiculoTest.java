import org.junit.Test;

import Enums.TipoCliente;
import Enums.Turno;
import Exceptions.UsoDeVagaException;
import Exceptions.VagaDesocupadaException;
import Exceptions.VagaOcupadaException;

import org.junit.Before;
import static org.junit.Assert.*;

import java.util.ArrayList;
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
        turno1 = Turno.MANHA;
        turno2 = Turno.TARDE;
        turno3 = Turno.NOITE;
    }

    @Test
    public void testEstacionarEArrecadado() throws VagaOcupadaException {
        // Teste do método estacionar e totalArrecadado
        double valorEstacionamento = 10.0;
        veiculo1.estacionar(vaga1, turno1);
        assertEquals(valorEstacionamento, veiculo1.totalArrecadado(), 10.0);
    }

    @Test
    public void testSairEArrecadadoNoMes() throws VagaOcupadaException {
        // Teste do método sair e arrecadadoNoMes
        double valorEstacionamento = 10.0;
        int mes = 10;
        veiculo1.estacionar(vaga2, turno2);
        vaga2.sair();
        assertEquals(valorEstacionamento, veiculo1.arrecadadoNoMes(mes), 10.0);
    }

    @Test
    public void testTotalDeUsos() throws VagaOcupadaException {
        // Teste do método totalDeUsos
        Vaga vaga3 = new Vaga(2, 1);
        veiculo1.estacionar(vaga3, turno3);
        vaga3.sair();
        Vaga vaga4 = new Vaga(2, 1);
        veiculo1.estacionar(vaga4, turno1);
        assertEquals(2, veiculo1.totalDeUsos());
    }

     @Test
    public void testGerarRelatorioComPrioridade() throws VagaOcupadaException, UsoDeVagaException, VagaDesocupadaException {
        veiculo3.estacionar(vaga3, turno2);
        veiculo3.sair(vaga3);
        veiculo3.estacionar(vaga1, turno3);
        veiculo3.sair(vaga1);
    
        veiculo1.estacionar(vaga2, turno1);
        veiculo1.sair(vaga2);
    
        List<Veiculo> veiculos = new ArrayList<>();
        veiculos.add(veiculo1);
        veiculos.add(veiculo2);
        veiculos.add(veiculo3);
    

      
    }
}

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class EstacionamentoTest {
	private Estacionamento estacionamento;
	private String nomeMock;
	private String idMock;
	private Cliente cliente;
    private Veiculo veiculoMock;
    private String placaMock;
    private Vaga vaga;

	@Before
    public void setUp() {
		estacionamento = new Estacionamento("Estacionamento", 20, 9);

		nomeMock = "João";
		idMock = "1";
        cliente = new Cliente(nomeMock, idMock);

        placaMock = "ABC1234";
        veiculoMock = new Veiculo(placaMock);
        veiculoMock.estacionar(vaga);
    }

	@Test
	public void testAddVeiculo() {
		estacionamento.addVeiculo(veiculoMock, idMock);
		assertEquals(veiculoMock, cliente.possuiVeiculo("ABC1234"));
	}

	@Test
	public void testAddVeiculoJaCadastrado() {
		Veiculo veiculoMock2 = new Veiculo("ABC1234");

		estacionamento.addVeiculo(veiculoMock, idMock);
		estacionamento.addVeiculo(veiculoMock2, idMock);
		assertNotEquals(veiculoMock2, cliente.possuiVeiculo("ABC1234"));
	}

	// @Test
	// public void testAddCliente(Cliente cliente) {
	// 	estacionamento.addCliente(cliente);
	// 	assertEquals();
	// }

	// private void gerarVagas() {
	// 	int count = 0; 

	// 	for (int i = 0; i <= fileiras; i++) {
	// 		for (int j = 0; j <= vagasPorFileira; j++) {
	// 			Vaga vaga = new Vaga(i, j);

	// 			vagas[count] = vaga;
	// 			count++;
	// 		}
	// 	}
	// }

	// public void estacionar(String placa) {
	// 	for (int i = 0; i <= vagas.length; i++) {
	// 		if (vagas[i] != null && vagas[i].disponivel()) {
	// 			Veiculo veiculo = new Veiculo(placa);
	// 			veiculo.estacionar(vagas[i]);
	// 		}
	// 	}
	// }

	// public double sair(String placa) {
	// 	double valorTotal = 0;
	// 	Veiculo veiculo = new Veiculo(placa);

    //     for (int i = 0; i < id.length; i++) {
    //         if (id[i] != null) {
	// 			if (id[i].possuiVeiculo(placa).equals(veiculo)) {
	// 				valorTotal = veiculo.sair();
	// 			}
    //         }
    //     }

    //     return valorTotal;
	// }

	// public double totalArrecadado() {
	// 	double total = 0;

	// 	for (int i = 0; i <= id.length; i++) {
	// 		if (id[i] != null) {
	// 			total += id[i].arrecadadoTotal();
	// 		}
	// 	}

	// 	return total;
	// }

	// public double arrecadacaoNoMes(int mes) {
	// 	double totalMes = 0;

	// 	for (int i = 0; i <= id.length; i++) {
	// 		if (id[i] != null) {
	// 			totalMes += id[i].arrecadadoNoMes(mes);
	// 		}
	// 	}

	// 	return totalMes;
	// }

	// public double valorMedioPorUso() {
	// 	double totalDeUsos = 0;
	// 	double arrecadadoTotal = 0;

	// 	for (int i = 0; i <= id.length; i++) {
	// 		totalDeUsos += id[i].totalDeUsos();
	// 		arrecadadoTotal += id[i].arrecadadoTotal();
	// 	}

	// 	return arrecadadoTotal/totalDeUsos;
	// }

	// public String top5Clientes(int mes) {
	// 	double arrayArrecadado[] = {0,0,0,0,0};
	// 	String topClientes[] = new String[5];

	// 	for (int i = 0; i <= id.length; i++) {
	// 		double arrecadado = id[i].arrecadadoNoMes(mes);
	// 		for (int j = 0; j <= 5; j++) {
	// 			if (arrecadado > arrayArrecadado[j]) {
	// 				arrayArrecadado[j] = arrecadado;
	// 				topClientes[j] = id[i].toString();
	// 			}
	// 		}
	// 	}

	// 	return "";
	// }

}

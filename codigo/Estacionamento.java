

public class Estacionamento {

	private String nome;
	private Cliente[] id;
	private Vaga[] vagas;
	private int fileiras; 
	private int vagasPorFileira; 

	public Estacionamento(String nome, int fileiras, int vagasPorFileira) {
		this.nome = nome;
		this.fileiras = fileiras;
		this.vagasPorFileira = vagasPorFileira;

		this.gerarVagas();
	}

	public void addVeiculo(Veiculo veiculo, String idCli) {
		Cliente novoCliente = new Cliente(idCli);

		for(int i = 0; i <= id.length; i++) {
			if (id[i].equals(novoCliente)) {
				id[i].addVeiculo(veiculo);
			}
		}
	}

	public void addCliente(Cliente cliente) {
		for(int i = 0; i <= id.length; i++) {
			if (id[i] == null) {
				id[i] = cliente;
			} 
		}
	}

	private void gerarVagas() {
		int count = 0; 

		for (int i = 0; i <= fileiras; i++) {
			for (int j = 0; j <= vagasPorFileira; j++) {
				Vaga vaga = new Vaga(i, j);

				vagas[count] = vaga;
				count++;
			}
		}
	}

	public void estacionar(String placa) {
		for (int i = 0; i <= vagas.length; i++) {
			if (vagas[i] != null && vagas[i].disponivel()) {
				Veiculo veiculo = new Veiculo(placa);
				veiculo.estacionar(vagas[i]);
			}
		}
	}

	public double sair(String placa) {
		double valorTotal = 0;
		Veiculo veiculo = new Veiculo(placa);

        for (int i = 0; i < id.length; i++) {
            if (id[i] != null) {
				if (id[i].possuiVeiculo(placa).equals(veiculo)) {
					valorTotal = veiculo.sair();
				}
            }
        }

        return valorTotal;
	}

	public double totalArrecadado() {
		double total = 0;

		for (int i = 0; i <= id.length; i++) {
			if (id[i] != null) {
				total += id[i].arrecadadoTotal();
			}
		}

		return total;
	}

	public double arrecadacaoNoMes(int mes) {
		double totalMes = 0;

		for (int i = 0; i <= id.length; i++) {
			if (id[i] != null) {
				totalMes += id[i].arrecadadoNoMes(mes);
			}
		}

		return totalMes;
	}

	public double valorMedioPorUso() {
		double totalDeUsos = 0;
		double arrecadadoTotal = 0;

		for (int i = 0; i <= id.length; i++) {
			totalDeUsos += id[i].totalDeUsos();
			arrecadadoTotal += id[i].arrecadadoTotal();
		}

		return arrecadadoTotal/totalDeUsos;
	}

	public String top5Clientes(int mes) {
		double arrayArrecadado[] = {0,0,0,0,0};
		String topClientes[] = new String[5];

		for (int i = 0; i <= id.length; i++) {
			double arrecadado = id[i].arrecadadoNoMes(mes);
			for (int j = 0; j <= 5; j++) {
				if (arrecadado > arrayArrecadado[j]) {
					arrayArrecadado[j] = arrecadado;
					topClientes[j] = id[i].toString();
				}
			}
		}

		return "";
	}

}

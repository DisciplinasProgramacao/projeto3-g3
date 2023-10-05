import java.util.ArrayList;
import java.util.List;

public class Veiculo {

	private String placa;
	private List <UsoDeVaga> usos;
	private int qntdVagasUsadas;

	public Veiculo(String placa) {
		this.placa = placa;
		this.qntdVagasUsadas = 0;
	}

	public void estacionar(Vaga vaga) {
		if(vaga.disponivel()){
				UsoDeVaga usos = new UsoDeVaga(vaga);
				this.usos.add(usos);
				qntdVagasUsadas++;
			}
		}

	public double sair() {
	
	}

	public double totalArrecadado() {
		
	}

	public double arrecadadoNoMes(int mes) {
		
	}

	public int totalDeUsos() {
		
	}

}

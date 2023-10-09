import java.util.ArrayList;

public class Veiculo {

	private String placa;
	private ArrayList<UsoDeVaga> usos;

	public Veiculo(String _placa) {
		placa = _placa;
		usos = new ArrayList<UsoDeVaga>();
	}

	public String getPlaca() {
		return placa;
	}

	public ArrayList<UsoDeVaga> getUsos() {
		return usos;
	}

	public void estacionar(Vaga vaga) {

	}

	public double sair() {

	}

	public double totalArrecadado() {

	}

	public double arrecadadoNoMes(int mes) {

	}

	public int totalDeUsos() {

	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Veiculo) {
			Veiculo veiculo = (Veiculo) obj;
			return placa.equals(veiculo.getPlaca());
		}
		return false;
	}

	@Override
	public String toString() {
		return "Veículo: " + placa + " | Usos: " + usos;
	}

}

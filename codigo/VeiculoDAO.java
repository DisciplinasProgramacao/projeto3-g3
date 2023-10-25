public interface VeiculoDAO{
	
	void estacionar(Vaga vaga);
	double sair(Vaga vaga);
	double totalArrecadado();
	double arrecadadoNoMes(int mes);
	int totalDeUsos();
}
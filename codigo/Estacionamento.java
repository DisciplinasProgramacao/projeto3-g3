

public class Estacionamento {

	private String nome;
	private Cliente[] id;
	private Vaga[] vagas;
	private int quantFileiras;
	private int vagasPorFileira;

	public Estacionamento(){
		this.nome = nome;
		this.quantFileiras = quantFileiras;
		this.vagasPorFileira = vagasPorFileira;
	}

	public String getnome() {
		return	this.nome;
	}

	public void setnome(){
		this.nome = nome;
	}

	public int getquantFileiras(){
		return this.quantFileiras;
	}

	public void setquantFileiras(){
		this.quantFileiras = quantFileiras;
	}

	public int getvagasPorFileiras(){
		return this.vagasPorFileira;
	}

	public void setvagasPorFileiras(){
		this.vagasPorFileira = vagasPorFileira;
	}


	public Estacionamento(String nome, int fileiras, int vagasPorFila) {
		
	}

	public void addVeiculo(Veiculo veiculo, String idCli) {
		
	}

	public void addCliente(Cliente cliente) {
		
	}

	private void gerarVagas() {
		
	}

	public void estacionar(String placa) {
		
	}

	public double sair(String placa) {
		
	}

	public double totalArrecadado() {
		
	}

	public double arrecadacaoNoMes(int mes) {
		
	}

	public double valorMedioPorUso() {
		
	}

	public String top5Clientes(int mes) {
		
	}

}

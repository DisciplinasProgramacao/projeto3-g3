import java.time.Duration;
import java.time.LocalDateTime;

public class UsoDeVaga {

	private static final double FRACAO_USO = 0.25;
	private static final double VALOR_FRACAO = 4.0;
	private static final double VALOR_MAXIMO = 50.0;
	private Vaga vaga;
	private LocalDateTime entrada;
	private LocalDateTime saida;
	private double valorPago;

	public UsoDeVaga(Vaga vaga) {
		this.vaga = vaga;
        this.entrada = LocalDateTime.now();
        this.saida = null;
        this.valorPago = 0.0;
	}

	public double sair() {
		if (saida == null) {
			saida = LocalDateTime.now();
			Duration duracao = Duration.between(entrada, saida);
			long minutos = duracao.toMinutes();

			double valor = minutos / 15 * VALOR_FRACAO;

			if (valor > VALOR_MAXIMO) {
				valor = VALOR_MAXIMO;
			}

			valorPago = valor;

			//Eu sei q ta com erro, sou apenas burro, tenha calma.
			Estacionamento.vagas[vaga] = null;
		}
	}

	public boolean ehDoMes(int mes){
		if (saida == null) {
            return false;
        }
        return saida.getMonthValue() == mes;
    
	}
	
	public double valorPago() {
		return valorPago;
	}

}

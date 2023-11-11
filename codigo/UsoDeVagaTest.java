import static org.junit.Assert.*;
import java.time.LocalDateTime;
import org.junit.Test;

public class UsoDeVagaTest<Cliente> {

    @Test
    public <Vaga> void testCalcularValorPago() {
        Cliente cliente = new cliente("Amanda", "abc123");
        Vaga vaga = new vaga("A1");
        LocalDateTime entrada = LocalDateTime.of(2023, 10, 18, 9, 04);
        UsoDeVaga uso = new UsoDeVaga(cliente, vaga, entrada);
        LocalDateTime saida = LocalDateTime.of(2023, 10, 18, 11, 04);
        uso.sair(saida);

        double valorEsperado = 20.0;
        double valorCalculado = uso.valorPago();

        assertEquals(valorEsperado, valorCalculado, 0.01); // verificar se são iguais
    }

}

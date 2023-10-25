import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Exceptions.*;
import Enums.*;

import java.time.LocalDateTime;

public class UsoDeVagaTest {

  private Vaga vaga;
  private UsoDeVaga usoDeVaga;

  @BeforeEach
  public void setUp() throws VagaOcupadaException {
    vaga = new Vaga(1, 1);
    usoDeVaga = new UsoDeVaga(vaga);
  }

  @Test
  public void testSairSemServico() throws UsoDeVagaException, VagaDesocupadaException {
    LocalDateTime entrada = LocalDateTime.now().minusHours(2);
    LocalDateTime saida = LocalDateTime.now();
    usoDeVaga = new UsoDeVaga(vaga);
    usoDeVaga.setEntrada(entrada);
    usoDeVaga.setSaida(saida);
    double valorEsperado = 16.0;
    Assertions.assertEquals(valorEsperado, usoDeVaga.valorPago());
  }

  @Test
  public void testSairComServico() throws UsoDeVagaException, VagaDesocupadaException {
    LocalDateTime entrada = LocalDateTime.now().minusHours(3);
    LocalDateTime saida = LocalDateTime.now();
    Servicos servico = new Servicos("Lavagem", 2, 10.0);
    usoDeVaga = new UsoDeVaga(vaga, servico);
    usoDeVaga.setEntrada(entrada);
    usoDeVaga.setSaida(saida);
    double valorEsperado = 24.0;
    Assertions.assertEquals(valorEsperado, usoDeVaga.valorPago());
  }

  @Test
  public void testSairSemTempoMinimoDeServico() throws VagaOcupadaException {
    Servicos servico = new Servicos("Lavagem", 2, 10.0);
    usoDeVaga = new UsoDeVaga(vaga, servico);
    Assertions.assertThrows(VagaDesocupadaException.class, () -> usoDeVaga.sair());
  }

  @Test
  public void testSairComTempoMinimoDeServico() throws UsoDeVagaException, VagaDesocupadaException {
    LocalDateTime entrada = LocalDateTime.now().minusHours(3);
    LocalDateTime saida = LocalDateTime.now();
    Servicos servico = new Servicos("Lavagem", 2, 10.0);
    usoDeVaga = new UsoDeVaga(vaga, servico);
    usoDeVaga.setEntrada(entrada);
    usoDeVaga.contratarServico(servico);
    usoDeVaga.setSaida(saida);
    double valorEsperado = 34.0;
    Assertions.assertEquals(valorEsperado, usoDeVaga.valorPago());
  }

  @Test
  public void testEhDoMes() throws UsoDeVagaException, VagaDesocupadaException {
    LocalDateTime entrada = LocalDateTime.of(2021, 10, 1, 10, 0);
    LocalDateTime saida = LocalDateTime.of(2021, 10, 1, 12, 0);
    usoDeVaga = new UsoDeVaga(vaga);
    usoDeVaga.setEntrada(entrada);
    usoDeVaga.setSaida(saida);
    Assertions.assertTrue(usoDeVaga.ehDoMes(10));
    Assertions.assertFalse(usoDeVaga.ehDoMes(11));
  }
}
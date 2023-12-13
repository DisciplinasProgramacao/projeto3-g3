import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import Enums.Servicos;
import Exceptions.UsoDeVagaException;
import Exceptions.VagaDesocupadaException;
import Exceptions.VagaOcupadaException;

import java.time.LocalDateTime;

public class UsoDeVagaTest {

  private Vaga vaga;
  private Servicos servicos;
  private UsoDeVaga usoHorista, usoComServico, uso;

  @Before
  public void setUp() throws Exception {
    vaga = new Vaga(1, 1);
  }

  
  @Test
  public void testUsarVagaSemServicoValorMaximo()
      throws VagaOcupadaException, UsoDeVagaException, VagaDesocupadaException {
    UsoHorista usoHorista = new UsoHorista(vaga);
    double valorPago = usoHorista.sair(LocalDateTime.now().plusHours(10));
    assertEquals(50.0, valorPago, 0.01);
  }

  @Test
  public void testUsarVagaSemServicoValorMinimo()
      throws VagaOcupadaException, UsoDeVagaException, VagaDesocupadaException {
     UsoHorista usoHorista = new UsoHorista(vaga);
    double valorPago = usoHorista.sair(LocalDateTime.now().plusMinutes(14));
    assertEquals(4.0, valorPago, 0.01);
  }

  @Test
  public void testUsarVagaComServico() throws VagaOcupadaException, UsoDeVagaException, VagaDesocupadaException {
    UsoHorista usoComServico = new UsoHorista(vaga);
    usoComServico.contratarServico(Servicos.LAVAGEM);
    double valorPago = usoComServico.sair(LocalDateTime.now().plusHours(2));
    assertEquals(56.0, valorPago, 0.01);
  }

  @Test
  public void testSaidaInvalida() throws VagaOcupadaException, UsoDeVagaException, VagaDesocupadaException {
    UsoHorista uso = new UsoHorista(vaga);
    uso.contratarServico(Servicos.POLIMENTO);
    assertEquals(0.0, uso.calcularValorPago(), 0.01);
    assertThrows(VagaDesocupadaException.class,
        () -> uso.sair(LocalDateTime.now().plusMinutes(30)));
  }

  @Test
  public void testSaidaValida() throws VagaOcupadaException, UsoDeVagaException, VagaDesocupadaException {
     UsoHorista uso = new UsoHorista(vaga);
    LocalDateTime saida = LocalDateTime.now().plusMinutes(30);
    uso.sair(saida);
    assertEquals(saida, uso.sair(saida));
  }

  @Test
  public void testEhDoMes() throws VagaOcupadaException, UsoDeVagaException, VagaDesocupadaException {
     UsoHorista uso = new UsoHorista(vaga);
    LocalDateTime saidaMesDiferente = LocalDateTime.of(2023, 10, 10, 10, 10);
    uso.sair(LocalDateTime.now());
    assertTrue(uso.ehDoMes(LocalDateTime.now().getMonthValue()));
    assertFalse(uso.ehDoMes(saidaMesDiferente.getMonthValue()));

  }
}
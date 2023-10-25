import Exceptions.UsoDeVagaException;
import Exceptions.VagaDesocupadaException;
import Exceptions.VagaOcupadaException;

public class App {
   public static void main(String[] args) throws VagaOcupadaException, UsoDeVagaException, VagaDesocupadaException {
      Cliente cliente = new Cliente("Ana", "1");
      Cliente cliente2 = new Cliente();
      Veiculo veiculo1 = new Veiculo("123");
      Veiculo veiculo2 = new Veiculo("321");
      Vaga vaga1 = new Vaga(1, 1);
      Vaga vaga2 = new Vaga(2, 2);
      cliente.addVeiculo(veiculo1);
      cliente.addVeiculo(veiculo2);

      cliente.possuiVeiculo("123");
      cliente.possuiVeiculo("321");

      veiculo1.estacionar(vaga1);
      veiculo1.sair(vaga1);
      veiculo2.estacionar(vaga2);
      veiculo2.sair(vaga1);
      System.out.println(veiculo1.totalArrecadado());

      System.out.println(veiculo1);
      System.out.println(veiculo2);
      System.out.println(cliente.arrecadadoPorVeiculo("123"));
      System.out.println(cliente.arrecadadoTotal());
      // System.out.println(cliente2);
   }
}

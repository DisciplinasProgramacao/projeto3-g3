public class App {
   public static void main(String[] args) {
      Cliente cliente = new Cliente("Ana", "1");
      Cliente cliente2 = new Cliente();
      Veiculo veiculo = new Veiculo("123");
      cliente.addVeiculo(veiculo);
      cliente.possuiVeiculo("123");
      System.out.println(cliente);
      System.out.println(cliente2);
   }
}

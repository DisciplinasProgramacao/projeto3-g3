public class App {
   public static void main(String[] args) {
      Cliente cliente = new Cliente("Ana", "1");
      Cliente cliente2 = new Cliente();
      Veiculo veiculo1 = new Veiculo("123");
      Veiculo veiculo2 = new Veiculo("321");
      cliente.addVeiculo(veiculo1);
      cliente.addVeiculo(veiculo2);

      System.out.println(veiculo1);
      System.out.println(veiculo2);

      cliente.possuiVeiculo("123");
      cliente.possuiVeiculo("321");

      System.out.println(cliente);
      System.out.println(cliente2);
   }
}

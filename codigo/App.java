public class App {
 public static void main(String[] args) {
    Cliente cliente = new Cliente("Ana", "1");
    Veiculo veiculo =  new Veiculo("123");
    cliente.addVeiculo(veiculo);
    cliente.possuiVeiculo("123");
    System.out.print(cliente);
 }   
}

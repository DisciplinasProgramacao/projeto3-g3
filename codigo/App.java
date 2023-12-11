
import java.util.Scanner;

import Exceptions.UsoDeVagaException;
import Exceptions.VagaDesocupadaException;
import Exceptions.VagaOcupadaException;

public class App {
    /**
     * @param args
     * @throws VagaDesocupadaException
     * @throws UsoDeVagaException
     * @throws VagaOcupadaException
     */
    public static void main(String[] args) throws UsoDeVagaException, VagaDesocupadaException, VagaOcupadaException {
        Scanner scanner = new Scanner(System.in);

        Estacionamento estacionamento = new Estacionamento("EstacionamentoG2", 20, 9);
        Cliente cliente = new Cliente(); 
        Veiculo veiculo = new Veiculo(null);
        System.out.printf("Insira o Id do cliente:", cliente);
        scanner.nextInt();
        System.out.printf("Insira a placa do veiculo", veiculo );
        scanner.nextLine();
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Adicionar Cliente");
            System.out.println("2. Adicionar Veículo a um Cliente");
            System.out.println("3. Estacionar Veículo");
            System.out.println("4. Sair do Estacionamento");
            System.out.println("5. Total Arrecadado");
            System.out.println("6. Arrecadação no Mês");
            System.out.println("7. Valor Médio por Uso");
            System.out.println("8. Top 5 Clientes");
           System.out.println("9. Emitir relatório por data de entrada");
           System.out.println("10. Emitir relatorio por data de saida");
           System.out.println("11. Emitir relatorio por preço");
            System.out.println("12. Sair do Programa");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar a nova linha

            switch (opcao) {
                case 1:
                    // Adicionar um cliente
                    estacionamento.addCliente(null);
                    break;
                case 2:
                    // Adicionar veículo a um cliente
                    cliente.addVeiculo(null);
                    break;
                case 3:
                    veiculo.estacionar(null, null);
                    break;
                case 4:
                    veiculo.sair(null);
                    // Sair do estacionamento
                    break;
                case 5:
                    cliente.arrecadadoTotal();
                    break;
                case 6:
                    // Arrecadação no mês
                    
                    System.out.println("Digite o número do mês: ");
                    int mes = scanner.nextInt(); 
                    System.out.println("Digite o número do ano: ");
                    int ano = scanner.nextInt();                  
                    cliente.arrecadadoNoMes(mes);
                    break;
                case 7:
                    estacionamento.valorMedioPorUso();
                    break;
                case 8:
                    // Top 5 Clientes
                    System.out.println("Digite o número do mês: ");
                    int mes2 = scanner.nextInt(); 
                    System.out.println("Digite o número do ano: ");
                    int ano2 = scanner.nextInt();   
                    estacionamento.top5Clientes(mes2);
                    break;
                // case 9: 
                //      Veiculo.listarPorDataUltimaSaida(null);
                //      break;
                // case 10: 
                //     Veiculo.listarPorDataUltimaEntrada(null);
                //     break;
                // case 11: 
                //     Veiculo.gerarListaPorValorPago(null);
                //     break;
                case 9:
                    // Sair do programa
                    System.out.println("Saindo do programa.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
}
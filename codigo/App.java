import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Enums.TipoCliente;
import Enums.Turno;
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
    static long id = 100;

    private static Map<String, Veiculo> getVeiculos = new HashMap<>();
    private static Map<String, Cliente> getCLientes = new HashMap<>();

     private static void adicionarCliente(Scanner scanner, Estacionamento estacionamento) {
        
    }

    private static void exibirMenu(){
          System.out.println("Menu:");
            System.out.println("1. Adicionar Cliente");
            System.out.println("2. Adicionar Veículo a um Cliente");
            System.out.println("3. Estacionar Veículo");
            System.out.println("4. Sair do Estacionamento");
            System.out.println("5. Total Arrecadado por cliente");
            System.out.println("6. Arrecadação no Mês por cliente");
            System.out.println("7. Total de usos por veiculo: ");
            System.out.println("8. Valor Médio por Uso");
            System.out.println("9. Top 5 Clientes");
            System.out.println("10. Total de usos por Cliente: ");
            System.out.println("11. Arrecadação por veículo");
            System.out.println("!2. Arrecadação por mes do veiculo: ");
            System.out.println("13. Arrecadação total do estacionamento: ");
            System.out.println("14. Arrecadação por mês do estacionamento: ");


    }

    public static void main(String[] args) throws UsoDeVagaException, VagaDesocupadaException, VagaOcupadaException, IOException {
        Scanner scanner = new Scanner(System.in);

        Estacionamento estacionamento = new Estacionamento("EstacionamentoG2", 20, 9);
        Cliente cliente = new Cliente();
    
          
           
            System.out.print("-----Escolha uma opção: ");
            exibirMenu();
            System.out.println("20. Sair do Programa");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar a nova linha

            
            while(opcao != 20){
            switch (opcao) {
                case 1:
                   veiculoMenu();
                   break;
                case 2:
                    // Adicionar veículo a um cliente
                    System.out.println("Digite a Placa do Veículo:");
                    String placa = scanner.nextLine();
                    Veiculo veiculo = new Veiculo(placa);
                    cliente.addVeiculo(veiculo);
                   System.out.println("Veículo adicionado com sucesso");
               
                break;
                case 3:
                   estacionamento.estacionar(placa, null);
                case 4:
                    
                   estacionamento.sair(placa);
                    System.out.println("Saida do estacionamento do veiculo de placa" + veiculo);
                    // Sair do estacionamento
                    break;
                case 5:
                    // Arrecadação total por cliente
                    cliente.arrecadadoTotal();
                    System.out.println("Arrecadado total: " + cliente.arrecadadoTotal());
                    break;
                case 6:
                    // Arrecadação no mês por cliente
                    
                    System.out.println("Digite o número do mês: ");
                    int mes = scanner.nextInt();               
                    cliente.arrecadadoNoMes(mes);
                    
                    System.out.println("Arrecadado no mes: " +  cliente.arrecadadoNoMes(mes));
                    break;
                
                case 7:
                // Valor total de uso por veiculo
                //veiculo.totalDeUsos(); 
                break;
                    case 8:
                    // Valor medio por uso
                    estacionamento.valorMedioPorUso();
                    
                    System.out.println("Valor médio por uso: " + estacionamento.valorMedioPorUso());
                    break;
                case 9:
                    // Top 5 Clientes
                    System.out.println("Digite o número do mês: ");
                    int mes2 = scanner.nextInt();   
                    estacionamento.top5Clientes(mes2);
                    
                    System.out.println("Top5 Clientes: " + estacionamento.top5Clientes(mes2));
                    break;
                  case 10:
                  // Valor total de uso por clientes
                 cliente.totalDeUsos();
                 
                 System.out.println("Total de usos: " + cliente.totalDeUsos());
                 break;
                 case 11: 
                    // Valor arrecadado por veiculo
                    System.out.println("Insisra a placa do veículo: ");
                    String veiculo1 = scanner.nextLine();
                     cliente.arrecadadoPorVeiculo(veiculo1);
                     
                    System.out.println("Arrecadado por veiculo: " + cliente.arrecadadoPorVeiculo(veiculo1));
                 break;
                 case 12:
                 System.out.println("Insisra a placa do veículo: ");
               Veiculo veiculo3 = new Veiculo(scanner.nextLine());
                System.out.println("Insira o mes que deseja emitir a arrecadação: ");
                 int mes3 = scanner.nextInt();
                veiculo3.arrecadadoNoMes(mes3);
            
                System.out.println("Arrecadado no mes: " + veiculo3.arrecadadoNoMes(mes3));
                 break;
                 case 13:
                 // Valor total arrecadado pelo estafcionamento
                 estacionamento.totalArrecadado(); 
                 System.out.println("Arrecadado total: + estacionamento " + estacionamento.totalArrecadado() );
                 break;
                 case 14:
                 // arrecadação mensal do estacionamento
                 System.out.println("Insira o mes que deseja emitir a arrecadação: ");
                 int mes4 = scanner.nextInt();
                 estacionamento.arrecadacaoNoMes(mes4); 
                 
                 System.out.println("Arrecadado total: " + estacionamento.arrecadacaoNoMes(mes4));
                 break;

                case 20 :
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
    public static void veiculoMenu() throws IOException{;
       try (Scanner scanner = new Scanner(System.in)) {
        int escolha;
       Estacionamento estacionamento = new Estacionamento("EstacionamentoG2", 20, 9);
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2. Cadastrar veículo");
        System.out.println("3.Alterar Plano");
        System.out.println("4. Voltar ao menu principal");
        escolha = scanner.nextInt();
        while(escolha !=4){
        switch (escolha) {
        case 1:
        System.out.println("Insira o nome do Cliente:");
        String nome = scanner.nextLine();
         System.out.println("Escolha o tipo de cliente:");
      System.out.println("1. Horista");
      System.out.println("2. Mensalista");
      System.out.println("3. Turno Manha");
      System.out.println("4. Turno Tarde");
      System.out.println("5. Tuno Noite");
      int opcaoTipo = scanner.nextInt();
      scanner.nextLine(); // Limpar a nova linha

      TipoCliente tipo = null;

      switch (opcaoTipo) {
        case 1:
            tipo = TipoCliente.HORISTA;
            break;
        case 2:
            tipo = TipoCliente.MENSALISTA;
            break;
        case 3:
            tipo = TipoCliente.TURNOMANHA;
            break;
            case 4: 
            tipo = TipoCliente.TURNOTARDE;
            break;
            case 5: 
            tipo = TipoCliente.TURNONOITE;
            break;
        default:
            System.out.println("Opção inválida.");
            return;
      }

      Cliente cliente = new Cliente(nome, String.valueOf(id), tipo);
      System.out.println("Cliente adicionado com sucesso!");
      getCLientes.put(String.valueOf(id), cliente);
      id ++;
                break;
         case 2:
             // Adicionar veículo a um cliente
             System.out.println("Digite o id do cliente: ");
             String id = scanner.nextLine();
             System.out.println("Digite a Placa do Veículo:");
             String placa = scanner.nextLine();
             Veiculo veiculo = new Veiculo(placa);
             Cliente cliente2 = getCLientes.get(id);
            estacionamento.addVeiculo(veiculo, id);
            System.out.println("Veículo adicionado com sucesso");
            ClienteDAO daoClienteDAO = new ClienteDAO("Clientela.txt");
             daoClienteDAO.abrirEscrita();
             daoClienteDAO.add(cliente2);
             daoClienteDAO.fechar();;
         break;
         case 3:
         System.out.println("Opção não implementada devido a falte pessoal. Estamos trabalhando para aprimorarmos sua experiencia :)"); 
         break;
         case 4: 
         System.out.println("Retornando ao menu principal");            
            default:
            System.out.println("Opção invalida");            
                         
                break;
        }

      }
    }

}}
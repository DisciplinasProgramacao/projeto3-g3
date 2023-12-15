import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Enums.TipoCliente;
import Enums.Turno;
import Enums.Servicos;
import Exceptions.UsoDeVagaException;
import Exceptions.VagaDesocupadaException;
import Exceptions.VagaOcupadaException;

public class App<Servico> {
    /**
     * @param args
     * @throws VagaDesocupadaException
     * @throws UsoDeVagaException
     * @throws VagaOcupadaException
     */
    static long id = 100;

    private static Map<String, Veiculo> getVeiculos = new HashMap<>();
    private static Map<String, Cliente> getCLientes = new HashMap<>();


    private static void exibirMenu(){
          System.out.println("Menu:");
            System.out.println("1. Estacionamento menu");
            System.out.println("2. Veiculo Menu");
            System.out.println("3. Sair");


    }

    public static void main(String[] args) throws UsoDeVagaException, VagaDesocupadaException, VagaOcupadaException, IOException {
        Scanner scanner = new Scanner(System.in);    
          
           
            System.out.print("-----Escolha uma opção: ");
            exibirMenu();
            System.out.println("20. Sair do Programa");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar a nova linha

            
            while(opcao != 3){
            switch (opcao) {
                case 1:
                   estacionamentoMenu();
                   break;
                case 2:
                    veiculoMenu();
                break;
                case 3 :
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
    public static void estacionamentoMenu() throws IOException{
       try (Scanner scanner = new Scanner(System.in)) {
        int escolha;
       Estacionamento estacionamento = new Estacionamento("EstacionamentoG2", 20, 9);
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2. Cadastrar veículo");
        System.out.println("3.Alterar Plano");
        System.out.println("4. Arrecadação por mês do estacionamento: ");
        System.out.println("5. Top 5 Clientes");
        System.out.println("6. Arrecadação total do estacionamento: ");
        System.out.println("7. Valor médio por uso: ");
        System.out.println("8. Voltar ao menu principal");
        escolha = scanner.nextInt();
        while(escolha !=10){
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
            System.out.println("Informe o mes que deseja emitir o relatório");
            int mes = scanner.nextInt();
           try {
            if(mes<1 || mes>12) {
                System.out.println("Mes invalido");
            }
            
           } catch (Exception e) {
                System.out.println("Mes invalido");
           }
           double arrecadacaoNoMes = estacionamento.arrecadacaoNoMes(mes);
           System.out.println("O valor arrecadado pelo estacionamento no mes"+ mes+ "foi de " +arrecadacaoNoMes );

         break;
         case 5:
          System.out.println("Informe o mes que deseja emitir o relatório");
            int mes1= scanner.nextInt();
           try {
            if(mes1<1 || mes1>12) {
                System.out.println("Mes invalido");
            }
            
           } catch (Exception e) {
                System.out.println("Mes invalido");
           } 
             String clientes = estacionamento.top5Clientes(mes1);
             System.out.println("O top5 de clientes do estacionamento no mes"+mes1+"está nesse moemnto assim"+clientes);

         break;
         case 6:
         double totalArrecadado = estacionamento.totalArrecadado();
         System.out.println("O total arrecadado é de:" + totalArrecadado);

         break;
         case 7:
         double media = estacionamento.valorMedioPorUso();
         
         System.out.println("O valor medio por uso é de:" + media);

         break;
            case 8: 
         System.out.println("Retornando ao menu principal");            
            default:
            System.out.println("Opção invalida");            
                         
                break;
        }
      
      }
    }
}
  public static void veiculoMenu() throws UsoDeVagaException, VagaDesocupadaException{
        try (Scanner scanner = new Scanner(System.in)) {
        int escolha;
       Estacionamento estacionamento = new Estacionamento("EstacionamentoG2", 20, 9);
        System.out.println("1. Estacionar ");
        System.out.println("2. Sair");
        System.out.println("3.Relatório");
        System.out.println("4. Voltar ao menu principal");
        escolha = scanner.nextInt();
        while(escolha !=4){
        switch (escolha) {
            case 1:
            int option;
            scanner.nextLine();
              System.out.print("Digite a placa do carro: ");
              String placa = scanner.nextLine();
              System.out.println("Gostaraia de contrtatar algum serviço? 1. Sim 2.Não");
             option = scanner.nextInt();
             switch (option) {
                case 1: 

                System.out.print("Qual serviço você gostaria de contratar(MANOBRISTA/LAVAGEM/POLIMENTO)? ");
                String opcaoServico = scanner.nextLine().toUpperCase();
                try {
                    Servicos servico = Servicos.valueOf(opcaoServico);
                    estacionamento.estacionar(placa, servico);
                    System.out.println("Serviço de " + servico.getServicodeDesc() + " contratado!");
                    System.out.println("Veículo de placa: " + placa + " estacionado!");
                }
                catch (IllegalArgumentException e) {
                    System.out.println("Serviço inválido. Escolha entre MANOBRISTA, LAVAGEM ou POLIMENTO.");
                } catch (VagaOcupadaException e) {
                    System.out.println("Serviço não encontrado");
                }                    
                    break;
                case 2:
                System.out.print("Digite a placa do carro: ");
                String placa1 = scanner.nextLine();
                double valorPago = 0;
                try {
                    valorPago = estacionamento.sair(placa1);
                    Veiculo veiculo = getVeiculos.get(placa1);
                    VeiculoDAO daoVeiculo = new VeiculoDAO("veiculos.txt");
                    try {
                        daoVeiculo.abrirEscrita();
                        daoVeiculo.add(veiculo);
                        daoVeiculo.fechar();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Valor a pra ser pago: "+ valorPago);
                } catch (IllegalStateException  e) {
                    System.out.println("Ocorreu um erro ao sair do estacionamento: " + e.getMessage());
                }

            }

                break;
                case 3:
                System.out.print("Digite a placa do carro: ");
                String placa2 = scanner.nextLine();
                try {
                Veiculo veiculo = getVeiculos.get(placa2);
                System.out.println(veiculo.gerarRelatorio(null));
                    
                } catch (Exception e) {
                    System.out.println("Veiculo de placa" + placa2 +"não encontrado");
                }
                break;
               
                case 4: 
                System.out.println("Saindo");
             break;
                default:
                System.out.println("Valor não reconhecido, selecione uma opção válida");
                    break;
             }
            break;

    }
        }}}
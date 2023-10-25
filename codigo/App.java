import java.util.Scanner;

public class App {

   public static void main(String[] args) {

      Estacionamento estacionamento = null;
      Cliente cliente = null;
      Veiculo veiculo = null;
      Vaga vaga = new Vaga(1,10);

      try (Scanner scanner = new Scanner(System.in)) {
         int mainOption = 0;

         do {
            System.out.println("===============================================");
            System.out.println("Bem Vindo ao Gerenciamento de Estacionamento.\n");
            System.out.println("Escolha uma opção:");
            System.out.println("1. Menu Estacionamento:");
            System.out.println("2. Menu Cliente:");
            System.out.println("3. Menu Veículo:");
            System.out.println("4. Menu Vaga:");
            System.out.println("5. Gerar Relatório.");
            System.out.println("6. Sair");

            mainOption = scanner.nextInt();

            switch (mainOption) {
               case 1:
                  int subOptionEst;
                  do {
                     System.out.println("===============================================");
                     System.out.println("Menu Estacionamento:");
                     System.out.println("Escolha uma opção:");
                     System.out.println("1. Cadastrar Estacionamento");
                     System.out.println("2. Listar Estacionamentos");
                     System.out.println("3. Voltar ao menu principal");

                     subOptionEst = scanner.nextInt();

                     switch (subOptionEst) {
                        case 1:
                           // Solicitar informações do estacionamento
                           System.out.println("Informe o nome do estacionamento:");
                           String nome = scanner.next();
                           System.out.println("Informe a quantidade de fileiras:");
                           int fileiras = scanner.nextInt();
                           System.out.println("Informe a quantidade de vagas por fila:");
                           int vagasPorFila = scanner.nextInt();

                           // Criar uma instância de Estacionamento
                           estacionamento = new Estacionamento(nome, fileiras, vagasPorFila);
                           System.out.println("Estacionamento cadastrado com sucesso!");
                           break;
                        case 2:
                           System.out.println("Opção 2 do submenu Estacionamento escolhida.");
                           break;
                        case 3:
                           System.out.println("Retornando ao menu principal.");
                           break;
                        default:
                           System.out.println("Opção inválida no submenu Estacionamento.");
                           break;
                     }
                  } while (subOptionEst != 3);
                  break;

               case 2:
                  int subOptionCli;
                  do {
                     System.out.println("===============================================");
                     System.out.println("Menu Cliente:");
                     System.out.println("Escolha uma opção:");
                     System.out.println("1. Cadastrar Cliente:");
                     System.out.println("2. Listar Clientes");
                     System.out.println("3. Voltar ao menu principal");

                     subOptionCli = scanner.nextInt();

                     switch (subOptionCli) {
                        case 1:
                           // Solicitar informações do Cliente
                           System.out.println("Informe o nome do Cliente:");
                           String nome = scanner.next();
                           System.out.println("Informe o id do Cliente:");
                           String id = scanner.next();
                           // Criar uma instância de Cliente
                           cliente = new Cliente(nome, id); //////////////////////////////////// COMO RESOLVER????????
                           System.out.println("Cliente cadastrado com sucesso!");
                           break;
                        case 2:
                           System.out.println("Listar clientes."); //a ser implementada
                           break;
                        case 3:
                           System.out.println("Retornando ao menu principal.");
                           break;
                        default:
                           System.out.println("Opção inválida no submenu Cliente.");
                           break;
                     }
                  } while (subOptionCli != 3);
                  break;

               case 3:
                 int subOptionVei;
                  do {
                     System.out.println("===============================================");
                     System.out.println("Menu Veículo:");
                     System.out.println("Escolha uma opção:");
                     System.out.println("1. Cadastrar Veículo:");
                     System.out.println("2. Listar Veículos:");
                     System.out.println("3. Voltar ao menu principal");

                     subOptionVei = scanner.nextInt();

                     switch (subOptionVei) {
                        case 1:
                           // Solicitar informações do Veiculo
                           System.out.println("Informe placa do veículo:");
                           String placa = scanner.next();
                           // Criar uma instância de Veiculo
                           veiculo = new Veiculo(placa);
                           System.out.println("Veículo cadastrado com sucesso!");
                           break;
                        case 2:
                           System.out.println("Listar veículos cadastrados."); //a ser implementada
                           break;
                        case 3:
                           System.out.println("Retornando ao menu principal.");
                           break;
                        default:
                           System.out.println("Opção inválida no submenu Veículo.");
                           break;
                     }
                  } while (subOptionVei != 3);
                  break;

               case 4:
                  int subOptionVaga;
                  do {
                     System.out.println("===============================================");
                     System.out.println("Menu Vaga:");
                     System.out.println("Escolha uma opção:");
                     System.out.println("1. Estacionar Veículo:");
                     System.out.println("2. Sair da Vaga:");
                     System.out.println("3. Voltar ao menu principal");

                     subOptionVaga = scanner.nextInt();

                     switch (subOptionVaga) {
                        case 1:
                            // Verifica se a vaga está disponível
                           if (vaga.disponivel()) {
                              boolean estacionado = vaga.estacionar();
                              if (estacionado) {
                                 System.out.println("Veículo estacionado com sucesso!");
                              } else {
                                 System.out.println("A vaga já está ocupada. Não é possível estacionar.");
                              }
                           } else {
                                 System.out.println("A vaga já está ocupada. Não é possível estacionar.");
                           }
                           break;
                        case 2:
                           // Verifica se a vaga está ocupada
                           if (!vaga.disponivel()) {
                              boolean liberado = vaga.sair();
                                 if (liberado) {
                                    System.out.println("Veículo saiu da vaga com sucesso!");
                                 } else {
                                    System.out.println("A vaga já está disponível. Não é possível liberar.");
                                 }
                           } else {
                                 System.out.println("A vaga já está disponível. Não é possível liberar.");
                           }
                           break;
                        case 3:
                           System.out.println("Retornando ao menu principal.");
                           break;
                        default:
                           System.out.println("Opção inválida no submenu Vaga.");
                           break;
                     }
                  } while (subOptionVaga != 3);
                  break;

               case 5:
                  int subOptionRelat;
                  do {
                     System.out.println("===============================================");
                     System.out.println("Menu Relatório:");
                     System.out.println("Escolha uma opção:");
                     System.out.println("1. Valor total arrecadado do estacionamento.");
                     System.out.println("2. Valor arrecadado em determinado mês.");
                     System.out.println("3. Valor médio de cada utilização do estacionamento.");
                     System.out.println("4. Ranking dos clientes que mais geraram arrecadação em um determinado mês.");
                     System.out.println("5. Voltar ao menu principal");

                     subOptionRelat = scanner.nextInt();

                     switch (subOptionRelat) {
                        case 1:
                           // Solicitar informações do estacionamento
                           System.out.println("Informe o nome do estacionamento:");
                           String nome = scanner.next();
                           break;
                        case 2:
                           System.out.println("Informe o mês da arrecadação:");
                           break;
                        case 3:
                           System.out.println("Informe o nome do estacionamento:");
                           break;
                        case 4:
                           System.out.println("Informe o nome do estacionamento:");
                           break;
                        case 5:
                           System.out.println("Retornando ao menu principal.");
                           break;
                        default:
                           System.out.println("Opção inválida no submenu Vaga.");
                           break;
                     }
                  } while (subOptionRelat != 5);
                  break;

               case 6:
                  System.out.println("Obrigado por usar o menu!");
                  break;

               default:
                  System.out.println("Opção inválida no menu principal.");
                  break;
            }
         } while (mainOption != 6);
      }
   }
}

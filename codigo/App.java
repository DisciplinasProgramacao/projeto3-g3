import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import Exceptions.VagaOcupadaException;

public class App {

    // #region utilidades
    static Scanner teclado;

    private static Estacionamento[] novo = new Estacionamento[3];
    
    /**
     * Pausa para leitura de mensagens em console
     * 
     * @param teclado Scanner de leitura
     */
    static void pausa() {
        System.out.println("Enter para continuar.");
        teclado.nextLine();
    }

    /**
     * Encapsula uma leitura de teclado, com mensagem personalizada. A mensagem
     * sempre é completada com ":". Retorna uma string
     * que pode ser posteriormente convertida.
     * 
     * @param mensagem A mensagem a ser exibida, sem pontuação final.
     * @return String lida do usuário.
     */
    public static String leitura(String mensagem) {
        System.out.print(mensagem + ": ");
        return teclado.nextLine();
    }

    /**
     * Método para montagem de menu. Lê as opções de um arquivo e as numera
     * automaticamente a partir de 1.
     * 
     * @param nomeArquivo Nome do arquivo texto com as opções (uma por linha)
     * @return Opção do usuário (int)
     * @throws FileNotFoundException em caso de arquivo não encontrado.
     */
    public static int menu(String nomeArquivo) throws FileNotFoundException {

        File arqMenu = new File(nomeArquivo);
        Scanner leitor = new Scanner(arqMenu, "UTF-8");
        System.out.println(leitor.nextLine());
        System.out.println("==========================");
        int contador = 1;
        while (leitor.hasNextLine()) {
            System.out.println(contador + " - " + leitor.nextLine());
            contador++;
        }
        System.out.println("0 - Sair");
        System.out.print("\nSua opção: ");
        int opcao = Integer.parseInt(teclado.nextLine());
        leitor.close();
        return opcao;
    }
    // #endregion

    // #region métodos do app (encapsulam ações de usuário)

    public static int escolhaEstacionamento(){
        System.out.println("Escolha o estacionamento (1, 2 ou 3): ");
        int escolhaEstacionamento = Integer.parseInt(teclado.nextLine());

        switch (escolhaEstacionamento) {
            case 1:
                Estacionamento novo1 = new Estacionamento("Minas Gerais", 10, 10);
                break;
            case 2:
                Estacionamento novo2 = new Estacionamento("São Paulo", 15, 20);
                break;
            case 3:
                Estacionamento novo3 = new Estacionamento("Rio de Janeiro", 13, 15);
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
        return escolhaEstacionamento;
    }

    

    /**
     * 
     * @throws FileNotFoundException Em caso de arquivo não encontrado para mostar o
     *                               menu, lança exceção sem tratamento de erro (por
     *                               enquanto)
     */
    public static Estacionamento menuEstacionamento() throws FileNotFoundException {
        String nomeArq = "menuEstacionamento";
        int opcao = -1;

        

        opcao = menu(nomeArq);
        switch (opcao) {
            case 1 -> {
            String nomeCliente = "";
            String idCliente = "";
                leitura("Digite o nome do cliente e o seu identificador");
                nomeCliente = teclado.nextLine();
                idCliente = teclado.nextLine();

                Cliente novoCliente = new Cliente(nomeCliente, idCliente);
                Estacionamento.addCliente(nomeCliente);

                System.out.println("Cliente adicionado com sucesso.");

            }
            case 2 -> {
                leitura("Digite a placa do veículo");
                String novaPlaca = teclado.nextLine();
                leitura("Digite o identificador do cliente");
                String idCliente = teclado.nextLine();

                Veiculo novoVeiculo = new Veiculo(novaPlaca);

                Estacionamento.addVeiculo(Veiculo, idCliente);

                System.out.println("Veículo adicionado com sucesso.");
            }
            case 3 -> {
                // Inserir o veículo na vaga
                leitura("Digite a placa do veículo");
                String novaPlaca = teclado.nextLine();
                try {
                    if (Estacionamento.estacionar(novaPlaca)==true) {
                        System.out.println("Veículo estacionado com sucesso.");
                    } else {
                        System.out.println("Nenhuma vaga disponível nesse estacionamento.");
                    }
                } catch (VagaOcupadaException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            case 0 -> {
                System.out.println("Retornando ao menu principal.");
            }
            default -> {
                System.out.println("Escolha inválida");
            }
        }
        while (opcao != 0)
            ;

        return menuEstacionamento();

    }

    // #endregion

    public static void main(String[] args) throws Exception {

        teclado = new Scanner(System.in);
        escolhaEstacionamento();
        
        String nomeArq = "menuEstacionamento";
        int opcao = -1;
        while (opcao != 0) {
            opcao = menu(nomeArq);
            switch (opcao) {
                case 1 -> menuEstacionamento();
            }
        }
        System.out.println("Estamos sempre a disposição para melhor atende-lo.");
        teclado.close();
    }

}
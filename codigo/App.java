import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Exceptions.VagaOcupadaException;


public class App {

    //#region utilidades
    static Scanner teclado;

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
     * Encapsula uma leitura de teclado, com mensagem personalizada. A mensagem sempre é completada com ":". Retorna uma string 
     * que pode ser posteriormente convertida.
     * @param mensagem A mensagem a ser exibida, sem pontuação final.
     * @return String lida do usuário.
     */
    public static String leitura(String mensagem){
        System.out.print(mensagem+": ");
        return teclado.nextLine();
    }

    /**
     * Método para montagem de menu. Lê as opções de um arquivo e as numera automaticamente a partir de 1.
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
        while(leitor.hasNextLine()){
            System.out.println(contador + " - " + leitor.nextLine());
            contador++;
        }
        System.out.println("0 - Sair");
        System.out.print("\nSua opção: ");
        int opcao = Integer.parseInt(teclado.nextLine());
        leitor.close();
        return opcao;
    }
    //#endregion

     //#region métodos do app (encapsulam ações de usuário)






    /**
     * 
     * @throws FileNotFoundException Em caso de arquivo não encontrado para mostar o menu, lança exceção sem tratamento de erro (por enquanto)
     */
    public static Estacionamento menuEstacionamento() throws FileNotFoundException{
        String nomeArq = "menuEstacionamento";
        int opcao = -1;
        
        Estacionamento novo = null;

         // Solicitar informações do estacionamento
         System.out.println("Informe o nome do estacionamento:");
         String nome = teclado.next();
         System.out.println("Informe a quantidade de fileiras:");
         int fileiras = teclado.nextInt();
         System.out.println("Informe a quantidade de vagas por fila:");
         int vagasPorFila = teclado.nextInt();

         // Criar uma instância de Estacionamento
         novo = new Estacionamento(nome, fileiras, vagasPorFila);
         System.out.println("Estacionamento cadastrado com sucesso!");

        opcao = menu(nomeArq);
        switch(opcao){
            case 1 -> {
                    System.out.println("Adicionando um cliente ao cadastro do estacionamento.");
                
                    // Aqui você pode criar um novo cliente com os detalhes desejados
                    Cliente novoCliente = new Cliente("Nome do Cliente", "ID do Cliente"); // Substitua com os detalhes reais.
                
                    // Agora, chame o método addCliente da instância do estacionamento para adicionar o cliente.
                    novo.addCliente(novoCliente);
                
                    System.out.println("Cliente adicionado com sucesso.");
                } 
            case 2 -> {
                System.out.println("Adicionando um veículo ao cadastro do estacionamento.");
                
                Veiculo novoVeiculo = new Veiculo("nome");

                novo.addVeiculo(novoVeiculo, nome);
                
            }
            case 3 -> {
                //Inserir o veículo na vaga 
                try {
                    if (novo.estacionar("ABC1234")){
                        System.out.println("Veículo estacionado com sucesso.");
                    } else {
                        System.out.println("Nenhuma vaga disponível nesse estacionamento.");
                    }
                } catch (VagaOcupadaException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }                
            }
            default-> {
                System.out.println("Escolha inválida");
            }
        }
        //novo = menuVeiculo(novo);
        return novo;
      
    }
    
    //#endregion

     public static void main(String[] args) throws Exception{
        teclado = new Scanner(System.in);
        String nomeArq = "menuEstacionamento";
        int opcao = -1;
        while(opcao!=0){
            opcao = menu(nomeArq);
            switch(opcao){
                case 1-> menuEstacionamento();
            }
        }
        System.out.println("Estamos sempre a disposição para melhor atende-lo.");
        teclado.close();
    }
    
}

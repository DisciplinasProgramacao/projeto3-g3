import java.nio.charset.Charset;
import java.util.*;
import java.io.*;

/**
 * Classe que implementa um Data Access Object (DAO) para a entidade Veiculo.
 * Permite operações de leitura e escrita de objetos Veiculo em um arquivo.
 * 
 * @param <Veiculo> O tipo de entidade que a classe manipula.
 */
public class VeiculoDAO implements Dao<Veiculo> {

    // #region atributos
    /**
     * Lista que armazena os objetos Veiculo em memória.
     */
    private List<Veiculo> veiculos = new ArrayList<>();

    /**
     * Nome do arquivo associado ao DAO.
     */
    private String nomeArq;

    /**
     * Scanner utilizado para leitura do arquivo.
     */
    private Scanner arqLeitura;

    /**
     * FileWriter utilizado para escrita no arquivo.
     */
    private FileWriter arqEscrita;
    // #endregion

    // #region construtor
    /**
     * Construtor da classe VeiculoDAO.
     * 
     * @param nomeArq O nome do arquivo associado ao DAO.
     */
    public VeiculoDAO(String nomeArq) {
        this.nomeArq = nomeArq;
        this.arqEscrita = null;
        this.arqLeitura = null;
    }
    // #endregion

    // #region métodos de negócio

    /**
     * Abre o arquivo para leitura.
     * 
     * @throws IOException Se ocorrer um erro de leitura.
     */
    public void abrirLeitura() throws IOException {
        if (arqEscrita != null) {
            arqEscrita.close();
            arqEscrita = null;
        }
        arqLeitura = new Scanner(new File(nomeArq), Charset.forName("UTF-8"));
    }

    /**
     * Abre o arquivo para escrita.
     * 
     * @throws IOException Se ocorrer um erro de escrita.
     */
    public void abrirEscrita() throws IOException {
        if (arqLeitura != null) {
            arqLeitura.close();
            arqLeitura = null;
        }
        arqEscrita = new FileWriter(nomeArq, Charset.forName("UTF-8"), true);
    }

    /**
     * Fecha os recursos de leitura e escrita.
     * 
     * @throws IOException Se ocorrer um erro ao fechar os recursos.
     */
    public void fechar() throws IOException {
        if (arqEscrita != null)
            arqEscrita.close();
        if (arqLeitura != null)
            arqLeitura.close();
        arqEscrita = null;
        arqLeitura = null;
    }

    /**
     * Obtém o próximo objeto Veiculo do arquivo de leitura.
     * 
     * @return O próximo objeto Veiculo.
     */
    public Veiculo getNext() {
        String[] linha = arqLeitura.nextLine().split(";");

        String placa = linha[0].toLowerCase();

        return new Veiculo(placa);
    }

    /**
     * Adiciona um objeto Veiculo ao arquivo de escrita.
     * 
     * @param v O objeto Veiculo a ser adicionado.
     * @throws IOException Se ocorrer um erro de escrita.
     */
    public void add(Veiculo v) throws IOException {
        arqEscrita.append(v.dataToText() + "\n");
    }

    /**
     * Obtém todos os objetos Veiculo do arquivo de leitura.
     * 
     * @return Uma lista contendo todos os objetos Veiculo.
     * @throws IOException Se ocorrer um erro de leitura.
     */
    public List<Veiculo> getAll() throws IOException {
        List<Veiculo> dados = new ArrayList<>();
        try {
            fechar();
            abrirLeitura();
            while (arqLeitura.hasNext()) {
                dados.add(getNext());
            }
        } catch (IOException exception) {
            arqEscrita = null;
            arqLeitura = null;
            dados = null;
        }
        dados = List.copyOf(dados);
        return dados;
    }

    /**
     * Adiciona uma lista de objetos Veiculo ao arquivo de escrita.
     * 
     * @param veiculos A lista de objetos Veiculo a serem adicionados.
     */
    public void addAll(List<Veiculo> veiculos) {
        try {
            fechar();
            abrirEscrita();
            for (Veiculo veiculo : veiculos) {
                if (veiculo != null) {
                    add(veiculo);
                }
            }
        } catch (IOException exception) {
            arqEscrita = null;
            arqLeitura = null;
        }
    }

    /**
     * Remove um objeto Veiculo da lista em memória.
     * 
     * @param veiculo O objeto Veiculo a ser removido.
     */
    public void delete(Veiculo veiculo) {
        veiculos.remove(veiculo);
    }
    // #endregion
}

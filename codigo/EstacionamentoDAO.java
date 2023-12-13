import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Classe responsável por operações de leitura e escrita de dados relacionados a
 * estacionamentos.
 */
public class EstacionamentoDAO implements Dao<Estacionamento> {

  // #region atributos
  private List<Estacionamento> estacionamentos = new ArrayList<>();
  private String nomeArq;
  private Scanner arqLeitura;
  private FileWriter arqEscrita;
  // #endregion

  // #region construtor
  /**
   * Construtor da classe EstacionamentoDAO.
   *
   * @param nomeArq Nome do arquivo que será manipulado.
   */
  public EstacionamentoDAO(String nomeArq) {
    this.nomeArq = nomeArq;
    this.arqLeitura = null;
    this.arqEscrita = null;
  }
  // #endregion

  // #region métodos de negócio
  /**
   * Abre o arquivo para leitura.
   *
   * @throws IOException Se ocorrer um erro ao abrir o arquivo.
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
   * @throws IOException Se ocorrer um erro ao abrir o arquivo.
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
   * Obtém o próximo estacionamento do arquivo.
   *
   * @return O próximo estacionamento do arquivo.
   */
  public Estacionamento getNext() {
    String[] linha = arqLeitura.nextLine().split(";");
    String nome = linha[0].toLowerCase();
    int fileiras = Integer.parseInt(linha[1]);
    int vagasPorFila = Integer.parseInt(linha[2]);

    return new Estacionamento(nome, fileiras, vagasPorFila);
  }

  /**
   * Obtém todos os estacionamentos do arquivo.
   *
   * @return Lista contendo todos os estacionamentos do arquivo.
   * @throws IOException Se ocorrer um erro ao ler os estacionamentos do arquivo.
   */
  public List<Estacionamento> getAll() throws IOException {
    List<Estacionamento> dados = new ArrayList<>();
    try {
      fechar();
      abrirLeitura();

      while (arqLeitura.hasNextLine()) {
        dados.add(getNext());
      }

    } catch (IOException e) {
      arqLeitura = null;
      arqEscrita = null;
      dados = null;
    }

    dados = List.copyOf(dados);
    return dados;
  }

  /**
   * Adiciona um estacionamento ao arquivo.
   *
   * @param e O estacionamento a ser adicionado.
   * @throws IOException Se ocorrer um erro ao escrever o estacionamento no
   *                     arquivo.
   */
  public void add(Estacionamento e) throws IOException {
    arqEscrita.append(e.dataToText() + "\n");
  }

  /**
   * Adiciona uma lista de estacionamentos ao arquivo.
   *
   * @param t Lista de estacionamentos a serem adicionados.
   */
  public void addAll(List<Estacionamento> t) {
    try {
      fechar();
      abrirEscrita();

      for (Estacionamento estacionamento : estacionamentos) {
        if (estacionamento != null) {
          add(estacionamento);
        }
      }
    } catch (Exception e) {
      arqEscrita = null;
      arqLeitura = null;
    }
  }

  /**
   * Remove um estacionamento da lista.
   *
   * @param c O estacionamento a ser removido.
   */
  public void delete(Estacionamento c) {
    estacionamentos.remove(c);
  }
  // #endregion

}

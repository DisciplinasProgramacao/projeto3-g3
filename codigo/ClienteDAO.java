import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

import Enums.TipoCliente;
import Enums.Turno;

/**
 * Classe responsável por operações de leitura e escrita de dados relacionados
 * ao
 * cliente.
 */
public class ClienteDAO implements Dao<Cliente> {

  // #region atributos
  private List<Cliente> clientes = new ArrayList<>();
  private String nomeArq;
  private Scanner arqLeitura;
  private FileWriter arqEscrita;
  // #endregion

  // #region Construtores/inicializadores
  public ClienteDAO(String nomeArq) {
    this.nomeArq = nomeArq;
    this.arqLeitura = null;
    this.arqEscrita = null;
  }
  // #endregion

  // #region Métodos de negócio

  /**
   * Abre o arquivo para leitura.
   * 
   * @throws IOException Se ocorrer um erro ao abrir o arquivo.
   */
  public void abrirLeitura() throws IOException {
    if (arqLeitura != null) {
      arqLeitura.close();
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
  public Cliente getNext() {
    String[] linha = arqLeitura.nextLine().split(";");
    String nome = linha[0].toLowerCase();
    String id = linha[1].toLowerCase();

    String strTipo = linha[2].toLowerCase();
    TipoCliente tipo = TipoCliente.HORISTA;

    switch (strTipo) {
      case "horista":
        tipo = TipoCliente.HORISTA;
        break;

      case "mensalista":
        tipo = TipoCliente.MENSALISTA;
        break;

      case "turnomanha":
        tipo = TipoCliente.TURNO;
        tipo.setTurno(Turno.MANHA);
        break;

      case "turnotarde":
        tipo = TipoCliente.TURNO;
        tipo.setTurno(Turno.TARDE);
        break;

      case "turnonoite":
        tipo = TipoCliente.TURNO;
        tipo.setTurno(Turno.NOITE);
        break;
    }

    return new Cliente(nome, id, tipo);
  }

  /**
   * Obtém todos os clientes do arquivo.
   *
   * @return Lista contendo todos os clientes do arquivo.
   * @throws IOException Se ocorrer um erro ao ler os clientes do arquivo.
   */
  public List<Cliente> getAll() throws IOException {
    List<Cliente> dados = new ArrayList<>();
    try {
      fechar();
      abrirLeitura();
      while (arqLeitura.hasNextLine()) {
        dados.add(getNext());
      }
    } catch (IOException e) {
      arqEscrita = null;
      arqLeitura = null;
      dados = null;
    }
    dados = List.copyOf(dados);
    return dados;
  }

  /**
   * Adiciona um cliente ao arquivo.
   *
   * @param c O cliente a ser adicionado.
   * @throws IOException Se ocorrer um erro ao escrever o cliente no
   *                     arquivo.
   */
  public void add(Cliente c) throws IOException {
    arqEscrita.append(c.dataToText() + "\n");
  }

  /**
   * Adiciona uma lista de clientes ao arquivo.
   *
   * @param clientes Lista de clientes a serem adicionados.
   */
  public void addAll(List<Cliente> clientes) {
    try {
      fechar();
      abrirEscrita();
      for (Cliente cliente : clientes) {
        if (cliente != null) {
          add(cliente);
        }
      }
    } catch (IOException e) {
      arqEscrita = null;
      arqLeitura = null;
    }
  }

  /**
   * Remove um cliente da lista.
   *
   * @param c O cliente a ser removido.
   */
  public void delete(Cliente c) {
    clientes.remove(c);
  }
  // #endregion
}

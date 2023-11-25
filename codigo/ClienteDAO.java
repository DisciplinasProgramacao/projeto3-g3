import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

public class ClienteDAO implements DAO<Cliente> {
  private List<Cliente> clientes = new ArrayList<>();
  private String nomeArq;
  private Scanner arqLeitura;
  private FileWriter arqEscrita;

  public ClienteDAO(String nomeArq) {
    this.nomeArq = nomeArq;
    this.arqLeitura = null;
    this.arqEscrita = null;
  }

  public void abrirLeitura() throws IOException {
    if (arqLeitura != null) {
      arqLeitura.close();
      arqEscrita = null;
    }
    arqLeitura = new Scanner(new File(nomeArq), Charset.forName("UTF-8"));
  }

  public void abrirEscrita() throws IOException {
    if (arqLeitura != null) {
      arqLeitura.close();
      arqLeitura = null;
    }
    arqEscrita = new FileWriter(nomeArq, Charset.forName("UTF-8"), true);
  }

  public void fechar() throws IOException {
    if (arqEscrita != null)
      arqEscrita.close();
    if (arqLeitura != null)
      arqLeitura.close();
    arqEscrita = null;
    arqLeitura = null;
  }

  public Cliente getNext() {
    String[] linha = arqLeitura.nextLine().split(";");
    String nome = linha[0].toLowerCase();
    String id = linha[1].toLowerCase();
    return new Cliente(nome, id);
  }

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

  public void add(Cliente c) throws IOException {
    arqEscrita.append(c.dataToText() + "\n");
  }

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

  public void delete(Cliente c) {
    clientes.remove(c);
  }
}

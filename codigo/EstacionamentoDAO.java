import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

public class EstacionamentoDAO implements DAO<Estacionamento> {

  //#region atributos
  private List<Estacionamento> estacionamentos = new ArrayList<>();
  private String nomeArq;
  private Scanner arqLeitura;
  private FileWriter arqEscrita;
  //#endregion

  
  public EstacionamentoDAO(String nomeArq) {
    this.nomeArq = nomeArq;
    this.arqLeitura = null;
    this.arqEscrita = null;
  }

  public void abrirLeitura() throws IOException {
    if (arqEscrita != null) {
      arqEscrita.close();
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

  public Estacionamento getNext() {
    String[] linha = arqLeitura.nextLine().split(";");
    String nome = linha[0].toLowerCase();
    int fileiras = Integer.parseInt(linha[1]);
    int vagasPorFila = Integer.parseInt(linha[2]);
    return new Estacionamento(nome, fileiras, vagasPorFila);
  }

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

  public void add(Estacionamento e) throws IOException {
    arqEscrita.append(e.dataToText() + "\n");
  }

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

  public void delete(Estacionamento c) {
    estacionamentos.remove(c);
  }

}

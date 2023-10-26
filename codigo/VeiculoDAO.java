import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

public class VeiculoDAO implements Dao<Veiculo> {
    private List<Veiculo> veiculos = new ArrayList<>();

    private String nomeArq;
    private Scanner arqLeitura;
    private FileWriter arqEscrita;

    public VeiculoDAO(String nomeArq) {
        this.nomeArq = nomeArq;
        this.arqEscrita = null;
        this.arqLeitura = null;
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

    public Veiculo getNext() {
        String[] linha = arqLeitura.nextLine().split(";");

        String placa = linha[0].toLowerCase();

        return new Veiculo(placa);
    }

    public void add(Veiculo v) throws IOException {
        arqEscrita.append(v.dataToText() + "\n");
    }

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

    public void delete(Veiculo veiculo) {
        veiculos.remove(veiculo);
    }
}
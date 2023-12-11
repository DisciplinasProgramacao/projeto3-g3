import java.nio.charset.Charset;
import java.util.*;
import java.io.*;

public class VeiculoDAO implements DAO<Veiculo> {

    //#region atributos
    private List<Veiculo> veiculos = new ArrayList<>();
    private String nomeArq;
    private Scanner arqLeitura;
    private FileWriter arqEscrita;
    //#endregion

    //#region construtor
    public VeiculoDAO(String nomeArq) {
        this.nomeArq = nomeArq;
        this.arqEscrita = null;
        this.arqLeitura = null;
    }
    //#endregion

    //#region métodos de negócio

    /**
     * 
     * @throws IOException
     */
    public void abrirLeitura() throws IOException {
        if (arqEscrita != null) {
            arqEscrita.close();
            arqEscrita = null;
        }
        arqLeitura = new Scanner(new File(nomeArq), Charset.forName("UTF-8"));
    }

    /**
     * 
     * @throws IOException
     */
    public void abrirEscrita() throws IOException {
        if (arqLeitura != null) {
            arqLeitura.close();
            arqLeitura = null;
        }
        arqEscrita = new FileWriter(nomeArq, Charset.forName("UTF-8"), true);
    }

    /**
     * 
     * @throws IOException
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
     * 
     */
    public Veiculo getNext() {
        String[] linha = arqLeitura.nextLine().split(";");

        String placa = linha[0].toLowerCase();

        return new Veiculo(placa);
    }

    /**
     * 
     */
    public void add(Veiculo v) throws IOException {
        arqEscrita.append(v.dataToText() + "\n");
    }

    /**
     * 
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
     * 
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
     * 
     */
    public void delete(Veiculo veiculo) {
        veiculos.remove(veiculo);
    }
    //#endregion
}
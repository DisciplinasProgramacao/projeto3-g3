import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FabricaUsoDeVaga {

    private Map<String, IFabrica<UsoDeVaga>> fabricas;
    private static String arqConf = "arqFabricas";
   
    public FabricaUsoDeVaga() throws FileNotFoundException {
        fabricas = new HashMap<>();

        Scanner leitura = new Scanner(new File(arqConf));

        while (leitura.hasNextLine()) {
            String[] linha = leitura.nextLine().split(";");
            String produto = linha[0];
            String fabrica = linha[1];
            IFabrica<UsoDeVaga> classeFabrica;

            try {
                classeFabrica = (IFabrica<UsoDeVaga>)Class.forName(fabrica).getConstructor().newInstance();
                fabricas.put(produto, classeFabrica);
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException
                    | ClassNotFoundException e) {
                classeFabrica = null;
                System.err.println("Problemas criando a fábrica " + fabrica);
            }
        }
        
        leitura.close();
    }

    public void setArquivo(String nomeArq){
        arqConf = nomeArq;
    }

    public UsoDeVaga get(String descricao){
        return fabricas.get(descricao).create();
    }
}

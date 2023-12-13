import java.io.IOException;
import java.util.List;

/**
 * Interface genérica para operações de acesso a dados (Data Access Object -
 * DAO).
 *
 * @param <T> Tipo de objeto que a interface manipula, deve implementar
 *            {@link IDataToText}.
 */
public interface Dao<T extends IDataToText> {

    /**
     * Obtém o próximo objeto do armazenamento de dados.
     *
     * @return O próximo objeto do armazenamento.
     */
    public T getNext();

    /**
     * Obtém todos os objetos do armazenamento de dados.
     *
     * @return Uma lista contendo todos os objetos armazenados.
     * @throws IOException Se ocorrer um erro de leitura dos dados.
     */
    public List<T> getAll() throws IOException;

    /**
     * Adiciona um objeto ao armazenamento de dados.
     *
     * @param t O objeto a ser adicionado.
     * @throws IOException Se ocorrer um erro ao escrever o objeto no armazenamento.
     */
    public void add(T t) throws IOException;

    /**
     * Adiciona uma lista de objetos ao armazenamento de dados.
     *
     * @param t A lista de objetos a serem adicionados.
     */
    public void addAll(List<T> t);

    /**
     * Remove um objeto do armazenamento de dados.
     *
     * @param t O objeto a ser removido.
     */
    public void delete(T t);
}

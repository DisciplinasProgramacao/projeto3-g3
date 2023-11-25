import java.io.IOException;
import java.util.List;

public interface DAO<T extends IDataToText> {
    public T getNext();

    public List<T> getAll() throws IOException;

    public void add(T t) throws IOException;

    public void addAll(List<T> t);

    public void delete(T t);
}

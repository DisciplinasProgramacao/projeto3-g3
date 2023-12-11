import Exceptions.VagaOcupadaException;

public interface IFabrica<T> {
    public T create(Vaga vaga) throws VagaOcupadaException;
} 
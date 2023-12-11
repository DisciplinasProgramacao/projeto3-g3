import Enums.Turno;
import Exceptions.VagaOcupadaException;

public interface IFabrica<T> {
    public T create(Vaga vaga) throws VagaOcupadaException;

    // public T create(Turno turno, Vaga vaga);
} 
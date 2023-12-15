import Exceptions.VagaOcupadaException;

/**
 * Fábrica para criar instâncias de {@link UsoHorista}.
 */
public class FabricaHorista implements IFabrica<UsoDeVaga> {

    /**
     * Cria uma instância de {@link UsoHorista} associada à vaga especificada.
     *
     * @param vaga A vaga associada à instância de {@link UsoHorista} a ser criada.
     * @return Uma instância de {@link UsoHorista}.
     * @throws VagaOcupadaException Se a vaga estiver ocupada.
     */
    @Override
    public UsoDeVaga create(Vaga vaga) throws VagaOcupadaException {
        return new UsoHorista(vaga);
    }
}

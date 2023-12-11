import Exceptions.VagaOcupadaException;

public class FabricaHorista implements IFabrica<UsoDeVaga> {

    @Override
    public UsoDeVaga create(Vaga vaga) throws VagaOcupadaException {
        return new UsoHorista(vaga);
    }
    
}
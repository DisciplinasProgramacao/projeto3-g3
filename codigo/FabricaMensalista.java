import Exceptions.VagaOcupadaException;

public class FabricaMensalista implements IFabrica<UsoDeVaga> {

    @Override
    public UsoDeVaga create(Vaga vaga) throws VagaOcupadaException {
        return new UsoMensalista(vaga);
    }
    
}

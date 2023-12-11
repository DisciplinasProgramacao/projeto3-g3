import Exceptions.VagaOcupadaException;

import Enums.Turno;

public class FabricaTurnoNoite implements IFabrica<UsoDeVaga> {

    @Override
    public UsoDeVaga create(Vaga vaga) throws VagaOcupadaException {
        return new UsoTurno(Turno.NOITE, vaga);
    }
    
}
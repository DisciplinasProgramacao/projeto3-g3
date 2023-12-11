import Exceptions.VagaOcupadaException;

import Enums.Turno;

public class FabricaTurnoTarde implements IFabrica<UsoDeVaga> {

    @Override
    public UsoDeVaga create(Vaga vaga) throws VagaOcupadaException {
        return new UsoTurno(Turno.TARDE, vaga);
    }
    
}
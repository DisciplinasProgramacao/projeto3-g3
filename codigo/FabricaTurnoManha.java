import Exceptions.VagaOcupadaException;

import Enums.Turno;

public class FabricaTurnoManha implements IFabrica<UsoDeVaga> {

    @Override
    public UsoDeVaga create(Vaga vaga) throws VagaOcupadaException {
        return new UsoTurno(Turno.MANHA, vaga);
    }
    
}


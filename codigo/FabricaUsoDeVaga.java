import java.util.HashMap;
import java.util.Map;

import Enums.TipoCliente;
import Exceptions.VagaOcupadaException;

public class FabricaUsoDeVaga {

    private Map<TipoCliente, IFabrica<UsoDeVaga>> fabricas;

    public FabricaUsoDeVaga(){
        fabricas = new HashMap<>();
        fabricas.put(TipoCliente.HORISTA, new FabricaHorista());
        fabricas.put(TipoCliente.MENSALISTA, new FabricaMensalista());
        fabricas.put(TipoCliente.TURNOMANHA, new FabricaTurnoManha());
        fabricas.put(TipoCliente.TURNOTARDE, new FabricaTurnoTarde());
        fabricas.put(TipoCliente.TURNONOITE, new FabricaTurnoNoite());
    }

    public UsoDeVaga get(TipoCliente tipo, Vaga vaga){
        try {
            return fabricas.get(tipo).create(vaga);
        } catch (VagaOcupadaException v) {
            return null;
        }
    }
}
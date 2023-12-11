import java.util.HashMap;
import java.util.Map;

import Exceptions.VagaOcupadaException;

public class FabricaUsoDeVaga {

    private Map<String, IFabrica<UsoDeVaga>> fabricas;

    public FabricaUsoDeVaga(){
        fabricas = new HashMap<>();
        fabricas.put("horista", new FabricaHorista());
        fabricas.put("mensalista", new FabricaMensalista());
        fabricas.put("turnomanha", new FabricaTurnoManha());
        fabricas.put("turnotarde", new FabricaTurnoTarde());
        fabricas.put("turnonoite", new FabricaTurnoNoite());
    }

    public UsoDeVaga get(String desc, Vaga vaga){
        try {
            return fabricas.get(desc).create(vaga);
        } catch (VagaOcupadaException v) {
            return null;
        }
    }
}
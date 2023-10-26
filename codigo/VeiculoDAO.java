import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class VeiculoDAO implements Dao<Veiculo> {
    private List<Veiculo> veiculos = new ArrayList<>();

    public VeiculoDAO() {
        veiculos.add(new Veiculo("123"));
        veiculos.add(new Veiculo("321"));
    }

    @Override
    public Optional<Veiculo> get(long id) {
        return Optional.ofNullable(veiculos.get((int) id));
    }

    @Override
    public List<Veiculo> getAll() {
        return veiculos;
    }

    @Override
    public void save(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    @Override
    public void update(Veiculo veiculo, String[] params) {
        veiculo.setPlaca(Objects.requireNonNull(
          params[0], "Placa cannot be null"));
        veiculos.add(veiculo);
    }

    @Override
    public void delete(Veiculo veiculo) {
        veiculos.remove(veiculo);
    }
}
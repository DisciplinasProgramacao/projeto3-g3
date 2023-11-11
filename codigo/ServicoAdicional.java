public enum ServicoAdicional {
    MANOBRISTA(5.0),
    LAVAGEM(20.0),
    POLIMENTO(45.0);

    private final double custo;

    ServicoAdicional(double custo) {
        this.custo = custo;
    }

    public double getCusto() {
        return custo;
    }
}

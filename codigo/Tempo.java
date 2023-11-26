
public enum Tempo {
    HORISTA("Horista"),
    MENSALISTA("Mensalista"),
    TURNO("Turno");

    private String tipoCliente;

    Tempo(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String gettipoCliente() {
        return tipoCliente;
    }
}
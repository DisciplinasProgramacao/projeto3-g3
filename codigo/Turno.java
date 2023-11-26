
public enum Turno {
    MANHA("Manhã"),
    TARDE("Tarde"),
    NOITE("Noite");

    private String qualTurno;

    Turno(String qualTurno) {
        this.qualTurno = qualTurno;
    }

    public String getTurno() {
        return qualTurno;
    }
}

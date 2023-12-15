package Enums;

public enum TipoCliente {
  HORISTA("horista", 0.0),
  MENSALISTA("mensalista", 500.0),
  TURNO("turno", 200.0);
  
  public String desc;
  private double valor;
  private Turno turno;

  TipoCliente(String desc, double valor) {
    this.desc = desc;
    this.valor = valor;
  }

  public String getDesc() {
    return desc;
  }

  public double getValor() {
    return valor;
  }

  public void setTurno(Turno turno) {
    this.turno = turno;
  }

  public Turno getTurno() {
    return turno;
  }
}

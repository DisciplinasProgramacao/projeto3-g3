package Enums;

public enum TipoCliente {
  HORISTA("horista", 0.0),
  MENSALISTA("mensalista", 500.0),
  TURNOMANHA("turnomanha", 200.0),
  TURNOTARDE("turnotarde", 200.0),
  TURNONOITE("turnonoite", 200.0);
  
  public String desc;
  private double valor;

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
}

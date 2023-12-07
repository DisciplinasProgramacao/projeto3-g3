package Enums;

public enum TipoCliente {
  TURNO("turno", 200.0),
  HORISTA("horista", 0.0),
  MENSALISTA("mensalista", 500.0),
  INDEFINIDO("indefinido", 0.0);

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

package Enums;

public enum TipoCliente {
  TURNO("turno", 200),
  HORISTA("horista", 0),
  MENSALISTA("mensalista", 500),
  INDEFINIDO("indefinido", 0);

  public String desc;
  private Double valor;

  TipoCliente(String desc, double valor) {
    this.desc = desc;
    this.valor = valor;
  }

  public String getDesc() {
    return desc;
  }

  public Double getValor() {
    return valor;
  }
}

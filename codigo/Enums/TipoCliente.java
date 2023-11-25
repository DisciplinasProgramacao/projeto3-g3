package enums;

public enum TipoCliente {
  TURNO("Turno", 200),
  HORISTA("Horista", 0),
  MENSALISTA("Mensalista", 500);

  private String desc;
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

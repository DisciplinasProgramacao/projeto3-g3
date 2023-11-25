package enums;

public enum Servicos {
  MANOBRISTA("Manobrista", 5.0, 0),
  LAVAGEM("Lavagem", 20.0, 1),
  POLIMENTO("Polimento", 45.0, 2);

  private String servicoDesc;
  private double valor;
  private int tempoMinimo;

  Servicos(String servicoDesc, double valor, int tempoMinimo) {
    this.servicoDesc = servicoDesc;
    this.valor = valor;
    this.tempoMinimo = tempoMinimo;
  }

  public String getServicodeDesc() {
    return servicoDesc;
  }

  public double getValor() {
    return valor;
  }

  public int getTempoMinimo() {
    return tempoMinimo;
  }
}

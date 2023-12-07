package Enums;

import java.time.*;

public enum Turno {
  MANHA("manhã", LocalTime.of(8, 0), LocalTime.of(12, 0)),
  TARDE("tarde", LocalTime.of(12, 1), LocalTime.of(18, 0)),
  NOITE("noite", LocalTime.of(18, 1), LocalTime.of(23, 59));

  private String desc;
  private LocalTime inicioTurno;
  private LocalTime finalTurno;

  Turno(String desc, LocalTime inicioTurno, LocalTime finalTurno) {
    this.desc = desc;
    this.inicioTurno = inicioTurno;
    this.finalTurno = finalTurno;
  }

  public String getDesc() {
    return desc;
  }

  public LocalTime getInicioTurno() {
    return inicioTurno;
  }

  public LocalTime getFinalTurno() {
    return finalTurno;
  }
}
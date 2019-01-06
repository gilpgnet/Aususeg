package net.ramptors.aususeg;

import android.os.Bundle;

public class CtrlIndex extends CtrlNavegacion {
  public static final String URL_SERVICIOS = "https://ususeg.000webhostapp.com/servicios/";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.form_index);
    iniciaNavegacion();
  }
}
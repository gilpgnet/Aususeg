package net.ramptors.aususeg;

import android.content.Intent;
import android.view.View;
import java.util.List;
import net.ramptors.android.Controlador;
import net.ramptors.android.GetRespuesta;
import net.ramptors.android.Util;
import static net.ramptors.android.Util.isNullOrEmpty;
import static net.ramptors.aususeg.CtrlIndex.URL_SERVICIOS;

public class CtrlNavegacion extends Controlador implements GetRespuesta.RecibeRespuesta<RespuestaSesion> {
  private static final GetRespuesta<RespuestaSesion> buscaSesion = new GetRespuesta<RespuestaSesion>();
  private View navInicio;
  private View navClientes;
  private View navInvitados;
  private View navSesion;
  private View navInicia;

  protected void iniciaNavegacion() {
    navInicio = findViewById(R.id.navInicio);
    navClientes = findViewById(R.id.navClientes);
    navInvitados = findViewById(R.id.navInvitados);
    navSesion = findViewById(R.id.navSesion);
    navInicia = findViewById(R.id.navInicia);
    Util.setVisible(navClientes, false);
    Util.setVisible(navInvitados, false);
    Util.setVisible(navSesion, false);
    Util.setVisible(navInicia, false);
    buscaSesion.get(this, URL_SERVICIOS + "sesion_busca.php", RespuestaSesion.class, this);
  }

  public void clicNavInicio(View v) {
    startActivity(new Intent(this, CtrlIndex.class));
  }

  public void clicNavClientes(View v) {
    startActivity(new Intent(this, CtrlClientes.class));
  }

  public void clicNavInvitados(View v) {
    startActivity(new Intent(this, CtrlInvitados.class));
  }

  public void clicNavSesion(View v) {
    startActivity(new Intent(this, CtrlSesion.class));
  }

  public void clicNavInicia(View v) {
    startActivity(new Intent(this, CtrlInicia.class));
  }

  @Override
  public void recibe(RespuestaSesion respuesta) {
    final String cue = respuesta.cue;
    final List<String> roles = respuesta.roles;
    Util.setVisible(navClientes, roles.contains("Cliente"));
    Util.setVisible(navInvitados, roles.contains("Invitado"));
    Util.setVisible(navSesion, !isNullOrEmpty(cue));
    Util.setVisible(navInicia, isNullOrEmpty(cue));
  }

  @Override
  public void error(Exception e) {
    muestraError("Error recuperando sesión.", e);
  }

  @Override
  protected void onDestroy() {
    buscaSesion.setControlador(null);
    super.onDestroy();
  }
}
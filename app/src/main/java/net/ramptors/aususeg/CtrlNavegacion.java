package net.ramptors.aususeg;

import ner.ramptors.android.Controlador;
import android.view.View;
import net.ramptors.android.GetRespuesta;
import java.util.Set;
import static net.ramptors.android.Util.isNullOrEmpty;
import static net.ramptors.android.Util.setVisible;

public class CtrlNavegacion extends Controlador
    implements  GetRespuesta.RecibeRespuesta<RespuestaSesion> {
  private static final GetRespuesta<RespuestaSesion> buscaSesion = new GetRespuesta<RespuestaSesion>();
  private View navInicio;
  private View navClientes;
  private View navInvitados;
  private View navSesion;
  private View navInicia;

  protected void iniciaNavegacion() {
    navInicio = activity.findViewById(R.id.navInicio);
    navClientes = activity.findViewById(R.id.navClientes);
    navInvitados = activity.findViewById(R.id.navInvitados);
    navSesion = activity.findViewById(R.id.navSesion);
    navInicia = activity.findViewById(R.id.navUsuarios);
    setVisible(navClientes, false);
    setVisible(navInvitados, false);
    setVisible(navSesion, false);
    setVisible(navInicia, false);
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
    final Set<String> roles = respuesta.roles;
    setVisible(navClientes, roles.contains("Cliente"));
    setVisible(navInvitados, roles.contains("Invitado"));
    setVisible(navSesion, !isNullOrEmpty(cue));
    setVisible(navInicia, isNullOrEmpty(cue));
  }
  @Override
  protected void onDestroy() {
    buscaSesion.setControlador(null);
    super.onDestroy();
  }
}
package net.ramptors.aususeg;

import android.content.Intent;
import android.os.Bundle;
import net.ramptors.android.GetRespuesta;
import net.ramptors.android.Respuesta;
import static net.ramptors.aususeg.CtrlIndex.URL_SERVICIOS;

public class CtrlInvitados extends CtrlNavegacion {
  private static final GetRespuesta<Respuesta> invitadosVerifica = new GetRespuesta<Respuesta>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.form_clientes);
    iniciaNavegacion();
    invitadosVerifica.get(this, URL_SERVICIOS + "clientes_verifica.php", Respuesta.class,
        new GetRespuesta.RecibeRespuesta<Respuesta>() {
          @Override
          public void recibe(Respuesta respuesta) {
          }

          @Override
          public void error(Exception error) {
            muestraError("Error verificando invitado.", error);
            startActivity(new Intent(CtrlInvitados.this, CtrlIndex.class));
          }
        });
  }

  @Override
  protected void onDestroy() {
    invitadosVerifica.setControlador(null);
    super.onDestroy();
  }
}
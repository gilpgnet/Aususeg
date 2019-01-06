package net.ramptors.aususeg;

import android.os.Bundle;
import net.ramptors.android.GetRespuesta;
import net.ramptors.android.Respuesta;
import static net.ramptors.aususeg.CtrlIndex.URL_SERVICIOS;

public class CtrlClientes extends CtrlNavegacion {
  private static final GetRespuesta<Respuesta> clientesVerifica = new GetRespuesta<Respuesta>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.form_clientes);
    iniciaNavegacion();
    clientesVerifica.get(this, URL_SERVICIOS + "clientes_verifica.php", Respuesta.class,
        new GetRespuesta.RecibeRespuesta<Respuesta>() {
          @Override
          public void recibe(Respuesta respuesta) {
          }

          @Override
          public void error(Exception error) {
            muestraError("Error verificando cliente.", error);
            startActivity(new Intent(this, CtrlIndex.class));
          }
        });
  }

  @Override
  protected void onDestroy() {
    clientesVerifica.setControlador(null);
    super.onDestroy();
  }
}
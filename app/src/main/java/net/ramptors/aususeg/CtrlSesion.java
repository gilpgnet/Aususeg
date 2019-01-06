package net.ramptors.aususeg;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import net.ramptors.android.Respuesta;
import net.ramptors.android.PostForma;
import static net.ramptors.android.Util.isNullOrEmpty;
import static net.ramptors.android.Util.texto;
import static net.ramptors.aususeg.CtrlIndex.URL_SERVICIOS;

public class CtrlSesion extends CtrlNavegacion implements PostForma.Publicado<Respuesta> {
  private final PostForma<Respuesta> sesionTermina = new PostForma<Respuesta>();
  private TextView cue;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.form_sesion);
    cue = findViewById(R.id.cue);
    iniciaNavegacion();
    sesionTermina.continua(this, this);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_sesion, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
    case R.id.action_terminar_sesion:
      sesionTermina.post(this, URL_SERVICIOS + "sesion_termina.php", Respuesta.class, this);
      return true;
    default:
      return super.onOptionsItemSelected(item);
    }
  }

  @Override
  public void recibe(RespuestaSesion respuesta) {
    super.recibe(respuesta);
    if (isNullOrEmpty(respuesta.cue)) {
      muestraMensaje(R.string.no_has_iniciado_sesion);
      startActivity(new Intent(this, CtrlIndex.class));
    } else {
      cue.setText(texto(respuesta.cue));
    }
  }

  @Override
  public void publicado(Respuesta respuesta) {
    startActivity(new Intent(this, CtrlIndex.class));
  }
}
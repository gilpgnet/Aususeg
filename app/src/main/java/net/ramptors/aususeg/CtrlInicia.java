package net.ramptors.aususeg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import net.ramptors.android.FormData;
import net.ramptors.android.Respuesta;
import net.ramptors.android.PostForma;
import static net.ramptors.android.Util.isNullOrEmpty;
import static net.ramptors.android.Util.texto;
import static net.ramptors.aususeg.CtrlIndex.URL_SERVICIOS;

public class CtrlInicia extends CtrlNavegacion implements PostForma.Publicado<Respuesta> {
  private final PostForma<Respuesta> sesionInicia = new PostForma<Respuesta>();
  private EditText cue;
  private EditText match;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.form_inicia);
    cue = findViewById(R.id.cue);
    match = findViewById(R.id.match);
    iniciaNavegacion();
    sesionInicia.continua(this, this);
  }

  public void iniciaSesion(View v) {
    sesionInicia.post(this, URL_SERVICIOS + "sesion_inicia.php", Respuesta.class, this);
  }

  @Override
  public void recibe(RespuestaSesion respuesta) {
    super.recibe(respuesta);
    if (!isNullOrEmpty(respuesta.cue)) {
      muestraMensaje(R.string.ya_iniciaste_sesion);
      startActivity(new Intent(this, CtrlIndex.class));
    }
  }

  @Override
  protected void llenaFormData(FormData formData) {
    formData.append("cue", cue.getText().toString().trim());
    formData.append("match", match.getText().toString().trim());
  }

  @Override
  public void publicado(Respuesta respuesta) {
    startActivity(new Intent(this, CtrlIndex.class));
  }
}
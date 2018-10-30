package edu.bajio.appdiario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main3ActivityDia extends AppCompatActivity {

    Button btnAgregar;
    Bundle datos;
    String username;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3_dia);
        datos = getIntent().getExtras();
        username = datos.getString("usuario");

        btnAgregar = (Button) findViewById(R.id.btnDia);
        btnLogout = (Button) findViewById(R.id.btnLogout);



        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenta = new Intent (Main3ActivityDia.this, Main4ActivityRegDia.class);
                intenta.putExtra("usuario",username);
                startActivity(intenta);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenta = new Intent (Main3ActivityDia.this, MainActivity.class);
                startActivity(intenta);
            }
        });
    }
}

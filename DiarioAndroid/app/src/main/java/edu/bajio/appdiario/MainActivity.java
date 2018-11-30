package edu.bajio.appdiario;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import edu.bajio.appdiario.Class.Common;
import edu.bajio.appdiario.Class.HTTPDataHandler;
import edu.bajio.appdiario.Class.Usuario;

public class MainActivity extends AppCompatActivity {

    EditText txtUser;
    EditText txtPass;
    Button btnAceptar;
    Button btnRegistrar;

    Usuario usuario = null;

    List<Usuario> usuarios = new ArrayList<Usuario>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUser = (EditText) findViewById(R.id.txtUserL);
        txtPass = (EditText) findViewById(R.id.txtPwdL);
        btnAceptar = (Button) findViewById(R.id.btnAceptarL);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrarL);

        new GetData().execute(Common.getAddressAPI());

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtU = txtUser.getText().toString();
                String txtPa = txtPass.getText().toString();
                int ban = 0;
                if(txtU.equals("") || txtPa.equals(""))
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Do not leave empty spaces", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    for (int i = 0; i < usuarios.size(); i++) {
                        usuario = usuarios.get(i);
                        if ((txtU.equals(usuario.getUsuario())) && (txtPa.equals(usuario.getPassword()))) {
                            txtUser.setText("");
                            txtPass.setText("");
                            ban = 1;
                            Intent intent = new Intent(MainActivity.this, Main3ActivityDia.class);
                            intent.putExtra("usuario", usuario.getUsuario());
                            startActivity(intent);
                            break;
                        }
                    }
                    if (ban == 0)
                    {
                        Toast toast = Toast.makeText(getApplicationContext(), "wrong username or password", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                }
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, Main2ActivityReg.class);

                startActivity(intent);
            }
        });

    }

    class GetData extends AsyncTask<String,Void,String>{

        ProgressDialog pd = new ProgressDialog(MainActivity.this);


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //pd.setTitle("Please wait...");
            //pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String stream = null;
            String urlString = strings[0];

            HTTPDataHandler http = new HTTPDataHandler();
            stream = http.GetHTTPData(urlString);
            return stream;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Gson gson = new Gson();
            Type listType = new TypeToken<List<Usuario>>(){}.getType();
            usuarios = gson.fromJson(s,listType);
            pd.dismiss();
        }
    }
}

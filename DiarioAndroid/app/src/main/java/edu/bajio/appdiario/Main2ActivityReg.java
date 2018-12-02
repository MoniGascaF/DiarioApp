package edu.bajio.appdiario;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import edu.bajio.appdiario.Class.Common;
import edu.bajio.appdiario.Class.HTTPDataHandler;

public class Main2ActivityReg extends AppCompatActivity {


    Button btnRegresar;
    Button btnCreate;
    EditText txtUsuario;
    EditText txtPassword1;
    EditText txtPassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_reg);
        btnRegresar = (Button) findViewById(R.id.btnRegresar);
        btnCreate = (Button) findViewById(R.id.btnAceptarR);
        txtUsuario = (EditText) findViewById(R.id.txtUserR);
        txtPassword1 = (EditText)findViewById(R.id.txtPwdR);
        txtPassword2 = (EditText) findViewById(R.id.txtPwd2R);

        this.setTitle("Registro de Usuario");

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenta = new Intent (Main2ActivityReg.this, MainActivity.class);
                startActivity(intenta);
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = txtUsuario.getText().toString();
                String pass1 = txtPassword1.getText().toString();
                String pass2 = txtPassword2.getText().toString();
                if(user.equals("")||pass1.equals("")||pass2.equals(""))
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Do not leave empty spaces", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                {
                    if(pass1.equals(pass2))
                    {
                        new PostUsuario().execute(Common.getAddressAPI());
                    }
                    else
                    {
                        Toast toast = Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });
    }

    class PostUsuario extends AsyncTask<String,Void,String> {
        ProgressDialog pdia = new ProgressDialog(Main2ActivityReg.this);


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pdia.setTitle("Please wait...");
            pdia.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String urlString = params[0];

            HTTPDataHandler hh = new HTTPDataHandler();

            JSONObject json = new JSONObject();

            try {
                json.put("usuario", txtUsuario.getText().toString());
                json.put("password", txtPassword1.getText().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }


            hh.PostHTTPData(urlString, json);
            return "";

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            txtPassword1.setText("");
            txtPassword2.setText("");
            txtUsuario.setText("");
            pdia.dismiss();

            Toast toast = Toast.makeText(getApplicationContext(), "Registered user", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}

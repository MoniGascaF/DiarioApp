package edu.bajio.appdiario;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import edu.bajio.appdiario.Class.Common;
import edu.bajio.appdiario.Class.CommonDias;
import edu.bajio.appdiario.Class.Dia;
import edu.bajio.appdiario.Class.HTTPDataHandler;
import edu.bajio.appdiario.Class.Usuario;

public class Main3ActivityDia extends AppCompatActivity {

    Button btnAgregar;
    Bundle datos;
    String username;
    Button btnLogout;
    ListView lstDia;

    List<String> values = new ArrayList<String>();

    List<Dia> dias = new ArrayList<Dia>();
    List<Dia> diasU = new ArrayList<Dia>();
    Dia dia = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3_dia);
        datos = getIntent().getExtras();
        username = datos.getString("usuario");
        lstDia = (ListView) findViewById(R.id.lstDia);

        btnAgregar = (Button) findViewById(R.id.btnDia);
        btnLogout = (Button) findViewById(R.id.btnLogout);

        new GetData().execute(CommonDias.getAddressAPI());


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

    class GetData extends AsyncTask<String,Void,String> {

        ProgressDialog pd = new ProgressDialog(Main3ActivityDia.this);


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pd.setTitle("Please wait...");
            pd.show();
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
            Type listType = new TypeToken<List<Dia>>(){}.getType();
            dias = gson.fromJson(s,listType);
            pd.dismiss();

            for(int i = 0; i<dias.size();i++)
            {
                dia = dias.get(i);
                String user = dia.getUsuario();
                if(user.equals(username))
                {
                    diasU.add(dia);
                    values.add(dia.getTitulo());

                }
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),R.layout.simple_list_item_1,values);

            lstDia.setAdapter(adapter);
        }
    }
}

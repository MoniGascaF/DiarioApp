package edu.bajio.appdiario;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.renderscript.Allocation;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.DocumentEmotionResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EmotionOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EmotionResult;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EmotionScores;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesResult;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.KeywordsOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.KeywordsResult;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import edu.bajio.appdiario.Class.Canciones;
import edu.bajio.appdiario.Class.Common;
import edu.bajio.appdiario.Class.CommonCanciones;
import edu.bajio.appdiario.Class.CommonDias;
import edu.bajio.appdiario.Class.CommonImagenes;
import edu.bajio.appdiario.Class.CommonPeliculas;
import edu.bajio.appdiario.Class.HTTPDataHandler;
import edu.bajio.appdiario.Class.Imagenes;
import edu.bajio.appdiario.Class.Peliculas;
import edu.bajio.appdiario.Class.Usuario;

public class Main4ActivityRegDia extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    ImageView ima;
    EditText txtDescripcion;
    Button btnanalizar;
    EditText txtTitulo;

    YouTubePlayerView YTPlayer;

    String emocion;

    String ClaveYoutube = "AIzaSyBcaSL17tKOmwvDY7oVqeaXntyZFt3zNLk";

    Canciones cancion = null;
    Peliculas pelicula = null;
    Imagenes imagen = new Imagenes();

    String Link;


    String nombre;

    int tipo;

    List<Canciones> canciones = new ArrayList<Canciones>();
    List<Peliculas> peliculas = new ArrayList<Peliculas>();
    List<Imagenes> imagenes = new ArrayList<Imagenes>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4_reg_dia);

        txtDescripcion = (EditText) findViewById(R.id.txtDescripcion);
        ima = (ImageView) findViewById(R.id.ImageV);
        btnanalizar = (Button) findViewById(R.id.btnAceptarDia);
        YTPlayer = (YouTubePlayerView) findViewById(R.id.YTVideo);
        txtTitulo = (EditText) findViewById(R.id.txtTitulo);




        btnanalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String desc = txtDescripcion.getText().toString();
                String titu = txtTitulo.getText().toString();
                new PostDia().execute(CommonDias.getAddressAPI());

                /*if(desc.equals("") || titu.equals(""))
                {
                    Toast.makeText(getApplication(),"No dejes campos vacios", Toast.LENGTH_LONG).show();
                }
                else {

                    AskWatsonTask task = new AskWatsonTask();

                    task.execute(new String[]{});
                }*/
            }
        });

    }


    private void ElegirInfo()
    {
        tipo = (int) (Math.random()*3)+1;

        if(tipo == 1)
        {

            new GetDataCanciones().execute(CommonCanciones.getAddressAPI());

        }
        else if(tipo == 2)
        {
            new GetDataPeliculas().execute(CommonPeliculas.getAddressAPI());
        }
        else if (tipo == 3)
        {
            new GetDataImagenes().execute(CommonImagenes.getAddressAPI());
        }
    }


    class GetDataImagenes extends AsyncTask<String,Void,String> {

        ProgressDialog pdiq = new ProgressDialog(Main4ActivityRegDia.this);


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pdiq.setTitle("Please wait...");
            pdiq.show();
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
            Type listType = new TypeToken<List<Imagenes>>(){}.getType();
            imagenes = gson.fromJson(s,listType);
            pdiq.dismiss();
            ElegirImagen();

        }
    }

    class GetDataCanciones extends AsyncTask<String,Void,String> {

        ProgressDialog pdi = new ProgressDialog(Main4ActivityRegDia.this);


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pdi.setTitle("Please wait...");
            pdi.show();
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
            Type listType = new TypeToken<List<Canciones>>(){}.getType();
            canciones = gson.fromJson(s,listType);
            pdi.dismiss();
            ElegirCancion();

        }
    }

    class GetDataPeliculas extends AsyncTask<String,Void,String> {

        ProgressDialog pdia = new ProgressDialog(Main4ActivityRegDia.this);


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pdia.setTitle("Please wait...");
            pdia.show();
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
            Type listType = new TypeToken<List<Peliculas>>(){}.getType();
            peliculas = gson.fromJson(s,listType);
            pdia.dismiss();
            ElegirPelicula();

        }
    }

    class PostDia extends AsyncTask<String,Void,String>
    {
        ProgressDialog pdia = new ProgressDialog(Main4ActivityRegDia.this);


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pdia.setTitle("Please wait...");
            pdia.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String urlString =  params[0];

            HTTPDataHandler hh = new HTTPDataHandler();
            String json = "(\"titulo\":\"" + "H" + "\")";
                    //"(\"descripcion\":\"" + txtDescripcion.getText().toString() + "\")," +
                    //"(\"emocion\":\"" + emocion + "\")," +
                    //"(\"tipo\":\"" + tipo + "\")," +
                    //"(\"nombre\":\"" + nombre + "\")";

            hh.PostHTTPData(urlString,json);
            return "";

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            pdia.dismiss();


        }

    }

    private void ElegirCancion()
    {
        List<Canciones> ca = new ArrayList<Canciones>();
        for(int i = 0; i < canciones.size();i++)
        {
            cancion = canciones.get(i);
            if(cancion.getEmocion().equals(emocion))
            {
                ca.add(cancion);
            }
        }


        int numero = (int) (Math.random() * ca.size());
        cancion = ca.get(numero);
        Link = cancion.getLink();

        nombre = Link;

        YTPlayer.setVisibility(View.VISIBLE);

        YTPlayer.initialize(ClaveYoutube,this);
        new PostDia().execute(CommonDias.getAddressAPI());

    }

    private void ElegirPelicula()
    {
        List<Peliculas> pe = new ArrayList<Peliculas>();
        for(int i = 0; i < peliculas.size();i++)
        {
            pelicula = peliculas.get(i);
            if(pelicula.getEmocion().equals(emocion))
            {
                pe.add(pelicula);
            }
        }
        int numero = (int) (Math.random() * pe.size());
        pelicula = pe.get(numero);
        Link = pelicula.getLink();

        nombre = Link;

        YTPlayer.setVisibility(View.VISIBLE);

        YTPlayer.initialize(ClaveYoutube,this);
        new PostDia().execute(CommonDias.getAddressAPI());

    }

    private void ElegirImagen()
    {
        List<Imagenes> im = new ArrayList<Imagenes>();
        for (int i = 0;i < imagenes.size(); i++) {
            imagen = imagenes.get(i);
            if (imagen.getEmocion().equals(emocion)) {
                im.add(imagen);
            }
        }
            int numero = (int) (Math.random() * im.size());
            imagen = im.get(numero);

            String imag = imagen.getNombre();
            nombre = imag;
            Context c = getApplicationContext();
            int id = c.getResources().getIdentifier("mipmap/"+imag,null, c.getPackageName());

            ima.setVisibility(View.VISIBLE);
            ima.setImageResource(id);
            new PostDia().execute(CommonDias.getAddressAPI());

    }

    class AskWatsonTask extends AsyncTask<String, Void,String>
    {
        ProgressDialog pdig = new ProgressDialog(Main4ActivityRegDia.this);
        @Override
        protected String doInBackground(String... textToAnalyse)
        {
            System.out.println(txtDescripcion.getText());

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    pdig.setTitle("Analizando datos");
                    pdig.show();
                }
            });

            NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding("2017-11-07");
            service.setUsernameAndPassword("d186736a-b226-46c9-94a3-f4058519344b","FYE8TUaDZ0DX");


            EntitiesOptions entities = new EntitiesOptions.Builder()
                    .sentiment(true)
                    .emotion(true)
                    .limit(2)
                    .build();
            KeywordsOptions keywordsOptions = new KeywordsOptions.Builder()
                    .emotion(true)
                    .sentiment(true)
                    .limit(2)
                    .build();
            EmotionOptions emotionOptions = new EmotionOptions.Builder()
                    .document(true)
                    .build();
            Features features = new Features.Builder()
                    .entities(entities)
                    .keywords(keywordsOptions)
                    .emotion(emotionOptions)
                    .build();

            AnalyzeOptions parameters = new AnalyzeOptions.Builder()
                    .text(txtDescripcion.getText().toString())
                    .features(features)
                    .language("en")
                    .limitTextCharacters(500)
                    .returnAnalyzedText(true)
                    .fallbackToRaw(true)
                    .build();
            AnalysisResults results = service.analyze(parameters).execute();
            EmotionResult emotion1 = results.getEmotion();
            DocumentEmotionResults document = emotion1.getDocument();
            EmotionScores emotion = document.getEmotion();

            Double Joy = emotion.getJoy();
            Double Sad = emotion.getSadness();
            Double Anger = emotion.getAnger();

            //System.out.println(results);

            if (Joy>Sad && Joy>Anger)
            {
                emocion = "1";
            }
            else if (Anger > Joy && Anger > Sad)
            {
                emocion = "2";
            }
            else if (Sad > Joy && Sad > Anger)
            {
                emocion = "3";
            }
            else {
                emocion = "0";
            }
            return emocion;
        }

        @Override
        protected void onPostExecute(String s) {
            ElegirInfo();
            pdig.dismiss();
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if(!b)
        {
            youTubePlayer.cueVideo(Link);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if(youTubeInitializationResult.isUserRecoverableError())
        {
            youTubeInitializationResult.getErrorDialog(this,1).show();
        }
        else
        {
            String error = "Error al inicializar Youtube " +youTubeInitializationResult.toString();

            Toast.makeText(getApplication(),error, Toast.LENGTH_LONG).show();
        }
    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data)
    {
        if(resultCode == 1)
        {
            getYoutubePlayerProvider().initialize(ClaveYoutube, this);
        }
    }

    protected  YouTubePlayer.Provider getYoutubePlayerProvider()
    {
        return YTPlayer;
    }
}

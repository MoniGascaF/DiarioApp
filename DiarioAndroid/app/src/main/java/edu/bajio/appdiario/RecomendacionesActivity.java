package edu.bajio.appdiario;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.io.Console;

import edu.bajio.appdiario.Class.Recomendacion;

public class RecomendacionesActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    YouTubePlayerView YTPlayer;
    ImageView ima;
    String ClaveYoutube = "AIzaSyBcaSL17tKOmwvDY7oVqeaXntyZFt3zNLk";
    Bundle datos;
    String link;

    Recomendacion rec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendaciones);
        rec = (Recomendacion) getIntent().getSerializableExtra("reco");

        ima = (ImageView) findViewById(R.id.ImageVD);
        YTPlayer =  (YouTubePlayerView) findViewById(R.id.YTVideoD);

        this.setTitle("Recomendacion");

        if(rec.getTipo().equals("imagen"))
        {
            Context c = getApplicationContext();
            int id = c.getResources().getIdentifier("drawable/"+rec.getLink(),null, c.getPackageName());

            ima.setVisibility(View.VISIBLE);
            ima.setImageResource(id);
        }
        else
        {
            link = rec.getLink();
            YTPlayer.setVisibility(View.VISIBLE);
            YTPlayer.initialize(ClaveYoutube,this);
        }
    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if(!b)
        {
            youTubePlayer.cueVideo(link);
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

package edu.bajio.appdiario.Viewholders;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import java.io.Serializable;

import edu.bajio.appdiario.Class.Recomendacion;
import edu.bajio.appdiario.R;
import edu.bajio.appdiario.RecomendacionesActivity;

public class descripcionViewHolder extends ChildViewHolder {

    private TextView txtDescripcion;
    String Link;
    String opc;
    private Button btnRecomendacion;

    String ClaveYoutube = "AIzaSyBcaSL17tKOmwvDY7oVqeaXntyZFt3zNLk";

    public descripcionViewHolder(final View itemView) {
        super(itemView);
        txtDescripcion = (TextView) itemView.findViewById(R.id.tvaDescripcion);
        btnRecomendacion = (Button) itemView.findViewById(R.id.btnRecomendacion);

        btnRecomendacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Recomendacion rec = new Recomendacion();
                rec.setLink(Link);
                rec.setTipo(opc);
                Intent intent = new Intent(itemView.getContext(), RecomendacionesActivity.class);
                intent.putExtra("reco",rec);
                itemView.getContext().startActivity(intent);
            }
        });

    }

    public void setDescriocionName(String name)
    {
        txtDescripcion.setText(name);
    }

    public void setVideo(String link)
    {
        this.Link = link;
        opc = "video";

    }

    public void setImagen(String imag)
    {
        this.Link = imag;
        opc = "imagen";
    }



}



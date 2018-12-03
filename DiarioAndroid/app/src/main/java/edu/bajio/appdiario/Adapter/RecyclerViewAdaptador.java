package edu.bajio.appdiario.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import edu.bajio.appdiario.Class.Dia;
import edu.bajio.appdiario.R;

public class RecyclerViewAdaptador extends RecyclerView.Adapter<RecyclerViewAdaptador.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView titulo;
        private ImageView img;
        private Context ca;

        public ViewHolder(View itemView) {
            super(itemView);
            titulo = (TextView)itemView.findViewById(R.id.tvTitulo);
            img = (ImageView)itemView.findViewById(R.id.imgGps);
            ca = itemView.getContext();
        }
    }

    public List<Dia> marcadorLista;

    public RecyclerViewAdaptador(List<Dia> marcadorLista) {
        this.marcadorLista = marcadorLista;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.marcador_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Dia dia = new Dia();
        dia = marcadorLista.get(position);
        holder.titulo.setText(dia.getTitulo());
        holder.img.setImageResource(R.mipmap.emotion);


        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        holder.titulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return marcadorLista.size();
    }
}

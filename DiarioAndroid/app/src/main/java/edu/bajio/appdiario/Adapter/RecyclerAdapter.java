package edu.bajio.appdiario.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.LatLng;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import edu.bajio.appdiario.Model.Descripcion;
import edu.bajio.appdiario.R;
import edu.bajio.appdiario.Viewholders.TitleViewHolder;
import edu.bajio.appdiario.Viewholders.descripcionViewHolder;

public class RecyclerAdapter extends ExpandableRecyclerViewAdapter<TitleViewHolder, descripcionViewHolder> {

    public RecyclerAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public TitleViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.marcador_view,parent,false);
        return new TitleViewHolder(view);
    }

    @Override
    public descripcionViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_child,parent,false);
        return new descripcionViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(descripcionViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        Descripcion descripcion = (Descripcion) group.getItems().get(childIndex);

        holder.setDescriocionName(descripcion.getTitulo());
        String ti = descripcion.getTipo();

        if(descripcion.getTipo().equals("3"))
        {
            holder.setImagen(descripcion.getVim());
        }
        else {
            holder.setVideo(descripcion.getVim());
        }
    }

    @Override
    public void onBindGroupViewHolder(TitleViewHolder holder, int flatPosition, ExpandableGroup group) {
        Descripcion des =(Descripcion) group.getItems().get(0);
        String emo = des.getEmocion();
        holder.setTitleName(group.getTitle(),emo);
    }
}

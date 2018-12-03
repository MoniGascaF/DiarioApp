package edu.bajio.appdiario.Viewholders;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import edu.bajio.appdiario.R;

public class TitleViewHolder extends GroupViewHolder {

    private TextView title;
    private ImageView imgGps;


    public TitleViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.tvTitulo);
        imgGps = (ImageView) itemView.findViewById(R.id.imgGps);
    }

    public void setTitleName(String name, String emocion)
    {
        title.setText(name);
        Context c = itemView.getContext();
        int id = 1;
        if(emocion.equals("1")) {
            id = c.getResources().getIdentifier("drawable/happy2", null, c.getPackageName());
        }
        else if (emocion.equals("2"))
        {
            id = c.getResources().getIdentifier("drawable/angry", null, c.getPackageName());
        }
        else if(emocion.equals("3"))
        {
            id = c.getResources().getIdentifier("drawable/sad", null, c.getPackageName());
        }

        //id = c.getResources().getIdentifier("mipmap/emotion", null, c.getPackageName());
        imgGps.setVisibility(View.VISIBLE);
        imgGps.setImageResource(id);
    }
}

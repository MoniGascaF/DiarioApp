package edu.bajio.appdiario.Model;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class TitleDia extends ExpandableGroup {

    public TitleDia(String title,String emocion, List items) {
        super(title, items);
    }
}
package edu.bajio.appdiario.Class;

import java.io.Serializable;

public class Recomendacion implements Serializable {
    String tipo;
    String link;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

package edu.bajio.appdiario.Model;


public class Descripcion {
    private String titulo;
    private String vim;
    private String tipo;
    private String emocion;

    public Descripcion(String titulo, String vim, String tipo, String emocion)
    {
        this.titulo = titulo;
        this.vim = vim;
        this.tipo = tipo;
        this.emocion = emocion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getVim() {
        return vim;
    }

    public void setVim(String vim) {
        this.vim = vim;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEmocion() {
        return emocion;
    }

    public void setEmocion(String emocion) {
        this.emocion = emocion;
    }
}

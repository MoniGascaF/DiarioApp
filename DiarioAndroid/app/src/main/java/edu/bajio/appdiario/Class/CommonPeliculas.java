package edu.bajio.appdiario.Class;

public class CommonPeliculas {

    private static String DB_Name = "diario_emociones";
    private static String Collection = "Peliculas";
    public static String API_KEY = "RDLbQ83bHGU0_fFwYeJu5gaV51Fl7yvs";


    public static String getAddressSingle(Usuario user)
    {
        String baseUrl = String.format("https://api.mlab.com/api/1/databases/%s/collections/%s",DB_Name,Collection);
        StringBuilder stringBuilder = new StringBuilder(baseUrl);

        stringBuilder.append("/"+user.get_id().getOid()+"?apiKey="+API_KEY);

        return stringBuilder.toString();
    }

    public static String getAddressAPI(){
        String baseUrl = String.format("https://api.mlab.com/api/1/databases/%s/collections/%s",DB_Name,Collection);
        StringBuilder stringBuilder = new StringBuilder(baseUrl);

        stringBuilder.append("?apiKey="+API_KEY);

        return stringBuilder.toString();
    }
}

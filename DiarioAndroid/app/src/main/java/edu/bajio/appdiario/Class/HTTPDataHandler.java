package edu.bajio.appdiario.Class;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HTTPDataHandler
{
    static String stream = null;

    public HTTPDataHandler()
    {

    }

    public String GetHTTPData(String urlString)
    {
        try
        {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();

            if(urlConnection.getResponseCode() == 200)
            {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                BufferedReader r = new BufferedReader(new InputStreamReader(in));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line=r.readLine()) != null)
                {
                    sb.append(line);
                }
                stream = sb.toString();
                urlConnection.disconnect();
            }
            else
            {

            }
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return stream;
    }

    public void PostHTTPData(String urlString, JSONObject json)
    {
        try
        {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();

            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);


            //int length = out.length;

            byte[] out = json.toString().getBytes();


            //urlConnection.setFixedLengthStreamingMode(length);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            //urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.connect();;
            try (OutputStream os = urlConnection.getOutputStream())
            {
                os.write(out);
                os.flush();
                os.close();
            }

            int responseCode = urlConnection.getResponseCode();

            int a = 0;
            //InputStream response = urlConnection.getInputStream();
            //return  response.toString();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
            //return "";
        } catch (ProtocolException e) {
            e.printStackTrace();
            //return "";
        } catch (IOException e) {
             e.printStackTrace();
            //return "";
        }
    }

    /*public void PostHTTPData(String urlString, String json)
    {
        try
        {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();

            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);

            byte[] out = json.getBytes(StandardCharsets.UTF_8);
            int length = out.length;

            urlConnection.setFixedLengthStreamingMode(length);
            urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.connect();;
            try (OutputStream os = urlConnection.getOutputStream())
            {
                os.write(out);
            }
            //InputStream response = urlConnection.getInputStream();
            //return  response.toString();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
            //return "";
        } catch (ProtocolException e) {
            e.printStackTrace();
            //return "";
        } catch (IOException e) {
            e.printStackTrace();
            //return "";
        }
    }*/

    public void PutHTTPData(String urlString, String newValue)
    {
        try
        {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();

            urlConnection.setRequestMethod("PUT");
            urlConnection.setDoOutput(true);

            byte[] out = newValue.getBytes(StandardCharsets.UTF_8);
            int length = out.length;

            urlConnection.setFixedLengthStreamingMode(length);
            urlConnection.setRequestProperty("Content-Type","application/json; charset-UTF-8");
            urlConnection.connect();
            try (OutputStream os = urlConnection.getOutputStream())
            {
                os.write(out);
            }
            InputStream response = urlConnection.getInputStream();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void DeleteHTTPData(String urlString, String json)
    {
        try
        {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();

            urlConnection.setRequestMethod("DELETE");
            urlConnection.setDoOutput(true);

            byte[] out = json.getBytes(StandardCharsets.UTF_8);
            int length = out.length;

            urlConnection.setFixedLengthStreamingMode(length);
            urlConnection.setRequestProperty("Content-Type","application/json; charset-UTF-8");
            urlConnection.connect();
            try (OutputStream os = urlConnection.getOutputStream())
            {
                os.write(out);
            }
            InputStream response = urlConnection.getInputStream();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

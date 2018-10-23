
package com.jagrosh.jmusicbot.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class OtherUtil
{
    public static InputStream imageFromUrl(String url)
    {
        if(url==null)
            return null;
        try {
            URL u = new URL(url);
            URLConnection urlConnection = u.openConnection();
            urlConnection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36");
            return urlConnection.getInputStream();
        } catch(IOException|IllegalArgumentException e) {
        }
        return null;
    }
    
    public static String getLatestVersion()
    {
        try
        {
            Response response = new OkHttpClient.Builder().build()
                    .newCall(new Request.Builder().get().url("https://api.github.com/repos/jagrosh/MusicBot/releases/latest").build())
                    .execute();
            try(Reader reader = response.body().charStream())
            {
                JSONObject obj = new JSONObject(new JSONTokener(reader));
                return obj.getString("tag_name");
            }
            finally
            {
                response.close();
            }
        }
        catch(IOException | JSONException | NullPointerException ex)
        {
            return null;
        }
    }
}

package com.example.choresclient;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

// The following code is taken from
// https://stackoverflow.com/questions/19229204/android-waiting-for-response-from-server
// and https://stackoverflow.com/questions/8654876/http-get-using-android-httpurlconnection
// and https://stackoverflow.com/questions/9767952/how-to-add-parameters-to-httpurlconnection-using-post-using-namevaluepair

public class CustomAsyncTask extends AsyncTask<String, String, String> {
    private final CustomEventListener callback;
    String server_response;

    public CustomAsyncTask(CustomEventListener cb) {
        callback = cb;
    }

    @Override
    protected String doInBackground(String... strings) {
        URL url;
        HttpURLConnection urlConnection;

        try {
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection();

            // For POST requests
            // TODO: Need to add support for adding arguments to GET requests
            if (strings.length > 1) {
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(15000);

                String requestMethod = strings[1];
                urlConnection.setRequestMethod(requestMethod);
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                List<AbstractMap.SimpleEntry<String, String>> params = new ArrayList<>();
                int stringsPos = 2;
                while (stringsPos < strings.length) {
                    params.add(new AbstractMap.SimpleEntry<String, String>(strings[stringsPos], strings[stringsPos + 1]));
                    stringsPos += 2;
                }

                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getQuery(params));
                writer.flush();
                writer.close();
                os.close();
            }

            urlConnection.connect();

            int responseCode = urlConnection.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){
                server_response = readStream(urlConnection.getInputStream());
                Log.v("CatalogClient", server_response);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if(callback != null) {
            try {
                callback.onEventCompleted(server_response);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }

    private String getQuery(List<AbstractMap.SimpleEntry<String, String>> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (AbstractMap.SimpleEntry<String, String> pair : params)
        {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}

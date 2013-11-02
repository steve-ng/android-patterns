package com.example.pattern.proxyCache;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.io.InputStream;

public class LongRunningComputation extends AsyncTask<Integer, Void, String>  {

    long beforeTime;
    long afterTime;
    public MainActivity activity;
    protected int counter;

    public LongRunningComputation(MainActivity a){
        activity = a;
        System.out.println("Constructed!");
    }

    protected String getASCIIContentFromEntity(HttpEntity entity)
            throws IllegalStateException, IOException {
        InputStream in = entity.getContent();
        StringBuffer out = new StringBuffer();
        int n = 1;
        while (n > 0) {
            byte[] b = new byte[4096];
            n = in.read(b);
            if (n > 0)
                out.append(new String(b, 0, n));
        }
        return out.toString();
    }

    protected void onPreExecute() {

        beforeTime = System.currentTimeMillis();

    }

    @Override
    protected String doInBackground(Integer... params) {

        HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();
        HttpGet httpGet = new HttpGet(
                "http://ec2-54-254-140-28.ap-southeast-1.compute.amazonaws.com:8080/FYP-PartnerCloud-war/webresources/rest/"+ params[0]);
        String text = null;
        try {
            HttpResponse response = httpClient.execute(httpGet,
                    localContext);
            HttpEntity entity = response.getEntity();
            text = getASCIIContentFromEntity(entity);
        } catch (Exception e) {
            return e.getLocalizedMessage();
        }
        return text;
    }

    protected void onPostExecute(String results) {

    }

}




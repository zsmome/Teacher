package com.zsmome.fanyiscreen.threads;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2017/5/13.
 */

public class FanYiThread extends Thread {
    @Override
    public void run() {
        try {
            URL url = new URL("http://fanyi.youdao.com/openapi.do");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("encoding", "UTF-8");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");

            OutputStream os = connection.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);

            bw.write("keyfrom=zsmome&key=692648589&type=data&doctype=json&version=1.1&q=Microsoft");
            bw.flush();

            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);

            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }

            bw.close();
            osw.close();
            os.close();

            br.close();
            isr.close();
            is.close();

            Log.i("youdao", builder.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


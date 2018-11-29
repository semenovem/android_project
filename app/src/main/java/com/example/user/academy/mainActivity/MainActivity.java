package com.example.user.academy.mainActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.user.academy.R;
import com.example.user.academy.fullArticle.FullArticleActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
//    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Thread(new NetTransport()).start();
    }

    private class RightLeg implements Runnable {
        private boolean isRunning = true;
        @Override
        public void run() {
            while (isRunning) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Right step");
            }
        }
    }

}

class NetTransport implements Runnable {
    private String myUrl = "http://10.50.14.231:8080/index.json";

    @Override
    public void run() {

        System.out.println("start request");
        request();
        System.out.println("end request");
    }


    private void request() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().get().url(myUrl).build();

        Call call = client.newCall(request);

        try {
            Response response = call.execute();

            String contentAsString = response.body().string();

            System.out.println("ready content");
            System.out.println(contentAsString);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.example.kostyaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import org.xml.sax.SAXException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class MainActivity extends AppCompatActivity {

    private final OkHttpClient client = new OkHttpClient();
    final String TAG = "demo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = MainFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container,fragment,"main_fragment");
        transaction.commit();
    }

    void getAnime(){
        Request request = new Request.Builder()
                .url("http://api.anidb.net:9001/httpapi?request=anime&client=testkostya&clientver=9&protover=1&aid=3")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.d(TAG,"onResponse: " + Thread.currentThread().getId());

                if(response.isSuccessful()){
                    try {
                        AnimeXmlParser animeXmlParser = new AnimeXmlParser();
                        Anime anime = animeXmlParser.parse(response.body().byteStream());

                        Log.d(TAG, "onResponse: " + anime);
                    } catch (SAXException e) {
                        throw new RuntimeException(e);
                    }

                } else {
                    ResponseBody responseBody = response.body();
                    String body = responseBody.string();
                    Log.d(TAG, "onResponse: " + body);
                }
            }
        });
    }

}
package com.example.kostyaproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment implements RecyclerViewAdapter.ItemClickListener {

    private List<Anime> animeList = new ArrayList<>();
    private final OkHttpClient client = new OkHttpClient();
    final String TAG = "demo";
    RecyclerViewAdapter adapter = new RecyclerViewAdapter(animeList,this);


    public MainFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance( ) {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_main, container, false);
        initRecyclerView(view);
        getAnime();
        return view;
    }

    private void initRecyclerView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }


    void testData(){
        animeList.add(new Anime());
    }

    void getAnime(){
        Request request = new Request.Builder()
                .url("http://api.anidb.net:9001/httpapi?request=anime&client=testforbsuir&clientver=9&protover=1&aid=3")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    try {
                        AnimeXmlParser animeXmlParser = new AnimeXmlParser();
                        Anime anime = animeXmlParser.parse(response.body().byteStream());
                        Log.d(TAG, "Anime Type: " + anime.getType());
                        Log.d(TAG, "Anime Episode Count: " + anime.getEpisodecount());
                        Log.d(TAG, "Anime Start Date: " + anime.getStartdate());
                        Log.d(TAG, "Anime End Date: " + anime.getEnddate());
                        anime.setType("anime");
                        Log.d(TAG, "Anime Type: " + anime.getType());
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                animeList.add(anime);
                                adapter.notifyDataSetChanged(); // Обновление RecyclerView
                                Log.d(TAG, "Anime Type: " + animeList.get(0));
                            }
                        });
                    } catch (SAXException e) {
                        throw new RuntimeException(e);
                    }

                } else {
                    ResponseBody responseBody = response.body();
                    String body = responseBody.string();
                }
            }
        });
    }

    @Override
    public void onItemClick(Anime anime) {
        Fragment fragment = DetailFragment.newInstance(anime.getType());

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container,fragment,"detail_fragment");
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
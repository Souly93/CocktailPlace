package com.example.cocktailplace.ui.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktailplace.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Boisson> boissonList = new ArrayList<>();
    private RecyclerView recyclerView;
    private BoissonAdapter bAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = (RecyclerView) root.findViewById();
        bAdapter = new BoissonAdapter(boissonList);

        RecyclerView.LayoutManager bLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(bLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        /*
        final ListView listView = root.findViewById(R.id.ingredient_list);
        final List<String> data = new ArrayList<String>();
        */

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    // URL
                    URL boissonsApi = new URL("https://www.thecocktaildb.com/api/json/v1/1/list.php?i=list");
                    HttpsURLConnection myConnection =
                            (HttpsURLConnection) boissonsApi.openConnection();
                    if (myConnection.getResponseCode() == 200){
                        //Réussite
                        InputStream responseBody = myConnection.getInputStream();
                        InputStreamReader responseBodyReader =
                                new InputStreamReader(responseBody, "UTF-8");
                        JsonReader jsonReader = new JsonReader(responseBodyReader);
                        jsonReader.beginObject();
                        while (jsonReader.hasNext()) {
                            String key = jsonReader.nextName();
                            if(key.equals("strIngredient1")){
                                String value = jsonReader.nextString();
                                data.add(value);
                                break;
                            } else {
                                jsonReader.skipValue();
                            }
                        }
                        jsonReader.close();
                        myConnection.disconnect();
                    }else{
                        //Echec
                        System.out.println(myConnection.getResponseCode());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        /*
        // creation de l'adapter
        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.fragment_home, data);

        listView.setAdapter(adapter);

         */

        return root;
    }
}
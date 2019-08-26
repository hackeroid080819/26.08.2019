package com.example.a2608jsonusers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayAdapter = new ArrayAdapter(getBaseContext(), android.R.layout.simple_list_item_1, new ArrayList<String>());
        final ListView titles_list_view_LV = findViewById(R.id.titles_list_view_LV);

        titles_list_view_LV.setAdapter(arrayAdapter);

        OkHttpClient client = new OkHttpClient();

        String url = "https://jsonplaceholder.typicode.com/todos";

        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.isSuccessful()) {

                    final String myResponse = response.body().string();

                    GsonBuilder gsonBuilder = new GsonBuilder();
                    Gson gson = gsonBuilder.create();

                    final WebUsers[] webUsers =gson.fromJson(myResponse,WebUsers[].class);


                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            for (WebUsers webUser : webUsers) {
                                arrayAdapter.add(webUser.title);
                            }

                        }
                    });
                }
            }
        });





    }
}

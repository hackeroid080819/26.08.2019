package com.example.a2608_;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView mTextViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewResult = findViewById(R.id.text_view_result);

        // create client
        OkHttpClient client = new OkHttpClient();

        // create a request object
        String url = "http://jsonplaceholder.typicode.com/users/1";
        url = "https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22";
        Request request = new Request.Builder().url(url).build();

        // enqueue - add item to the request queue
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                mTextViewResult.setText(e.getMessage());
            }
            String temp = "";
            // SUCCESS!!!
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {

                    // non graphic commands
                    final String myResponse = response.body().string();

                    JSONObject data = null;
                    try {
                        data = new JSONObject(myResponse);
                        Log.d("============", data.getString("main"));

                        //  {
                        //        "temp": 280.32,
                        //        "pressure": 1012,
                        //        "humidity": 81,
                        //        "temp_min": 279.15,
                        //        "temp_max": 281.15
                        //    },
                        String main = data.getString("main");
                        JSONObject data2 = new JSONObject(main);
                        temp = data2.getString("temp");
                        Log.e("============", temp);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // grahic commands (UI)
                            mTextViewResult.setText("London: " + temp);
                        }
                    });
                }
            }
        });
    }


}

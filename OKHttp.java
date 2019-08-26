import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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

       OkHttpClient client = new OkHttpClient();

       String url = "http://jsonplaceholder.typicode.com/users/1";

       Request request = new Request.Builder()
               .url(url)
               .build();

       client.newCall(request).enqueue(new Callback() {
           @Override
           public void onFailure(Call call, IOException e) {
               e.printStackTrace();
           }

           @Override
           public void onResponse(Call call, Response response) throws IOException {
               if (response.isSuccessful()) {
                   final String myResponse = response.body().string();

                   MainActivity.this.runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           mTextViewResult.setText(myResponse);
                       }
                   });
               }
           }
       });
   }


}

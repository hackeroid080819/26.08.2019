
        implementation 'com.google.code.gson:gson:2.8.5'

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String serverResponse = "{\"temp\": 280.32,\"pressure\": 1012,\"humidity\": 81,\"temp_min\": 279.15,\"temp_max\": 281.15}";
        
        
public class MainWeather {

    public float temp;
    public int pressure;
    public int humidity;
    public float temp_min;
    public float temp_max;
}

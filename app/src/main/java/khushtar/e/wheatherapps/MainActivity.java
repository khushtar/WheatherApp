package khushtar.e.wheatherapps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button btn_getCityId,btn_searchById,btn_searchByName;
    EditText et_search;
    ListView lv_searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assigning ID's to every Button
        btn_getCityId=findViewById(R.id.getCityId);
        btn_searchById=findViewById(R.id.getWheatherById);
        btn_searchByName=findViewById(R.id.getWheatherByName);
        et_search=findViewById(R.id.et_search);
        lv_searchView=findViewById(R.id.searchList);


        //making click on listener for all btn
        btn_getCityId.setOnClickListener(v->{


//              RequestQueue requestQueue=Volley.newRequestQueue(this);
            RequestQueue queue = MySingleton.getInstance(this.getApplicationContext()).
                    getRequestQueue();
              String url="https://www.metaweather.com/api/location/search/?query="+et_search.getText().toString();

            JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, url, null,
                    response -> {
                        String cityId="";
                        try {
                            JSONObject obj=response.getJSONObject(0);
                            cityId=obj.getString("woeid");
                        } catch (JSONException e) {
                            Log.d("error is", e.toString());
                            e.printStackTrace();
                        }
                        Toast.makeText(this, "city ID = " +cityId, Toast.LENGTH_SHORT).show();
                    }, error -> {
                Toast.makeText(this, "Error Occured", Toast.LENGTH_SHORT).show();
            });
            queue.add(request);

//            Instantiate the RequestQueue.
//            RequestQueue queue = Volley.newRequestQueue(this);
//            String url ="https://www.google.com";
//
//            // Request a string response from the provided URL.
//            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//
//                            Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
//                        }
//                    }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
//                }
//            });
            // Add the request to the RequestQueue.
//            queue.add(stringRequest);

            Toast.makeText(this, "clicked btn 1", Toast.LENGTH_SHORT).show();
        });

        btn_searchById.setOnClickListener(v->{
            Toast.makeText(this, "clicked btn 2", Toast.LENGTH_SHORT).show();
        });

        btn_searchByName.setOnClickListener(v->{
            Toast.makeText(this, "clicked search for "+et_search.getText().toString(), Toast.LENGTH_SHORT).show();
        });


    }
}
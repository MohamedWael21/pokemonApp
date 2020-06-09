package androis.myapp.pokemon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private Adapter myadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

         myadapter = new Adapter(load(),MainActivity.this);
         recyclerView.setAdapter(myadapter);
         recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


    }





    public ArrayList load()
    {
        String url = "https://pokeapi.co/api/v2/pokemon?limit=150";
        final ArrayList<pokemonmodel>  pokemons  = new ArrayList<>();
        JsonObjectRequest  request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonarray = response.getJSONArray("results");
                    for(int i=0; i < jsonarray.length();i++){
                        JSONObject object = jsonarray.getJSONObject(i);
                        String name = object.getString("name").substring(0,1).toUpperCase() + object.getString("name").substring(1);
                        pokemons.add(new pokemonmodel(name,object.getString("url")));
                    }
                    myadapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    Log.e("JSONPARSE",e.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(request);
        return pokemons;

    }
}

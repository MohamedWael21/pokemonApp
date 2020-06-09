package androis.myapp.pokemon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class pokemoninfo extends AppCompatActivity {
    private  String pokemon_name ;
    private  String pokemon_url ;
    private TextView name ;
    private TextView id  ;
    private TextView height;
    private TextView ability ;
    private TextView  ability2 ;
    private ImageView image ;
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemoninfo);

        Intent intent = getIntent();
        pokemon_name  = intent.getStringExtra("pokemon_name");
        pokemon_url   =  intent.getStringExtra("pkemon_url");


        name = findViewById(R.id.pokemon_name);
        id = findViewById(R.id.pokemon_id);
        height = findViewById(R.id.pokemon_height);
        ability = findViewById(R.id.pokemon_ability);
        ability2 = findViewById(R.id.pokemon_ability2);
        image = findViewById(R.id.spirites);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        name.setText(pokemon_name);
        loadData(pokemon_url);

    }

    public void loadData(String url ){

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject sprites = response.getJSONObject("sprites");
                    String image_url = sprites.getString("back_shiny");
                    Log.d("debug",image_url);
                    Picasso.with(getApplicationContext()).load(image_url).resize(500,500).into(image);

                    int h = response.getInt("height");
                    Log.d("debug",String.valueOf(h));
                    height.setText(String.valueOf(h));

                    int i = response.getInt("id");
                     Log.d("debug",String.valueOf(i));
                    id.setText(String.valueOf(i));


                    JSONArray jsonarray = response.getJSONArray("abilities");

                    JSONObject obj = jsonarray.getJSONObject(0);
                    JSONObject abliti = obj.getJSONObject("ability");
                    String  abil = abliti.getString("name");
                    Log.d("debug",abil);
                    ability.setText(abil);
                    if(jsonarray.length() > 1) {
                        JSONObject obj2 = jsonarray.getJSONObject(1);
                        JSONObject abliti2 = obj.getJSONObject("ability");
                        String abil2 = abliti.getString("name");
                        Log.d("debug", abil2);
                        ability2.setText(abil2);
                    }else{
                        ability2.setText("none");
                    }

                } catch (JSONException e) {
                    Log.e("JSONPARSE_Pokemoninfo",e.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                   Log.w("json",error.getMessage());
            }
        });


        requestQueue.add(request);

    }
}

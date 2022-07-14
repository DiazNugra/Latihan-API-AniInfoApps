package com.diazna.weatherapps;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button tombolCari;
    EditText editTextJudulAnime;
    TextView judulAnime, malID, sinopsisAnime, scoreAnime;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tombolCari = findViewById(R.id.tombolCari);

        editTextJudulAnime = findViewById(R.id.editTextJudulAnime);

        judulAnime = findViewById(R.id.JudulAnime);

        malID = findViewById(R.id.malID);

        sinopsisAnime = findViewById(R.id.sinopsis);

        scoreAnime = findViewById(R.id.score);


        tombolCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url ="https://api.jikan.moe/v4/anime?q="+editTextJudulAnime.getText().toString()+"&sfw";

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject info;
                        String judul = "";
                        String mal_id = "";
                        String sinopsis = "";
                        String score = "";
                        try {
                            JSONArray data = response.getJSONArray("data");
                            info = data.getJSONObject(0);
                            judul = info.getString("title");
                            mal_id = info.getString("url");
                            sinopsis = info.getString("synopsis");
                            score = info.getString("score");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        judulAnime.setText(judul);
                        malID.setText(mal_id);
                        sinopsisAnime.setText(sinopsis);
                        scoreAnime.setText(score);

                        //Toast.makeText(MainActivity.this, "judul nya = "+ judul.toString() + " mal id= "+ mal_id.toString() + " sinopsis= " + sinopsis.toString() + "", Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "ada yang salah tuan", Toast.LENGTH_SHORT).show();
                    }
                });

                queue.add(request);

//                // Request a string response from the provided URL.
//                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
//                            }
//                        }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(MainActivity.this, "Terjadi Error", Toast.LENGTH_SHORT).show();
//                    }
//                });

                // Add the request to the RequestQueue.



                //Toast.makeText(MainActivity.this, "Anda Memasukan "+editTextNamaKota.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
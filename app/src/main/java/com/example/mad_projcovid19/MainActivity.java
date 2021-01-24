package com.example.mad_projcovid19;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    TextView liveCases,liveRecovered,liveCritical,liveActive,liveTodayCases,liveTotalDeaths,liveTodayDeaths,liveCountryStats;
    SimpleArcLoader simpleArcLoader;
    ScrollView scrollView;
    PieChart pieChart;
    /*
    PieChart pieChart
    ScrollView sV;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        liveActive = findViewById(R.id.liveActive);
        liveTodayCases = findViewById(R.id.liveTodayCases);
        liveTotalDeaths = findViewById(R.id.liveTotalDeaths);
        /*liveTodayDeaths = findViewById(R.id.TodayDeaths);
        * */
        liveTodayDeaths = findViewById(R.id.liveTodayDeaths);
        liveCountryStats = findViewById(R.id.liveCountryStats);
        liveCases = findViewById(R.id.liveCases);
        liveRecovered = findViewById(R.id.liveRecovered);
        liveCritical = findViewById(R.id.liveCritical);


        simpleArcLoader = findViewById(R.id.loader);
        scrollView = findViewById(R.id.scrollStats);
        pieChart = findViewById(R.id.piechart);


        fetchData();

    }

    private void fetchData() {

        String url  = "https://disease.sh/v3/covid-19/all";

        simpleArcLoader.start();

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());

                            liveCases.setText(jsonObject.getString("cases"));
                            liveRecovered.setText(jsonObject.getString("recovered"));
                            liveCritical.setText(jsonObject.getString("critical"));
                            liveActive.setText(jsonObject.getString("active"));
                            liveTodayCases.setText(jsonObject.getString("todayCases"));
                            liveTotalDeaths.setText(jsonObject.getString("deaths"));
                            liveTodayDeaths.setText(jsonObject.getString("todayDeaths"));
                            liveCountryStats.setText(jsonObject.getString("affectedCountries"));

                            pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(liveActive.getText().toString()), Color.parseColor("#007a9e")));
                            pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(liveCases.getText().toString()), Color.parseColor("#E8D100")));
                            pieChart.addPieSlice(new PieModel("Recoverd",Integer.parseInt(liveRecovered.getText().toString()), Color.parseColor("#00a648")));
                            pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(liveTotalDeaths.getText().toString()), Color.parseColor("#ff0000")));
                            pieChart.startAnimation();

                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);


                        } catch (JSONException e) {
                            e.printStackTrace();
                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    public void clickCheckCountries(View view) {
        startActivity(new Intent(getApplicationContext(), CountryStats.class));

    }
}
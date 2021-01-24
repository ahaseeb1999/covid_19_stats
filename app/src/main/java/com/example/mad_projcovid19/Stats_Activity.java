package com.example.mad_projcovid19;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Stats_Activity extends AppCompatActivity {
    private  int positionCountry;
    TextView tvCountry,liveCases,liveRecovered,liveCritical,liveActive,liveTodayCases,liveTotalDeaths,liveTodayDeaths;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_);
        Intent intent = getIntent();
        positionCountry = intent.getIntExtra("position",0);

        getSupportActionBar().setTitle("Details of "+CountryStats.Country_DatasList.get(positionCountry).getCountry());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        tvCountry = findViewById(R.id.tvCountry);
        liveCases = findViewById(R.id.liveCases);
        liveRecovered = findViewById(R.id.liveRecovered);
        liveCritical = findViewById(R.id.liveCritical);
        liveActive = findViewById(R.id.liveActive);
        liveTodayCases = findViewById(R.id.liveTodayCases);
        liveTotalDeaths = findViewById(R.id.tvDeaths);
        liveTodayDeaths = findViewById(R.id.liveTodayDeaths);

        tvCountry.setText(CountryStats.Country_DatasList.get(positionCountry).getCountry());
        liveCases.setText(CountryStats.Country_DatasList.get(positionCountry).getCases());
        liveRecovered.setText(CountryStats.Country_DatasList.get(positionCountry).getRecovered());
        liveCritical.setText(CountryStats.Country_DatasList.get(positionCountry).getCritical());
        liveActive.setText(CountryStats.Country_DatasList.get(positionCountry).getActive());
        liveTodayCases.setText(CountryStats.Country_DatasList.get(positionCountry).getTodayCases());
        liveTotalDeaths.setText(CountryStats.Country_DatasList.get(positionCountry).getDeaths());
        liveTodayDeaths.setText(CountryStats.Country_DatasList.get(positionCountry).getTodayDeaths());

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
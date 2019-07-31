package com.gustiawandicoding.submissionkamus;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.gustiawandicoding.submissionkamus.db.KamusHelper;
import com.gustiawandicoding.submissionkamus.model.EngModel;
import com.gustiawandicoding.submissionkamus.model.IndModel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new LoadData().execute();

    }

    private class LoadData extends AsyncTask<Void, Integer, Void>{
        final String TAG = LoadData.class.getSimpleName();
        KamusHelper kamusHelper;
        AppPreference appPreference;

        @Override
        protected void onPreExecute() {
            kamusHelper = new KamusHelper(SplashScreenActivity.this);
            appPreference = new AppPreference(SplashScreenActivity.this);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Boolean firstRun = appPreference.getFirstRun();
        if (firstRun) {

            ArrayList<EngModel> engModels = preLoadEng();
            ArrayList<IndModel> indModels = preLoadInd();

            kamusHelper.open();
            kamusHelper.beginTransaction();

                try {
                    for (EngModel engModel : engModels) {
                    kamusHelper.insertENG(engModel);
                    }


                    for (IndModel indModel : indModels) {
                        kamusHelper.insertINA(indModel);
                    }

                    kamusHelper.setTransactionSuccess(); //Jika semua proses telah di set success maka akan di commit ke database

                } catch (Exception e) {
                    Log.e(TAG, "doInBackground: Exception");
                    e.printStackTrace();
                }
                kamusHelper.endTransaction();
                kamusHelper.close();
                appPreference.setFirstRun();
        }else {
            try {
                synchronized (this){
                    this.wait(2000);
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        return null;
    }

        @Override
        protected void onPostExecute(Void aVoid) {
            Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private ArrayList<IndModel> preLoadInd() {
        ArrayList<IndModel> indModels = new ArrayList<>();
        String line ;
        BufferedReader bufferedReader;
        try{
            Resources resources = getResources();
            InputStream inputStream = resources.openRawResource(R.raw.indonesia_english);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            int count = 0;
            do {
                line = bufferedReader.readLine();
                String[] strings = line.split("\t");
                IndModel indModel;
                indModel = new IndModel(strings[0], strings[1]);
                indModels.add(indModel);
                count++;
            } while (true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return indModels;
    }

    private ArrayList<EngModel> preLoadEng() {
        ArrayList<EngModel> engModels = new ArrayList<>();
        String line ;
        BufferedReader bufferedReader;
        try{
            Resources resources = getResources();
            InputStream inputStream = resources.openRawResource(R.raw.english_indonesia);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            int count = 0;
            do {
                line = bufferedReader.readLine();
                String[] strings = line.split("\t");
                EngModel engModel;
                engModel = new EngModel(strings[0], strings[1]);
                engModels.add(engModel);
                count++;
            } while (true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return engModels;
    }
}

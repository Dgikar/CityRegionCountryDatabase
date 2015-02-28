package com.fowlcam.android.cityregioncountrydatabase;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.fowlcam.android.cityregioncountrydatabase.db.DataSource;
import com.fowlcam.android.cityregioncountrydatabase.db.FowlHelper;
import com.fowlcam.android.cityregioncountrydatabase.db.Storable;
import com.fowlcam.android.cityregioncountrydatabase.model.City;
import com.fowlcam.android.cityregioncountrydatabase.model.Country;
import com.fowlcam.android.cityregioncountrydatabase.model.Region;
import com.fowlcam.android.cityregioncountrydatabase.model.ZipCode;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import au.com.bytecode.opencsv.CSVReader;


public class MainActivity extends Activity {
    DataSource source;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExecutorService service = Executors.newFixedThreadPool(4);

        source = DataSource.getInstance(new FowlHelper(getApplicationContext()));
        source.open();

        service.submit(new Runnable() {
            @Override
            public void run() {
                parseDbZip("allCountries.txt", new ZipCode());
                parseDbUsZip("US1.csv", new ZipCode());
            }
        });

        service.submit(new Runnable() {
            @Override
            public void run() {
                parseDb("countries.csv", new Country());
            }
        });

        service.submit(new Runnable() {
            @Override
            public void run() {
                parseDb("regions.csv", new Region());
            }
        });

        service.submit(new Runnable() {
            @Override
            public void run() {
                parseDb("cities.csv", new Region());
            }
        });

//        source.close();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                parseDbZip("allCountries.txt", new ZipCode());
//                parseDbUsZip("US1.csv", new ZipCode());
//                parseDb("countries.csv", new Country());
//                parseDb("regions.csv", new Region());
//                parseDb("cities.csv", new City());
//                Log.d("MainActivity", "LOADED!");
//            }
//        }).run();
    }

    private void parseDb(String sheetName, Storable storable) {

        String next[];
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(getAssets().open(sheetName)));
            for (; ; ) {
                next = reader.readNext();
                if (next != null) {
                    storable.get(next);
                    Log.d(sheetName, storable.toString());
                    source.addStorable(storable);
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void parseDbZip(String sheetName, ZipCode storable) {
//        DataSource source = DataSource.getInstance(new FowlHelper(getApplicationContext()));
//        source.open();
        String next[];
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(getAssets().open(sheetName)), '\t');
            for (; ; ) {
                next = reader.readNext();
                if (next != null) {
                    storable.get(next);
                    Log.d(sheetName, storable.toString());
                    source.addStorable(storable);
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        source.close();
    }

    private void parseDbUsZip(String sheetName, ZipCode storable) {
//        DataSource source = DataSource.getInstance(new FowlHelper(getApplicationContext()));
//        source.open();
        String next[];
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(getAssets().open(sheetName)));
            for (; ; ) {
                next = reader.readNext();
                if (next != null) {
                    storable.getUsZip(next);
                    Log.d(sheetName, storable.toString());
                    Storable storable1 = source.addStorable(storable);
                    storable1.toString();
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        source.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

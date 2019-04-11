package com.example.bgsdatastarter_s1520400;


/*  Starter project for Mobile Platform Development in Semester B Session 2018/2019
    You should use this project as the starting point for your assignment.
    This project simply reads the data from the required URL and displays the
    raw data in a TextField
*/

//
// Name                 Matt Hedderly
// Student ID           S1520400
// Programme of Study   Computing
//

// Update the package name to include your Student Identifier

import android.R.layout;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.Buffer;
import java.util.List;


public class MainActivity extends AppCompatActivity{

    private static final String TAG = "MainActivity";

    private ListView listQuakes;
    private String urlSource="http://quakes.bgs.ac.uk/feeds/MhSeismology.xml";
    private List earthquakes;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Set up the raw links to the graphical components
        listQuakes = (ListView) findViewById(R.id.homeListView);

        Log.d(TAG, "onCreate: starting AsyncTask ");

        DownloadXml downloadXml = new DownloadXml();
        downloadXml.execute(urlSource);
        Log.d(TAG, "onCreate: done");


    }

        //inner class for Async XML task
    private class DownloadXml extends AsyncTask<String, Void, String>{
            private static final String TAG = "DownloadXml";

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Log.d(TAG, "onPostExecute: parameter is " + s);

                XmlPullParserHandler xmlPullParserHandler = new XmlPullParserHandler();
                xmlPullParserHandler.parseXml(s);

                //set up the array adapter to populate listview
                ArrayAdapter<Earthquake> arrayAdapter = new ArrayAdapter<Earthquake>(MainActivity.this, R.layout.list_item, xmlPullParserHandler.getEarthquakes());
                listQuakes.setAdapter(arrayAdapter);
            }

            //method for getting the XML
            @Override
            protected String doInBackground(String... strings) {
                Log.d(TAG, "doInBackground: starts with " + strings[0]);
                String xmlDocument = downloadXML(strings[0]);
                if(xmlDocument == null){
                    Log.e(TAG, "doInBackground: Error downloading XML");
                }
                return xmlDocument;
            }

            private String downloadXML(String urlPath){
                StringBuilder xmlResult = new StringBuilder();

                try{
                    URL url = new URL(urlPath);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    //get the response code and log it
                    int response = connection.getResponseCode();
                    Log.d(TAG, "downloadXML: the response code is " + response);

                    //open the stream to get the data
                    InputStream inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader reader = new BufferedReader(inputStreamReader);

                    //actually read the chars
                    String xmlRead;
                    while((xmlRead = reader.readLine()) != null){
                        xmlResult.append(xmlRead);
                        xmlResult.append('\n');
                    }
                    reader.close();

                    //return the result as a string
                    return xmlResult.toString();

                } catch(MalformedURLException e){
                    Log.e(TAG, "downloadXML: Invalid URL " + e.getMessage() );
                }catch(IOException e){
                    Log.e(TAG, "downloadXML: IO Exception reading the data " + e.getMessage() );
                }
                return null;
            }
    }
}
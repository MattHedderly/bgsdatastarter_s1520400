package com.example.bgsdatastarter_s1520400;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

public class XmlPullParserHandler {
    private static final String TAG = "XmlParser";
    private ArrayList<Earthquake> earthquakes;

    public XmlPullParserHandler() {
        this.earthquakes = new ArrayList<>();
    }

    public ArrayList<Earthquake> getEarthquakes() {
        return earthquakes;
    }

    public boolean parseXml(String xmlRead){
        boolean status = true;
        Earthquake currentRecord = null;
        boolean inItem = false;
        String tagValue = "";

        try{
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xPullParse = factory.newPullParser();
            xPullParse.setInput(new StringReader(xmlRead));
            int eventType = xPullParse.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT){
                String tagName = xPullParse.getName();
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        Log.d(TAG, "parseXml: Starting tag for" + tagName);
                        if ("item".equalsIgnoreCase(tagName)){
                            inItem = true;
                            currentRecord = new Earthquake();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        tagValue = xPullParse.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        Log.d(TAG, "parseXml: Ending tag for " + tagName);
                        if (inItem){
                            if ("item".equalsIgnoreCase(tagName)){
                                earthquakes.add(currentRecord);
                                inItem = false;
                                }
                            else if ("pubDate".equalsIgnoreCase(tagName)){
                                currentRecord.setDate(tagValue);
                            }else if("description".equalsIgnoreCase(tagName)){
                                String[] parts = tagValue.split(";");
                                String[] locationParse = parts[1].split(":");
                                currentRecord.setLocation(locationParse[1]);

                                String[] latLongParse = parts[2].split(":");
                                String[] latLonSplit = latLongParse[1].split(",");
                                currentRecord.setLatitude(latLonSplit[0]);
                                currentRecord.setLongitude(latLonSplit[1]);

                            }
                        }
                        break;

                        default:
                }
                eventType = xPullParse.next();

            }

            //test the parse loop
            for (Earthquake app: earthquakes){
                Log.d(TAG, "**************");
                Log.d(TAG, app.toString());
            }

        }catch(Exception e){
            status = false;
            e.printStackTrace();
        }
        return status;


    }
}

package com.example.bgsdatastarter_s1520400;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.XMLFormatter;

public class XmlPullParserHandler {

    public List<Earthquake> earthquakes = new LinkedList<Earthquake>();
    //the tags i need are earthquake pubdate geo:lat geo:long

    public LinkedList<Earthquake> parseData(String rawData){
        Earthquake earthquake = null;


        try{
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xParse = factory.newPullParser();
            xParse.setInput(new StringReader(rawData));
            int eventType = xParse.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT){
                if(eventType == XmlPullParser.START_TAG){
                        if(xParse.getName().equalsIgnoreCase("item")){
                            Log.e("my tag", "start tag found");
                            earthquake = new Earthquake();
                    }else
                        if(xParse.getName().equalsIgnoreCase("title")){
                            String tempString = xParse.nextText();
                            earthquake.setLocation(tempString);
                    }else
                        if(xParse.getName().equalsIgnoreCase("pubDate")){
                            String tempString = xParse.nextText();
                            earthquake.setDate(tempString);
                        } else
                            if(xParse.getName().equalsIgnoreCase("geo:lat")){
                                String temp = xParse.nextText();
                                earthquake.setLatitude(temp);
                            }else
                                if(xParse.getName().equalsIgnoreCase("geo:lon")){
                                String temp = xParse.nextText();
                                earthquake.setLongitude(temp);
                            }
                            else if(eventType == XmlPullParser.END_TAG) {
                                    if (xParse.getName().equalsIgnoreCase("item")) {
                                        earthquakes.add(earthquake);
                                    }
                                }
                                eventType =xParse.next();

                }}
                return (LinkedList<Earthquake>) earthquakes;
            }catch (XmlPullParserException ae1){
            Log.e("myTag", "parsing error" + ae1.toString());

        }catch(IOException ae1){
            Log.e("myTag", "IO error due to parsing");
        }
        Log.e("my tag", "end document");
        return (LinkedList<Earthquake>) earthquakes;


    }
}

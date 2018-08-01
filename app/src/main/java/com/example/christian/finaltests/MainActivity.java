package com.example.christian.finaltests;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.InputDevice;
import android.view.View;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.christian.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("##########1");
        TextView textView = (TextView) findViewById(R.id.textView1);
        parseXML();

        System.out.println("TESTING LANGUAGE##### " + Locale.getDefault().getDisplayLanguage().toString());
        System.out.println("TESTING LANGUAGE##### " + Locale.getDefault().getLanguage().toString());
    }

    private void parseXML(){
        System.out.println("##########2");
        XmlPullParserFactory parserFactory;
        InputStream is;
        try {
            parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();
            if(Locale.getDefault().getDisplayLanguage().toString().equals("Deutsch")){
                is = getResources().openRawResource(R.raw.human_de);
            }
            else {
                is = getResources().openRawResource(R.raw.human_en);
            }

            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);
            parser.setInput(is,null);
            processParsing(parser);

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processParsing(XmlPullParser parser) throws XmlPullParserException, IOException {
        StringBuilder sb = new StringBuilder();

        int eventType = parser.getEventType();
        System.out.println("##########3");
        while(eventType != XmlPullParser.END_DOCUMENT){
            if(eventType==XmlPullParser.START_TAG){
                sb.append(parser.getName() + " "+ parser.getAttributeValue(null,"name"));

            }
            if(eventType==XmlPullParser.END_TAG){
                sb.append(parser.getName());
            }
            if(eventType==XmlPullParser.TEXT){
                sb.append(parser.getText());
            }
            //
            eventType = parser.next();
        }
        System.out.println(sb.toString());
    }


    public void changeScene(View view){
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        TextView editText = (TextView) findViewById(R.id.textView1);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivity(intent);
    }
}

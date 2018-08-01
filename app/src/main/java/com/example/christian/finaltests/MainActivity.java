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

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.christian.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.textView1);
        parseXML();
    }

    private void parseXML(){
        XmlPullParserFactory parserFactory;
        try {
            parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();
            InputStream is = getAssets().open("human_en.xml");
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
        int eventType = parser.getEventType();
        System.out.println("hier alles zeuch VOR WHILE/n");
        while(eventType != XmlPullParser.END_DOCUMENT){
            switch(eventType){
                case XmlPullParser.START_DOCUMENT:
                    System.out.println("hier alles zeuch/n");
                    System.out.println(parser.getName());
                    System.out.println(parser.getText());
                    break;
            }
            eventType = parser.next();
        }
    }


    public void changeScene(View view){
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        TextView editText = (TextView) findViewById(R.id.textView1);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivity(intent);
    }
}

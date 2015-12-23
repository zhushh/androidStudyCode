package com.example.zhushh.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private Button searchBtn = null;
    private EditText content = null;
    private EditText phoneNum = null;
    private static final String url = "http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx/getMobileCodeInfo";
    private static final int UPDATE_CONTENT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phoneNum = (EditText)findViewById(R.id.phoneNum);
        searchBtn = (Button)findViewById(R.id.searchButton);
        content = (EditText)findViewById(R.id.content);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestHttpURLConnection();
            }
        });
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message message) {
            Log.v("current", message.obj.toString());
            switch (message.what) {
                case UPDATE_CONTENT:
                    content.setText(message.obj.toString());
                    break;
                default:
                    break;
            }
        }
    };

    private void sendRequestHttpURLConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    // create a connection use url
                    connection = (HttpURLConnection) ((new URL(url.toString()).openConnection()));

                    // set Method
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(80000);
                    connection.setReadTimeout(80000);

                    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                    out.writeBytes("mobileCode=" + phoneNum.getText().toString() + "&userID=");

                    // logcat phone number
                    Log.v("phone number", phoneNum.getText().toString());

                    // get response data
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    // logcat the text
                    Log.v("response", response.toString());

                    Message message = new Message();
                    message.what = UPDATE_CONTENT;
                    message.obj = parseXMLWithPull(response.toString());
                    handler.sendMessage(message);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private String parseXMLWithPull(String xml) {
        String str = "";
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(xml));

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("string".equals(parser.getName())) {
                            str = parser.nextText();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                    default:
                        break;
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return str;
        }
    }
}

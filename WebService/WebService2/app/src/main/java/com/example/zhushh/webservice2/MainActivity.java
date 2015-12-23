package com.example.zhushh.webservice2;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static String str;
    public static final int GET_BITMAP = 0;
    private EditText editText = null;
    private Button createBtn = null;
    private ImageView imgView = null;
    private ProgressDialog progressDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // bind views
        editText = (EditText)findViewById(R.id.editText);
        createBtn = (Button)findViewById(R.id.createBtn);
        imgView = (ImageView)findViewById(R.id.imgView);

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = editText.getText().toString();
                if (!"".equals(str)) {
                    progressDialog = ProgressDialog.show(MainActivity.this, "Requesting", "Requesting...", true, true);
                    new Thread(new Download()).start();
                }
            }
        });
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message message) {
            // handler message
            Log.v("current", message.obj.toString());
            switch (message.what) {
                case GET_BITMAP:
                    progressDialog.dismiss();
                    byte[] data = Base64.decode((message.obj.toString()).getBytes(), Base64.DEFAULT);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                    imgView.setImageBitmap(bitmap);
            }
        }
    };

    /**
     * Created by zhushh on 2015/12/18.
     */
    public class Download implements Runnable {
        private final String NAMESPACE = "http://WebXml.com.cn/";
        private final String METHODNAME = "enValidateByte";
        private final String SOAPACTION = "http://WebXml.com.cn/enValidateByte";
        private final String URL = "http://webservice.webxml.com.cn/WebServices/ValidateCodeWebService.asmx";

        @Override
        public void run() {
            SoapObject request = new SoapObject(NAMESPACE, METHODNAME);
            Log.e("str", MainActivity.str);
            request.addProperty("byString", MainActivity.str);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE transportSE = new HttpTransportSE(URL);
            try {
                transportSE.call(SOAPACTION, envelope);
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (HttpResponseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.e("fault", envelope.bodyIn.toString());
            SoapObject result = (SoapObject)envelope.bodyIn;
            SoapPrimitive detail = (SoapPrimitive)result.getProperty("enValidateByteResult");

            Message message = new Message();
            message.what = MainActivity.GET_BITMAP;
            message.obj = detail.toString();
            handler.sendMessage(message);
        }
    }
}

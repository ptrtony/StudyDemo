package com.zhaofan.kotlin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import com.binaryfork.spanny.Spanny;
import com.zhaofan.im.admin.SDKBuilder;
import com.zhaofan.im.admin.SDKDirector;
import com.zhaofan.im.admin.SDKImplBuilder;

import static android.graphics.Color.*;

public class MainActivity extends AppCompatActivity {
    private TextView textView1,textview2,textview3;
    private SDKBuilder sdkBuilder = new SDKImplBuilder();
    SDKDirector sdkDirector = SDKDirector.getInstance(sdkBuilder);
    private static final String APP_VALUE_ONLINE = "B7XfhII5KwnHlwvuEhjdmGkK";
    private static final String CHAT_SERVER_URL = "https://oims-api.iposecure.com:7001";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = findViewById(R.id.textview1);
        textview2 = findViewById(R.id.textview2);
        textview3 = findViewById(R.id.textview3);
        Spanny spanny = new Spanny("Underline text", new UnderlineSpan())
                .append("\nRed text", new ForegroundColorSpan(RED))
                .append("\nPlain text");
        textView1.setText(spanny);
        findViewById(R.id.message_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sdkDirector.setAccount("111111")
                        .setAppValue(APP_VALUE_ONLINE)
                        .setCenterTitleColor(R.color.white)
                        .setCusColor(R.color.colorPrimary)
                        .setStatusBarColor(R.color.colorPrimary)
//                        .setChatHttpHost(CHAT_SERVER_URL)
                        .goToChatActivity(MainActivity.this);
            }
        });
//        textView1.setText();
//        textview2.setText();
//        textview3.setText();
    }
}

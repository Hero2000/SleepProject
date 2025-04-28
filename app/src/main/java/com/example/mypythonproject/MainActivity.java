package com.example.mypythonproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "WebSocketActivity";
    private WebSocket mWebSocket;
    private TextView textView;
    private EditText ipEditText;
    private static final int COLUMN_COUNT = 10;
    private static final int ROW_COUNT = 5;
    private static final int ELEMENT_SIZE_DP = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ipEditText = findViewById(R.id.aboveTextViewEditText);

        // 找到 TextView 和 Button
        textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);
        EditText inputEditText = findViewById(R.id.inputEditText);
        Button sendButton = findViewById(R.id.sendbutton);

        // 为 Button 设置点击事件监听器
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openwebsocket();

            }
        });

        // 为按钮设置点击监听器
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取 EditText 的内容
                String inputText = inputEditText.getText().toString();
                // 这里可以处理获取到的文本内容，例如打印到日志或者发送到服务器
                Log.d(TAG, "输入的内容是: " + inputText);
                if (mWebSocket != null) {
                    mWebSocket.send(inputText);
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebSocket != null) {
            mWebSocket.close(1000, null);
        }
    }

    private void openwebsocket() {
        //"ws://172.23.52.13:5000"
        String ipport = ipEditText.getText().toString();
        Log.d(TAG, "openwebsocket ip: " + ipport);
        OkHttpClient client = new OkHttpClient();
//        http://172.23.52.13:5000
//                     .url("ws://你的 Mac 端 IP 地址:端口号")
        Request request = new Request.Builder()
                .url(ipport) // 替换为 Mac 端的 WebSocket 地址
                .build();

        client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                mWebSocket = webSocket;
                Log.d(TAG, "WebSocket 连接已打开");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("WebSocket 连接已打开");
                    }
                });

                // 可以在这里发送消息
                webSocket.send("Hello from Android!");
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                Log.d(TAG, "收到文本消息: " + text);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("收到文本消息: " + text);
                    }
                });

            }

            @Override
            public void onMessage(WebSocket webSocket, ByteString bytes) {
                Log.d(TAG, "收到二进制消息");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("收到二进制消息");
                    }
                });

            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
                Log.d(TAG, "WebSocket 正在关闭，代码: " + code + ", 原因: " + reason);
                webSocket.close(1000, null);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("WebSocket 正在关闭，代码: " + code + ", 原因: " + reason);
                    }
                });

            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                Log.d(TAG, "WebSocket 已关闭，代码: " + code + ", 原因: " + reason);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("WebSocket 已关闭，代码: " + code + ", 原因: " + reason);
                    }
                });
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                Log.e(TAG, "WebSocket 连接失败", t);
            }
        });

    }
}
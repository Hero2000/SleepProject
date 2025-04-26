package com.example.mypythonproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 创建根布局
        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);

        // 创建子 View
        TextView textView = new TextView(this);
        textView.setText("动态创建的 TextView");

        Button button = new Button(this);
        button.setText("动态创建的 Button");

        // 设置布局参数
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(16, 16, 16, 16);

        // 将布局参数应用到子 View
        textView.setLayoutParams(layoutParams);
        button.setLayoutParams(layoutParams);

        // 将子 View 添加到根布局
        rootLayout.addView(textView);
        rootLayout.addView(button);

        // 设置根布局为活动的内容视图
        setContentView(rootLayout);

//
//        setContentView(R.layout.activity_main);
//        if (!Python.isStarted()) {
//            Python.start(new AndroidPlatform(this));
//        }
//        // 找到 TextView 和 Button
//        TextView textView = findViewById(R.id.textView);
//        Button button = findViewById(R.id.button);
//
//        // 为 Button 设置点击事件监听器
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 点击按钮后改变 TextView 的文本
////                textView.setText("Button Clicked!");
//
//                Python py = Python.getInstance();
//                PyObject pyObject = py.getModule("hello");
//                PyObject result = pyObject.callAttr("say_hello");
//
//
//                String output = result.toString();
//                Log.d("PythonOutput", output);
//                textView.setText(output);
//            }
//        });






    }
}
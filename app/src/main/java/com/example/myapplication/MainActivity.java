package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {
    public static int conn_on=0;//用于判断连接是否成功
    public static String password_receive;//用于接收数据库查询的返回数据
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText username = (EditText) findViewById(R.id.IDinput);//取得输入框的对象
        final EditText password = (EditText) findViewById(R.id.code_input);

        final TextView conn = (TextView) findViewById(R.id.conn);//取得网络提示框的对象
        conn.setBackgroundColor(Color.RED);//默认设成红色
        final Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                switch (conn_on)//根据返回值判断网络连接是否成功
                {
                    case 1:conn.setText("网络连接成功");conn.setBackgroundColor(Color.GREEN);break;
                    case 2:conn.setText("网络连接失败");break;
                }
                return false;
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = new Message();
                try {
                    connect.getConnection("person");//执行连接测试
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                handler.sendMessage(msg);//跳转到handler1
            }
        }).start();

        Button Register = findViewById(R.id.log_on_button);
        Register.setOnClickListener(new View.OnClickListener() {//注册
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            connect.insertIntoData(username.getText().toString(),password.getText().toString());//调用插入数据库语句
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        final Handler handler2 = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if(password_receive.equals(password.getText().toString()))//判断输入密码与取得的密码是否相同
                    Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        Button logon = findViewById(R.id.log_on);
        logon.setOnClickListener(new View.OnClickListener() {//登录
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        try {
                            password_receive=connect.querycol(username.getText().toString());//调用查询语句，获得账号对应的密码
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }handler2.sendMessage(msg);//跳转到handler2
                    }
                }).start();
            }
        });

    }
}
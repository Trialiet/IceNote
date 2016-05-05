package org.trialiet.notedemo.activity;

import java.util.HashMap;
import java.util.Map;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.trialiet.notedemo.R;
import org.trialiet.notedemo.util.HttpUtils;

public class LoginActivity extends Activity {
    private String loginUsername;
    private String loginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText etUsername = (EditText) findViewById(R.id.et_login_username);
        EditText etPassword = (EditText) findViewById(R.id.et_login_password);
        Button btnLogin = (Button) findViewById(R.id.btn_login_login);
        Button btnRegister = (Button) findViewById(R.id.btn_login_register);
        loginUsername = etUsername.getText().toString();
        loginPassword = etPassword.getText().toString();
        btnLogin.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
				/*String url = "http://192.168.1.104:8080/IceNote/login.action";
				LoginHandle(loginUsername, loginPasswd);
				*/
                Intent intent = new Intent(LoginActivity.this, ListActivity.class);
                intent.putExtra("username", loginUsername);
                LoginActivity.this.startActivity(intent);

            }
        });
        btnRegister.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //??????????????????????????
                //IceDBHelper.CreateUserDataTable(username, db);
            }
        });
    }

    public void LoginHandle(String username, String passwd){
        new Thread(new Runnable(){

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Map<String, String> data = new HashMap<String, String>();
                data.put("username", loginUsername);
                data.put("passwd", loginPassword);
                String encode = "UTF-8";
                int result = HttpUtils.sendMessage(data, encode);
                result = 1;	//???????????
                Looper.prepare();
                if (result == 1){
                    Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, ListActivity.class);
                    intent.putExtra("username", loginUsername);
                    LoginActivity.this.startActivity(intent);
                }
                else if (result == 0){
                    Toast.makeText(LoginActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(LoginActivity.this, "Exception", Toast.LENGTH_SHORT).show();
                }
                Looper.loop();
                handler.sendEmptyMessage(0);
            }

        }).start();
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
        }
    };
}


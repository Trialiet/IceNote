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
}


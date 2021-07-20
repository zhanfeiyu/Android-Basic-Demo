package com.mike.demo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.databinding.DataBindingUtil;

import com.mike.demo.base.BaseActivity;
import com.mike.demo.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity {
    private final String TAG = LoginActivity.class.getSimpleName();
    ActivityLoginBinding binding;
    private SharedPreferences preferences;  //数据以键值对的方式存储数据

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBindings();
        initSharedPreference();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        initUI();
    }

    SharedPreferences.Editor editor;

    private void initSharedPreference() {
        preferences = getSharedPreferences("loginInfoPreference", MODE_PRIVATE);
        editor = preferences.edit();
    }

    private void initDataBindings() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
    }

    private void initUI() {
        binding.btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginButtonClicked();
            }
        });

        loadLoginState(); //根据用户之前的配置判断是否需要显示用户名和密码
    }

    private void loadLoginState() {
        boolean rememberPwd = preferences.getBoolean("remember_password", false);
        if (rememberPwd) {
            String username = preferences.getString("login_username", "");
            String password = preferences.getString("login_password", "");
            binding.edittextUsername.setText(username);
            binding.editTextPassword.setText(password);
            binding.cbRememberPassword.setChecked(true);
        }
    }

    private void onLoginButtonClicked() {
        String userName = "";
        String password = "";

        userName = binding.edittextUsername.getText().toString();
        password = binding.editTextPassword.getText().toString();

        boolean rememberPwd = binding.cbRememberPassword.isChecked();

        if (rememberPwd) {
            // 保存到sharedpreference里面去
            editor.putString("login_username", userName);
            editor.putString("login_password", password);
            editor.putBoolean("remember_password", rememberPwd);
            editor.apply();  //提交，重要
        } else {
            editor.clear();
            editor.apply(); //重要
        }

        Log.d(TAG, "username = " + userName + " ,password = " + password);
        if (userName.equals("admin") && password.equals("Ab123456")) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "用户名或密码不正确", Toast.LENGTH_SHORT).show();
        }
    }
}

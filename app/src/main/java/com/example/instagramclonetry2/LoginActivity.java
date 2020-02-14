package com.example.instagramclonetry2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button signUpSwitchButton,loginButton;
    private TextView emailField,passwordField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginlayout);
        setTitle("Login");

        signUpSwitchButton=findViewById(R.id.signUpSwitchButton);
        loginButton=findViewById(R.id.loginButton);
        emailField=findViewById(R.id.loginemailField);
        passwordField=findViewById(R.id.loginpasswordField);
        passwordField.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_ENTER && event.getAction()== KeyEvent.ACTION_DOWN) {
                    onClick(loginButton);

                }
                return false;
            }
        });
        loginButton.setOnClickListener(LoginActivity.this);
        signUpSwitchButton.setOnClickListener(LoginActivity.this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.loginButton:
                if (emailField.getText().toString().equals("") || passwordField.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "Email and Pass aqword Required", Toast.LENGTH_LONG).show();
                } else {
                   final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                   progressDialog.setMessage("Login In Process");
                   progressDialog.show();
                    ParseUser.logInInBackground(emailField.getText().toString(), passwordField.getText().toString(), new LogInCallback() {

                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (user != null && e == null) {
                                Toast.makeText(LoginActivity.this, user.getEmail().toString() + " Login Succesfull", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(LoginActivity.this, e.getMessage() + "Error", Toast.LENGTH_LONG).show();
                            }

                           progressDialog.dismiss();
                        }
                    });
                }
                    break;
            case R.id.signUpSwitchButton:
                Intent intent=new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
                break;
        }
    }
                public void loginRootLayout(View view)
                {
                    try {
                        InputMethodManager inputMethodManager=(InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }

        }



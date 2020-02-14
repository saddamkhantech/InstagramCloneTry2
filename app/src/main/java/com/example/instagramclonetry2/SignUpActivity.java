package com.example.instagramclonetry2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private Button signUpButton, loginSwitch;
    private TextView namefield, emailfield, passwordfield;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.signuplayout);
        setTitle("SignUp");
        signUpButton = findViewById(R.id.signUpButton);
        loginSwitch = findViewById(R.id.loginSwitch);
        namefield = findViewById(R.id.signUpnamefield);
        emailfield = findViewById(R.id.signUpemailfield);
        passwordfield = findViewById(R.id.signUppasswordfield);
        passwordfield.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {

                    onClick(signUpButton);

                }
                return false;
            }

        });


        signUpButton.setOnClickListener(SignUpActivity.this);
        loginSwitch.setOnClickListener(SignUpActivity.this);

    }
    
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.signUpButton:
                if (namefield.getText().toString().equals("") || emailfield.getText().toString().equals("") || passwordfield.getText().toString().equals("")) {
                    Toast.makeText(SignUpActivity.this, "Email,Name,Password is required", Toast.LENGTH_LONG).show();
                }
                else{

                    final ParseUser parseUser = new ParseUser();
                    parseUser.setUsername(namefield.getText().toString());
                    parseUser.setEmail(emailfield.getText().toString());
                    parseUser.setPassword(passwordfield.getText().toString());
                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("SignUp In Process");
                    progressDialog.show();

                    parseUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {

                                Toast.makeText(SignUpActivity.this, "Succesful", Toast.LENGTH_LONG).show();

                            } else {
                                Toast.makeText(SignUpActivity.this, e.getMessage() + "Error", Toast.LENGTH_SHORT).show();
                            }
                            progressDialog.dismiss();

                        }
                    });
                }
                break;

            case R.id.loginSwitch:
                Intent intent =new Intent(SignUpActivity.this , LoginActivity.class);
                startActivity(intent);
                break;
        }


    }

    public void rootLayoutTapped(View view)
    {
        try{
            InputMethodManager inputMethodManager=(InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    }


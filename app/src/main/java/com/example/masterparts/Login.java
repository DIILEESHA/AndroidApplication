package com.example.masterparts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class Login extends AppCompatActivity {
    EditText mEmail,mPassword;
    Button mLoginBtn;
    FirebaseAuth fAuth;
    TextView mRegisterBtn;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.Login2);
        mPassword = findViewById(R.id.Password2);
        mLoginBtn = findViewById(R.id.button3);
        mRegisterBtn = findViewById(R.id.textView4);
        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password =mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is Required");
                    return;
                }
                if(password.length() < 6){
                    mPassword.setError("Password Must be >= 6 Characters");
                    return;

                }
                if(email.equals("") && password.equals(""))
                {
                    new SweetAlertDialog(Login.this,SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("wrong something")
                            .show();
                }
                if(email.equals("master@gmail.com") && password.equals("123456"))
                {
                    new SweetAlertDialog(Login.this,SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Login Successed!")
                            .show();
                    startActivity(new Intent(getApplicationContext(),Dashboard.class));
                }

                progressBar.setVisibility(View.VISIBLE);

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            new SweetAlertDialog(Login.this,SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("Login Success!")
                                    .show();
                            startActivity(new Intent(getApplicationContext(),feedback.class));
                        }else {
                            new SweetAlertDialog(Login.this,SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Oops, something went wrong!")
                                    .show();
                        }
                    }
                });




            }

        });

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignUp.class));
            }
        });


    }
}
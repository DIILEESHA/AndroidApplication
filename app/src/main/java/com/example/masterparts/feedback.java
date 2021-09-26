package com.example.masterparts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.masterparts.databinding.ActivityMainBinding;


public class feedback extends AppCompatActivity {

    EditText etTo, etSubject, etMsg;
    Button sendBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        etTo = findViewById(R.id.etTo);
        etSubject = findViewById(R.id.etSubject);
        etMsg = findViewById(R.id.etMsg);
        sendBtn = findViewById(R.id.sendBtn);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mEmail = new Intent(Intent.ACTION_SEND);
                mEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{ "abc@gmail.com"});
                mEmail.putExtra(Intent.EXTRA_SUBJECT, "subject");
                mEmail.putExtra(Intent.EXTRA_TEXT, "message"+etMsg.getText());
// prompts to choose email client
                mEmail.setType("message/rfc822");
                startActivity(Intent.createChooser(mEmail, "Choose Option!"));
            }
        });
    }
}



//        feedback = (Button) findViewById(R.id.feedback);
//        feedback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent  = new Intent(Intent.ACTION_SENDTO);
//                String UriText = "mailto:" + Uri.encode("dileeshanawarathna18@gmail.com");
//
//                            Uri uri = Uri.parse(UriText);
//                            intent.setData(uri);
//                            startActivity(Intent.createChooser(intent,"send email"));
//            }
//        });


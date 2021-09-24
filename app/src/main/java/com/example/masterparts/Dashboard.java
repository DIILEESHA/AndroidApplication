package com.example.masterparts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class Dashboard extends AppCompatActivity {

    public CardView main1, main2, main3, main4, main5, main6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        main1 = (CardView) findViewById(R.id.main1);
        main2 = (CardView) findViewById(R.id.main2);
        main3 = (CardView) findViewById(R.id.main3);
        main4 = (CardView) findViewById(R.id.main4);
        main5 = (CardView) findViewById(R.id.main5);
        main6 = (CardView) findViewById(R.id.main6);

        main1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this, AddActivity.class));
                finish();
            }
        });
        main2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this, IsuruActivity.class));
                finish();
            }
        });
    }
}

//        main1.setOnClickListener(this);
//        main2.setOnClickListener(this);
//        main3.setOnClickListener(this);
//        main4.setOnClickListener(this);
//        main5.setOnClickListener(this);
//        main6.setOnClickListener(this);
//
//        }
//
//
//    @Override
//    public void onClick(View view) {
//
//        Intent i;
//
//        switch (view.getId()) {
//            case R.id.main1 :
//                i = new Intent(this,AddActivity.class);
//                startActivity(i);
//                break;
//
//            case R.id.main2:
//                i = new Intent(this,IsuruActivity.class);
//                startActivity(i);
//                break;
//            Toast("clicked");
//
//            case R.id.c3:
//                i = new Intent(this,SenuraActivity.class);
//                startActivity(i);
//                break;
//
//
//            case R.id.c4:
//                i = new Intent(this,JnithaActivity.class);
//                startActivity(i);
//                break;
//        }
//
//
//    }
//
//    public void logout(View view) {
//
//            FirebaseAuth.getInstance().signOut();
//            startActivity(new Intent(getApplicationContext(),Login.class));
//            finish();
//
//    }
//}
//
//
//

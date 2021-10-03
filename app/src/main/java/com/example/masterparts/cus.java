package com.example.masterparts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class cus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus);

        final EditText senderName = findViewById(R.id.editTextTextPersonName);
        final EditText msgText = findViewById(R.id.editTextTextPersonName5);


        Button button1 = findViewById(R.id.button5);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();

                Map< String,Object > msg = new HashMap<>();
                msg.put(senderName.getText().toString(),msgText.getText().toString());
                Date currentTime = Calendar.getInstance().getTime();
                String userName = currentTime.toString();

                db.collection("message").document(userName)
                        .set(msg)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("","successfully");

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("","Error",e);
                    }
                });
                Toast.makeText(cus.this, "sent", Toast.LENGTH_SHORT).show();
            }

        });

        Button button2 = findViewById(R.id.button6);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText editText1=findViewById(R.id.editTextTextMultiLine);
                editText1.getText().clear();
                FirebaseFirestore db =FirebaseFirestore.getInstance();
                db.collection("message")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Log.d("", document.getId() + "=>" + document.getData());
                                        editText1.setText(document.getId() + document.getData() + "\n" + editText1.getText().toString());
                                    }
                                } else {
                                    Log.w("", "Error", task.getException());
                                }
                            }
                        });

            }
        });
    }
}
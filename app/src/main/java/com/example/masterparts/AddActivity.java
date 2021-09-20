package com.example.masterparts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AddActivity extends AppCompatActivity {

    //view
    EditText mTitleEt, mDescriptionEt,mBrandEt,mEnginecEt,mFueluseEt,mAddressEt;
    Button mSaveBtn,mListBtn;

    //dialog
    ProgressDialog pd;

    //firebase instance
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
//
//        //action bar and title
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle("Add Data");

        //initalize values
        mTitleEt = findViewById(R.id.titleEt);
        mDescriptionEt = findViewById(R.id.descriptionEt);
        mBrandEt = findViewById(R.id.brandEt);
        mEnginecEt = findViewById(R.id.enginecEt);
        mFueluseEt = findViewById(R.id.fueluseEt);
        mAddressEt = findViewById(R.id.addressEt);
        mSaveBtn = findViewById(R.id.saveBtn);
        mListBtn = findViewById(R.id.listBtn);

        //progress
        pd = new ProgressDialog(this);

        //firestore
        db = FirebaseFirestore.getInstance();

        //click button to send the data
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //input data
                String title = mTitleEt.getText().toString().trim();
                String description = mDescriptionEt.getText().toString().trim();
                String brand = mBrandEt.getText().toString().trim();
                String enginec = mEnginecEt.getText().toString().trim();
                String fueluse = mFueluseEt.getText().toString().trim();
                String address = mAddressEt.getText().toString().trim();

                //function call to upload data
                uploadData(title, description ,brand ,enginec ,fueluse ,address);

            }



        });
            mListBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(AddActivity.this ,ListActivity.class));
                    finish();
                }
            });
    }

//    private void uploadData(String title, String description, String brand, String enginec, String fueluse, String address) {


    private void uploadData(String title, String description ,String brand, String enginec, String fueluse, String address) {
        //set title
        pd.setTitle("Processing");
        //when the user click save btn
        pd.show();
        //random id to each data to be stored
        String id = UUID.randomUUID().toString();

        Map<String, Object> doc = new HashMap<>();
        doc.put("id", id);
        doc.put("title", title);
        doc.put("description", description);
        doc.put("brand", brand);
        doc.put("enginec",enginec);
        doc.put("fueluse",fueluse);
        doc.put("address",address);



        //add this data
        db.collection("Documents").document(id).set(doc)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //this will be called data added successfully

                        pd.dismiss();
                        Toast.makeText(AddActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //unsuccessfully

                        //show error

                        pd.dismiss();
                        Toast.makeText(AddActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }
}
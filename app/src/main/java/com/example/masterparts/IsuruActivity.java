package com.example.masterparts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

public class IsuruActivity extends AppCompatActivity {

    EditText mVehicleName,mSpareParts, mPlace, mModel, mPrice, mContactNum, mDescription;
    Button mPostAdd;
    FirebaseFirestore fStore;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isuru);


        mVehicleName = findViewById(R.id.Vehicle);
        mSpareParts = findViewById(R.id.parts);
        mPlace = findViewById(R.id.place);
        mModel = findViewById(R.id.editTextTextPersonName7);
        mPrice =findViewById(R.id.editTextTextPersonName2);
        mContactNum = findViewById(R.id.editTextTextPersonName3);
        mDescription =findViewById(R.id.editTextTextPersonName4);
        mPostAdd = findViewById(R.id.button);

        fStore = FirebaseFirestore.getInstance();
        pd = new ProgressDialog(this);

        mPostAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //input data
                String VehicleName = mVehicleName.getText().toString().trim();
                String SpareParts = mSpareParts.getText().toString().trim();
                String Place = mPlace.getText().toString().trim();
                String Model = mModel.getText().toString().trim();
                String Price = mPrice.getText().toString().trim();
                String Phone = mContactNum.getText().toString().trim();
                String Description = mDescription.getText().toString().trim();



                //function call to upload data
                uploadData(VehicleName, SpareParts ,Place ,Model ,Price ,Phone, Description );

                if(TextUtils.isEmpty(VehicleName)){
                    mVehicleName.setError("fields are empty");
                    return;
                }

            }
        });

    }




    private void uploadData(String VehicleName, String SpareParts ,String Place, String Model, String Price, String Phone, String Description) {
        //set title
        pd.setTitle("Processing");
        //when the user click save btn
        pd.show();
        //random id to each data to be stored
        String id = UUID.randomUUID().toString();

        Map<String, Object> doc = new HashMap<>();
        doc.put("id", id);
        doc.put("VehicleName", VehicleName);
        doc.put("SpareParts", SpareParts);
        doc.put("Place", Place);
        doc.put("Model",Model);
        doc.put("Price",Price);
        doc.put("Phone",Phone);
        doc.put("Description",Description);



        //add this data
        fStore.collection("parts").document(id).set(doc)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {


                        pd.dismiss();
                        Toast.makeText(IsuruActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {


                        pd.dismiss();
                        Toast.makeText(IsuruActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }
}













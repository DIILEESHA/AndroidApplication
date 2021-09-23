package com.example.masterparts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
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

public class IsuruActivity extends AppCompatActivity {

    //view
    EditText mVehicleName, mSparePart,mPlace,mModle,mPrice,mContactNumber ,mDescription;
    Button mSaveBtn1,mListBtn1;

    //dialog
    ProgressDialog pd;

    //firebase instance
    FirebaseFirestore db;

    String pId, pVehicleName, pSparePart, pPlace, pModle, pPrice, pContactNumber ,pDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isuru);
        ActionBar actionBar = getSupportActionBar();
        // actionBar.setTitle("Add Data");


        //initalize values
        mVehicleName = findViewById(R.id.Vehicle);
        mSparePart = findViewById(R.id.parts);
        mPlace = findViewById(R.id.place);
        mModle = findViewById(R.id.editTextTextPersonName7);
        mPrice = findViewById(R.id.editTextTextPersonName2);
        mContactNumber = findViewById(R.id.editTextTextPersonName3);
        mDescription = findViewById(R.id.editTextTextPersonName4);
        mSaveBtn1 = findViewById(R.id.button);
        mListBtn1= findViewById(R.id.mbtn4);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            //actionBar.setTitle("Update Data ");
            mSaveBtn1.setText("Update Data");
            pId = bundle.getString("pId");
            pVehicleName = bundle.getString("pVehicleName");
            pSparePart = bundle.getString("pSparePart");
            pPlace = bundle.getString("pPlace");
            pModle = bundle.getString("pModle");
            pPlace = bundle.getString("pPlace");
            pContactNumber = bundle.getString("pContactNumber");
            pDescription = bundle.getString("pDescription");

            //setdata
            mVehicleName.setText(pVehicleName);
            mSparePart.setText(pSparePart);
            mPlace.setText(pPlace);
            mModle.setText(pModle);
            mPrice.setText(pPrice);
            mContactNumber.setText(pContactNumber);
            mDescription.setText(pDescription);

        }
        else{
//                   actionBar.setTitle("Add Data");
            mSaveBtn1.setText("Save");
        }

        //progress
        pd = new ProgressDialog(this);

        //firestore
        db = FirebaseFirestore.getInstance();

        //click button to send the data
        mSaveBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle1 = getIntent().getExtras();
                if (bundle != null){
                    String id = pId;
                    String vehicleName = mVehicleName.getText().toString().trim();
                    String sparepart = mSparePart.getText().toString().trim();
                    String place = mPlace.getText().toString().trim();
                    String modle = mModle.getText().toString().trim();
                    String price = mPrice.getText().toString().trim();
                    String contactNumber = mContactNumber.getText().toString().trim();
                    String description = mDescription.getText().toString().trim();

                    updateData(id,vehicleName,sparepart,place,modle,price,contactNumber,description);

                }
                else{
                    String vehicleName = mVehicleName.getText().toString().trim();
                    String sparepart = mSparePart.getText().toString().trim();
                    String place = mPlace.getText().toString().trim();
                    String modle = mModle.getText().toString().trim();
                    String price = mPrice.getText().toString().trim();
                    String contactNumber = mContactNumber.getText().toString().trim();
                    String description = mDescription.getText().toString().trim();

                    uploadData(vehicleName,sparepart,place,modle,price,contactNumber,description);
                }

                //input data
                String vehicleName = mVehicleName.getText().toString().trim();
                String sparepart = mSparePart.getText().toString().trim();
                String place = mPlace.getText().toString().trim();
                String modle = mModle.getText().toString().trim();
                String price = mPrice.getText().toString().trim();
                String contactNumber = mContactNumber.getText().toString().trim();
                String description = mDescription.getText().toString().trim();;

                //function call to upload data
                uploadData(vehicleName,sparepart,place,modle,price,contactNumber,description);

            }



        });
        mListBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IsuruActivity.this ,SpareListActivity.class));
                finish();
            }
        });
    }

    private void updateData(String id, String vehicleName, String sparePart, String place, String model, String price, String contactNumber , String description) {
        //set title
        pd.setTitle("Updating....");
        //when the user click save btn
        pd.show();
        db.collection("Documents").document(id)
                .update("vehicleName",vehicleName,"sparePart",sparePart,"place",place,"model",model,"price",price,"contactNumber",contactNumber,"description",description)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(IsuruActivity.this, "Updated", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(IsuruActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void uploadData(String title, String description, String brand, String enginec, String fueluse, String address) {


    private void uploadData(String vehicleName, String sparePart, String place, String model, String price, String contactNumber , String description) {
        //set title
        pd.setTitle("Processing");
        //when the user click save btn
        pd.show();
        //random id to each data to be stored
        String id = UUID.randomUUID().toString();

        Map<String, Object> doc = new HashMap<>();
        doc.put("id", id);
        doc.put("vehicleName",vehicleName);
        doc.put("sparePart", sparePart);
        doc.put("place", place);
        doc.put("modle",model);
        doc.put("price",price);
        doc.put("contactNumber",contactNumber);
        doc.put("description",description);



        //add this data
        db.collection("parts").document(id).set(doc)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //this will be called data added successfully

                        pd.dismiss();
                        Toast.makeText(IsuruActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //unsuccessfully

                        //show error

                        pd.dismiss();
                        Toast.makeText(IsuruActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }
}
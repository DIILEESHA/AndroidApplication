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

import cn.pedant.SweetAlert.SweetAlertDialog;

public class IsuruActivity extends AppCompatActivity {


    EditText mVehicleName, mSparePart,mPlace,mModle,mPrice,mContactNumber ,mDescription;
    Button mSaveBtn1,mListBtn1;


    ProgressDialog pd;


    FirebaseFirestore db;

    String pId, pVehicleName, pSparePart, pPlace, pModle, pPrice, pContactNumber ,pDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isuru);
        ActionBar actionBar = getSupportActionBar();




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

            mSaveBtn1.setText("Update Data");
            pId = bundle.getString("pId");
            pVehicleName = bundle.getString("pVehicleName");
            pSparePart = bundle.getString("pSparePart");
            pPlace = bundle.getString("pPlace");
            pModle = bundle.getString("pModle");
            pPrice = bundle.getString("pPrice");
            pContactNumber = bundle.getString("pContactNumber");
            pDescription = bundle.getString("pDescription");


            mVehicleName.setText(pVehicleName);
            mSparePart.setText(pSparePart);
            mPlace.setText(pPlace);
            mModle.setText(pModle);
            mPrice.setText(pPrice);
            mContactNumber.setText(pContactNumber);
            mDescription.setText(pDescription);

        }
        else{

            mSaveBtn1.setText("Save");
        }


        pd = new ProgressDialog(this);


        db = FirebaseFirestore.getInstance();


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

                    if (TextUtils.isDigitsOnly(vehicleName)) {
                        mVehicleName.setError("Plz enter the value");
                        return;
                    }
                    else if (TextUtils.isEmpty(sparepart)) {
                        mSparePart.setError("Field is Empty");
                        return;
                    }
                    else if (TextUtils.isEmpty(place)) {
                        mPlace.setError("Field is Empty");
                        return;
                    }
                    else if (TextUtils.isEmpty(modle)) {
                        mModle.setError("Field is Empty");
                        return;
                    }
                    else if (TextUtils.isEmpty(price)) {
                        mPrice.setError("Field is Empty");
                    }
                    else if (TextUtils.isEmpty(contactNumber)) {
                        mContactNumber.setError("Field is Empty");
                    }
                    else if (TextUtils.isEmpty(description)) {
                        mDescription.setError("Field is Empty");
                    }
                    else if(vehicleName.isEmpty()  && sparepart.isEmpty() && place.isEmpty() && modle.isEmpty() && price.isEmpty()&& contactNumber.isEmpty()&& description.isEmpty() ){
                        new SweetAlertDialog(IsuruActivity.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("All fields are empty ")
                                .show();
                    }
                    uploadData(vehicleName,sparepart,place,modle,price,contactNumber,description);
                }


//                String vehicleName = mVehicleName.getText().toString().trim();
//                String sparepart = mSparePart.getText().toString().trim();
//                String place = mPlace.getText().toString().trim();
//                String modle = mModle.getText().toString().trim();
//                String price = mPrice.getText().toString().trim();
//                String contactNumber = mContactNumber.getText().toString().trim();
//                String description = mDescription.getText().toString().trim();;
//
//
//                uploadData(vehicleName,sparepart,place,modle,price,contactNumber,description);

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

        pd.setTitle("Updating....");

        pd.show();
        db.collection("parts").document(id)
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




    private void uploadData(String vehicleName, String sparePart, String place, String model, String price, String contactNumber , String description) {

        pd.setTitle("Processing");

        pd.show();

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




        db.collection("parts").document(id).set(doc)
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
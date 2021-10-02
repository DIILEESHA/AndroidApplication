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

public class JnithaActivity extends AppCompatActivity {


    EditText mFirstName, mLastName,mNIC,mTpNumber,mEmail ;
    Button mRegister,mViewDriverDetails;


    ProgressDialog pd;


    FirebaseFirestore db;

    String pId, pFirstName, pLastName, pNIC, pTpNumber, pEmail ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jnitha);
        ActionBar actionBar = getSupportActionBar();




        mFirstName = findViewById(R.id.fname);
        mLastName = findViewById(R.id.lname);
        mNIC = findViewById(R.id.nic);
        mTpNumber = findViewById(R.id.tp);
        mEmail = findViewById(R.id.email);
        mRegister = findViewById(R.id.Register);
        mViewDriverDetails = findViewById(R.id.button2);



        Bundle bundle = getIntent().getExtras();
        if(bundle != null){

            mRegister.setText("Update Data");
            pId = bundle.getString("pId");
            pFirstName = bundle.getString("pFirstName");
            pLastName = bundle.getString("pLastName");
            pNIC = bundle.getString("pNIC");
            pTpNumber = bundle.getString("pTpNumber");
            pEmail = bundle.getString("pEmail");

            mFirstName.setText(pFirstName);
            mLastName.setText(pLastName);
            mNIC.setText(pNIC);
            mTpNumber.setText(pTpNumber);
            mEmail.setText(pEmail);

        }
        else{

            mRegister.setText("Register");
        }


        pd = new ProgressDialog(this);


        db = FirebaseFirestore.getInstance();


        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle1 = getIntent().getExtras();
                if (bundle != null){
                    String id = pId;
                    String firstname = mFirstName.getText().toString().trim();
                    String lastname = mLastName.getText().toString().trim();
                    String nic = mNIC.getText().toString().trim();
                    String tpnumber = mTpNumber.getText().toString().trim();
                    String email = mEmail.getText().toString().trim();

                    updateData(id,firstname,lastname,nic,tpnumber,email);

                }
                else{
                    String firstname = mFirstName.getText().toString().trim();
                    String lastname = mLastName.getText().toString().trim();
                    String nic = mNIC.getText().toString().trim();
                    String tpnumber = mTpNumber.getText().toString().trim();
                    String email = mEmail.getText().toString().trim();

                    if (TextUtils.isDigitsOnly(firstname)) {
                        mFirstName.setError("please enter first name");
                        return;
                    }
                    else if (TextUtils.isEmpty(lastname)) {
                        mLastName.setError("please enter last name");
                        return;
                    }
                    else if (TextUtils.isEmpty(nic)) {
                        mNIC.setError("please enter nic");
                        return;
                    }
                    else if (TextUtils.isEmpty(tpnumber)) {
                        mTpNumber.setError("please enter tpnumber");
                        return;
                    }
                    else if (TextUtils.isEmpty(email)) {
                        mEmail.setError("please enter email");
                    }

                    else if(firstname.isEmpty()  && lastname.isEmpty() && nic.isEmpty() && tpnumber.isEmpty() && email.isEmpty() ){
                        new SweetAlertDialog(JnithaActivity.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("All fields are empty ")
                                .show();
                    }
                    uploadData(firstname,lastname,nic,tpnumber,email);
                }


                String firstname = mFirstName.getText().toString().trim();
                String lastname = mLastName.getText().toString().trim();
                String nic = mNIC.getText().toString().trim();
                String tpnumber = mTpNumber.getText().toString().trim();
                String email = mEmail.getText().toString().trim();


                uploadData(firstname,lastname,nic,tpnumber,email);

            }



        });
        mViewDriverDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(JnithaActivity.this ,DriverList.class));
                finish();
            }
        });
    }

    private void updateData(String id, String firstname,String lastname,String nic,String tpnumber,String email) {

        pd.setTitle("Updating....");

        pd.show();
        db.collection("Drivers").document(id)
                .update("firstname",firstname,"lastname",lastname,"nic",nic,"tpnumber",tpnumber,"email",email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(JnithaActivity.this, "Updated", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(JnithaActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }




    private void uploadData(String firstname, String lastname, String nic, String tpnumber, String email) {

        pd.setTitle("Processing");

        pd.show();

        String id = UUID.randomUUID().toString();

        Map<String, Object> doc = new HashMap<>();
        doc.put("id", id);
        doc.put("firstname",firstname);
        doc.put("lastname",lastname);
        doc.put("nic", nic);
        doc.put("tpnumber",tpnumber);
        doc.put("email",email);





        db.collection("Drivers").document(id).set(doc)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {


                        pd.dismiss();
                        Toast.makeText(JnithaActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {


                        pd.dismiss();
                        Toast.makeText(JnithaActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }
}
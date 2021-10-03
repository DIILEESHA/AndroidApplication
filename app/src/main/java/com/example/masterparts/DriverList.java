package com.example.masterparts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class DriverList extends AppCompatActivity {

    List<DriverModel> modelList = new ArrayList<>();
    RecyclerView mRecycleView;
    RecyclerView.LayoutManager layoutManager;

    FloatingActionButton mAddBtn;

    FirebaseFirestore db;

    DriverAdapter adapter;

    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_list);
//
//        //action bar and title
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle("List Data");

        db = FirebaseFirestore.getInstance();

        mRecycleView = findViewById(R.id.recycle_view);
        mAddBtn = findViewById(R.id.addBtn12);

        mRecycleView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(layoutManager);

        pd = new ProgressDialog(this);

        showData();

        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DriverList.this, JnithaActivity.class));
                finish();
            }
        });

    }

    private void showData() {
//        pd.setTitle("Loading !!");
//
//        pd.show();

        db.collection("Drivers")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        pd.dismiss();
                        for (DocumentSnapshot doc : task.getResult()) {
                            DriverModel model = new DriverModel(doc.getString("id"),
                                    doc.getString("firtname"),
                                    doc.getString("lastname"),
                                    doc.getString("nic"),
                                    doc.getString("tpnumber"),
                                    doc.getString("email"));


                                    modelList.add(model);
                        }

                        adapter = new DriverAdapter(DriverList.this,modelList);

                        mRecycleView.setAdapter(adapter);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(DriverList.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

    public void deleteData(int index) {
        //pd.setTitle("Deleting Data");
        //pd.show();

        db.collection("Drivers").document(modelList.get(index).getId())
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        modelList.clear();
                        new SweetAlertDialog(DriverList.this,SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Deleted Succesfully")
                                .show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(DriverList.this,e.getMessage(), Toast.LENGTH_SHORT)
                                .show();
                    }
                });
    }
}
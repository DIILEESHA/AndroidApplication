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
import android.widget.Button;
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


public class cuslist  extends AppCompatActivity {
    FloatingActionButton mAddBtn;

    List<Model> modelList = new ArrayList<>();
    RecyclerView mRecycleView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseFirestore db;

    clientadapter adapter;

    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuslist);
//        Rent1 = findViewById(R.id.rent1);
//
//        //action bar and title
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle("List Data");

        db = FirebaseFirestore.getInstance();

        mRecycleView = findViewById(R.id.recycle_view);
//        mRecycleView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
//        mRecycleView.setLayoutManager(layoutManager);

       pd = new ProgressDialog(this);
        showData();

        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(cuslist.this, AddActivity.class));
                finish();
            }
        });

    }

    private void showData() {
        db.collection("Documents")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        modelList.clear();
                        pd.dismiss();
                        for (DocumentSnapshot doc : task.getResult()) {
                            Model model = new Model(doc.getString("id"),
                                    doc.getString("title"),
                                    doc.getString("description"),
                                    doc.getString("brand"),
                                    doc.getString("enginec"),
                                    doc.getString("fueluse"),
                                    doc.getString("address"));
                            modelList.add(model);
                        }
                        adapter = new clientadapter(cuslist.this, modelList);

//                        mRecycleView.setAdapter(adapter);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(cuslist.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });



    }

}

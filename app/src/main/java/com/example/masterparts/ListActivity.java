package com.example.masterparts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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


public class ListActivity  extends AppCompatActivity{


    List<Model> modelList = new ArrayList<>();
    RecyclerView mRecycleView;
    RecyclerView.LayoutManager layoutManager;

    FloatingActionButton mAddBtn;

    FirebaseFirestore db;

    CustomAdapter adapter;

    ProgressDialog pd;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
//
//        //action bar and title
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle("List Data");

        db = FirebaseFirestore.getInstance();

        mRecycleView = findViewById(R.id.recycle_view);
        mAddBtn = findViewById(R.id.addBtn);



        mRecycleView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(layoutManager);

        pd = new ProgressDialog(this);

        showData();

        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListActivity.this, AddActivity.class));
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
                        adapter = new CustomAdapter(ListActivity.this, modelList);

                        mRecycleView.setAdapter(adapter);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(ListActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void deleteData(int index) {

            db.collection("Documents").document(modelList.get(index).getId())
                        .delete()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            modelList.clear();
                                new SweetAlertDialog(ListActivity.this,SweetAlertDialog.SUCCESS_TYPE)
                                        .setTitleText("Deleted Succesfully")
                                        .show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                                pd.dismiss();
                            Toast.makeText(ListActivity.this,e.getMessage(), Toast.LENGTH_SHORT)
                                    .show();
                        }
                    });

    }
}
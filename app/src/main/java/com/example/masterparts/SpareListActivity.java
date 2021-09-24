package com.example.masterparts;

import androidx.annotation.NonNull;
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

public class SpareListActivity extends AppCompatActivity {

    List<SpareModel> spareModelList = new ArrayList<>();
    RecyclerView mRecycleView;
    RecyclerView.LayoutManager layoutManager;

    FloatingActionButton mAddBtn;

    FirebaseFirestore db;

    SpareAdpter spareAdpter;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spare_list);

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
                startActivity(new Intent(SpareListActivity.this,IsuruActivity.class));

            }
        });

    }

    private void showData() {
        pd.setTitle("Loading !!");

        pd.show();

        db.collection("parts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        pd.dismiss();
                        for (DocumentSnapshot doc:task.getResult()){
                            SpareModel model = new SpareModel(doc.getString("id"),
                                    doc.getString("vehicleName"),
                                    doc.getString("sparePart"),
                                    doc.getString("place"),
                                    doc.getString("modle"),
                                    doc.getString("price"),
                                    doc.getString("contactNumber"),
                                    doc.getString("description"));

                                    spareModelList.add(model);
                        }
                        spareAdpter = new SpareAdpter(SpareListActivity.this,spareModelList);

                        mRecycleView.setAdapter(spareAdpter);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(SpareListActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

    }
}
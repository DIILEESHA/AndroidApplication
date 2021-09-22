package com.example.masterparts;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<ViewHolder> {

    ListActivity listActivity;
    List<Model> modelList;
    Context context;

    public CustomAdapter(ListActivity listActivity, List<Model> modelList) {
        this.listActivity = listActivity;
        this.modelList = modelList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.model_layout,viewGroup,false);

        ViewHolder viewHolder = new ViewHolder(itemView);

        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                String title = modelList.get(position).getTitle();
                String reg = modelList.get(position).getBrand();
                String engine = modelList.get(position).getEnginec();
                String fuel = modelList.get(position).getFueluse();
               String addr = modelList.get(position).getAddress();
                String desc = modelList.get(position).getDescription();
                Toast.makeText(listActivity, title+"\n"+desc ,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onItemLongClick(View view, int position) {

                AlertDialog.Builder builder = new AlertDialog.Builder(listActivity);

                String[] options = {"Update", "Delete"};
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                        if (which == 0){
                            String id = modelList.get(position).getId();
                            String title = modelList.get(position).getTitle();
                            String description = modelList.get(position).getDescription();
                            String brand = modelList.get(position).getBrand();
                            String enginec = modelList.get(position).getEnginec();
                            String fueluse = modelList.get(position).getFueluse();
                            String address = modelList.get(position).getAddress();

                            Intent intent = new Intent(listActivity,AddActivity.class);
                            intent.putExtra("pId",id);
                            intent.putExtra("pTitle",title);
                            intent.putExtra("pDescription",description);
                            intent.putExtra("pBrand",brand);
                            intent.putExtra("pEnginec",enginec);
                            intent.putExtra("pFueluse",fueluse);
                            intent.putExtra("pAddress",address);

                            listActivity.startActivity(intent);
                        }
                        if(which == 1){

                        }
                    }
                }).create().show();


            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.mTitletv.setText(modelList.get(i).getBrand());
       viewHolder.mRegtv.setText(modelList.get(i).getBrand());
        viewHolder.menginetv.setText(modelList.get(i).getEnginec());
        viewHolder.mfueltv.setText(modelList.get(i).getFueluse());
        viewHolder.maddresstv.setText(modelList.get(i).getAddress());
        viewHolder.mdescriptiontv.setText(modelList.get(i).getDescription());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}

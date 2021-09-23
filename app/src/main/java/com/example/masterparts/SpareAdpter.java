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

public class SpareAdpter extends RecyclerView.Adapter<SpareViewHolder> {

    SpareListActivity spareListActivity;
    List<SpareModel> spareModelList;
    Context context;

    public SpareAdpter(SpareListActivity spareListActivity ,List<SpareModel> spareModelList){

        this.spareListActivity =spareListActivity;
        this.spareModelList =spareModelList;
    }

    @NonNull
    @Override
    public SpareViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sparemodel,viewGroup,false);

        SpareViewHolder spareViewHolder =new SpareViewHolder(itemView);

        spareViewHolder.setOnClickListener(new SpareViewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String vehicleName = spareModelList.get(position).getVehicleName();
                String spareParts = spareModelList.get(position).getSparePart();
                String place = spareModelList.get(position).getPlace();
                String model = spareModelList.get(position).getModel();
                String price = spareModelList.get(position).getPrice();
                String contactNumber = spareModelList.get(position).getContactNumber();
                String description = spareModelList.get(position).getDescription();
                Toast.makeText(spareListActivity, vehicleName+"\n"+description ,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onItemLongClick(View view, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(spareListActivity);
                String[] options = {"Update", "Delete"};
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                        if (which == 0){
                            String id = spareModelList.get(position).getId();
                            String vehicleName = spareModelList.get(position).getVehicleName();
                            String sparePart = spareModelList.get(position).getSparePart();
                            String place = spareModelList.get(position).getPlace();
                            String model = spareModelList.get(position).getModel();
                            String price = spareModelList.get(position).getPrice();
                            String contactNumber = spareModelList.get(position).getContactNumber();
                            String description = spareModelList.get(position).getDescription();

                            Intent intent = new Intent(spareListActivity,IsuruActivity.class);
                            intent.putExtra("pId",id);
                            intent.putExtra("pVehicleName",vehicleName);
                            intent.putExtra("pSparePart",sparePart);
                            intent.putExtra("pPlace",place);
                            intent.putExtra("pModel",model);
                            intent.putExtra("pPrice",price);
                            intent.putExtra("pContactNumber",contactNumber);
                            intent.putExtra("pDescription",description);

                            spareListActivity.startActivity(intent);
                        }
                        if(which == 1){

                        }
                    }
                }).create().show();


            }
        });

        return spareViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SpareViewHolder viewHolder, int i) {
        viewHolder.mVehicleName.setText(spareModelList.get(i).getVehicleName());
        viewHolder.mSparePart.setText(spareModelList.get(i).getSparePart());
        viewHolder.mPlace.setText(spareModelList.get(i).getPlace());
        viewHolder.mModel.setText(spareModelList.get(i).getModel());
        viewHolder.mPrice.setText(spareModelList.get(i).getPrice());
        viewHolder.mContactNumber.setText(spareModelList.get(i).getContactNumber());
        viewHolder.mDescription.setText(spareModelList.get(i).getDescription());
    }

    @Override
    public int getItemCount() {
        return spareModelList.size();
    }
}

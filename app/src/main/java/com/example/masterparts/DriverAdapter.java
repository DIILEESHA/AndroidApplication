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

public class DriverAdapter extends RecyclerView.Adapter<ViewDriver> {

    DriverList DriverList;
    List<DriverModel> DriverModel;
    Context context;

    public DriverAdapter(DriverList DriverList, List<DriverModel> DriverModel){

        this.DriverList = DriverList;
        this.DriverModel = DriverModel;
    }

    @NonNull
    @Override
    public ViewDriver onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_driver_model,viewGroup,false);

        ViewDriver ViewDriver =new ViewDriver(itemView);

        ViewDriver.setOnClickListener(new ViewDriver.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String firstname = DriverModel.get(position).getFirstname();
                String lastname = DriverModel.get(position).getLastname();
                String nic = DriverModel.get(position).getNic();
                String tpnumber = DriverModel.get(position).getTpnumber();
                String email = DriverModel.get(position).getEmail();
                Toast.makeText(DriverList, firstname+"\n"+email ,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onItemLongClick(View view, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DriverList);
                String[] options = {"Update", "Delete"};
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                        if (which == 0){
                            String id = DriverModel.get(position).getId();
                            String firstname = DriverModel.get(position).getFirstname();
                            String lastname = DriverModel.get(position).getLastname();
                            String nic = DriverModel.get(position).getNic();
                            String tpnumber = DriverModel.get(position).getTpnumber();
                            String email = DriverModel.get(position).getEmail();

                            Intent intent = new Intent(DriverList,JnithaActivity.class);
                            intent.putExtra("pId",id);
                            intent.putExtra("pfirstname",firstname);
                            intent.putExtra("plastname",lastname);
                            intent.putExtra("pnic",nic);
                            intent.putExtra("ptpnumber",tpnumber);
                            intent.putExtra("pemail",email);

                            DriverList.startActivity(intent);
                        }
                        if(which == 1){
                            DriverList.deleteData(position);

                        }
                    }
                }).create().show();


            }
        });

        return ViewDriver;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewDriver viewHolder, int i) {
        viewHolder.mfirstname.setText(DriverModel.get(i).getFirstname());
        viewHolder.mlastname.setText(DriverModel.get(i).getLastname());
        viewHolder.mnic.setText(DriverModel.get(i).getNic());
        viewHolder.mtpnumber.setText(DriverModel.get(i).getTpnumber());
        viewHolder.memail.setText(DriverModel.get(i).getEmail());
    }

    @Override
    public int getItemCount() {
        return DriverModel.size();
    }
}

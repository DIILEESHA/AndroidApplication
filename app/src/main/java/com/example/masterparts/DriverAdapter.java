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
    List<DriverModel> DriverModelList;
    Context context;

    public DriverAdapter(DriverList DriverList, List<DriverModel> DriverModelList){

        this.DriverList = DriverList;
        this.DriverModelList = DriverModelList;
    }

    @NonNull
    @Override
    public ViewDriver onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_driver_model,viewGroup,false);

        ViewDriver ViewDriver =new ViewDriver(itemView);

        ViewDriver.setOnClickListener(new ViewDriver.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String firstname = DriverModelList.get(position).getFirstname();
                String lastname = DriverModelList.get(position).getLastname();
                String nic = DriverModelList.get(position).getNic();
                String tpnumber = DriverModelList.get(position).getTpnumber();
                String email = DriverModelList.get(position).getEmail();
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
                            String id = DriverModelList.get(position).getId();
                            String firstname = DriverModelList.get(position).getFirstname();
                            String lastname = DriverModelList.get(position).getLastname();
                            String nic = DriverModelList.get(position).getNic();
                            String tpnumber = DriverModelList.get(position).getTpnumber();
                            String email = DriverModelList.get(position).getEmail();

                            Intent intent = new Intent(DriverList,JnithaActivity.class);
                            intent.putExtra("pId",id);
                            intent.putExtra("pFirstName",firstname);
                            intent.putExtra("pLastName",lastname);
                            intent.putExtra("pNIC",nic);
                            intent.putExtra("pTpNumber",tpnumber);
                            intent.putExtra("pEmail",email);

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
        viewHolder.mfirstname.setText(DriverModelList.get(i).getFirstname());
        viewHolder.mlastname.setText(DriverModelList.get(i).getLastname());
        viewHolder.mnic.setText(DriverModelList.get(i).getNic());
        viewHolder.mtpnumber.setText(DriverModelList.get(i).getTpnumber());
        viewHolder.memail.setText(DriverModelList.get(i).getEmail());
    }

    @Override
    public int getItemCount() {
        return DriverModelList.size();
    }
}

package com.example.masterparts;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SpareViewHolder extends RecyclerView.ViewHolder {

    TextView mVehicleName,mSparePart, mPlace, mModel, mPrice, mContactNumber ,mDescription ;
    View mView;

    public SpareViewHolder(@NonNull View itemView) {
        super(itemView);

        mView = itemView;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v, getAdapterPosition());

            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mClickListener.onItemLongClick(v,getAdapterPosition());
                return true;
            }
        });
        mVehicleName = itemView.findViewById(R.id.VehicleName);
        mSparePart = itemView.findViewById(R.id.SparePart);
        mPlace = itemView.findViewById(R.id.place);
        mModel= itemView.findViewById(R.id.Model);
        mPrice = itemView.findViewById(R.id.price);
        mContactNumber = itemView.findViewById(R.id.contactNumber);
        mDescription = itemView.findViewById(R.id.description);

    }

    private SpareViewHolder.ClickListener mClickListener;

    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
    public void setOnClickListener(SpareViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }
}


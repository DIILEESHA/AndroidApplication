package com.example.masterparts;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewDriver extends RecyclerView.ViewHolder {

    TextView mfirstname,mlastname, mnic, mtpnumber, memail;
    View mView;

    public ViewDriver(@NonNull View itemView) {
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
        mfirstname = itemView.findViewById(R.id.rTitletv);
        mlastname = itemView.findViewById(R.id.rRegtv);
        mnic= itemView.findViewById(R.id.renginetv);
        mtpnumber= itemView.findViewById(R.id.rfueltv);
        memail = itemView.findViewById(R.id.raddresstv);

    }

    private ViewDriver.ClickListener mClickListener;

    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
    public void setOnClickListener(ViewDriver.ClickListener clickListener){
        mClickListener = clickListener;
    }
}

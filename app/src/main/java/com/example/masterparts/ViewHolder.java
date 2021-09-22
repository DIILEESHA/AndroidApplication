package com.example.masterparts;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    TextView mTitletv,mRegtv, menginetv, mfueltv, maddresstv, mdescriptiontv;
    View mView;

    public ViewHolder(@NonNull View itemView) {
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
        mTitletv = itemView.findViewById(R.id.rTitletv);
        mRegtv = itemView.findViewById(R.id.rRegtv);
        menginetv = itemView.findViewById(R.id.renginetv);
        mfueltv= itemView.findViewById(R.id.rfueltv);
        maddresstv = itemView.findViewById(R.id.raddresstv);
        mdescriptiontv = itemView.findViewById(R.id.rdescriptiontv);

    }

    private ViewHolder.ClickListener mClickListener;

    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
    public void setOnClickListener(ViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }
}

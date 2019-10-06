package com.example.recyclertp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.recyclertp.ui.fournisseurs.Fournisseur;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class RecyclerViewAdapterSuppliers extends RecyclerView.Adapter<RecyclerViewAdapterSuppliers.ViewHolder> {

    private List<Fournisseur> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public RecyclerViewAdapterSuppliers(Context context, List<Fournisseur> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row_suppliers, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            Fournisseur f = mData.get(position);
            holder.tvName.setText(f.getName());
            holder.tvTel.setText(f.getTel());
            holder.tvContact.setText(f.getContact());
            holder.tvAdresse.setText(f.getAd1());

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName;
        TextView tvAdresse;
        TextView tvTel;
        TextView tvContact;

        ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvAdresse = itemView.findViewById(R.id.tvAdresse);
            tvTel = itemView.findViewById(R.id.tvTel);
            tvContact = itemView.findViewById(R.id.tvContact);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // allows clicks events to be caught
    public void setOnClickListener(RecyclerViewAdapterSuppliers.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

package com.example.recyclertp.ui.produits;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.recyclertp.R;

public class ProduitDetailsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        Bundle bundle = this.getArguments();
        Produit p = bundle.getParcelable("produit");
        View v = inflater.inflate(R.layout.fragment_produit_details,container, false);

        //declaration des textview
        TextView title=v.findViewById(R.id.tvTitleD);
        TextView modelNo=v.findViewById(R.id.tvModelNoD);
        TextView unitPrice=v.findViewById(R.id.tvPriceD);
        TextView inventory=v.findViewById(R.id.tvInventoryD);
        TextView code=v.findViewById(R.id.tvCodeD);

        //set les valeurs pour les champs
        title.setText(p.getTitle());
        modelNo.setText(p.getModelNo());
        unitPrice.setText(Double.toString(p.getUnitPrice()));
        inventory.setText(Integer.toString(p.getInventory()));
        code.setText(p.getCode());
        return v;
    }

}

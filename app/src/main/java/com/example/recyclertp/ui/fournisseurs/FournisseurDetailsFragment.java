package com.example.recyclertp.ui.fournisseurs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.recyclertp.R;

public class FournisseurDetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        Bundle bundle = this.getArguments();
        Fournisseur p = bundle.getParcelable("fournisseur");
        View v = inflater.inflate(R.layout.fragment_fournisseur_details,container, false);

        //declaration des textview
        TextView name=v.findViewById(R.id.tvNameD);
        TextView ad1=v.findViewById(R.id.tvAd1D);
        TextView ad2=v.findViewById(R.id.tvAd2D);
        TextView city=v.findViewById(R.id.tvCityD);
        TextView prov=v.findViewById(R.id.tvProvD);
        TextView cP=v.findViewById(R.id.tvCPD);
        TextView tel=v.findViewById(R.id.tvTelD);
        TextView contact=v.findViewById(R.id.tvContactD);

        //set les valeurs pour les champs
        name.setText(p.getName());
        ad1.setText(p.getAd1());
        ad2.setText(p.getAd2());
        city.setText(p.getCity());
        prov.setText(p.getProv());
        cP.setText(p.getcP());
        tel.setText(p.getTel());
        contact.setText(p.getContact());
        return v;
    }
}

package com.example.recyclertp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.recyclertp.R;

import org.json.JSONObject;

public class ProduitDetailsFragment extends Fragment {

    //creer une nouvelle instance du fragment et la rtourne
    public static Fragment newInstance()
    {
        ProduitDetailsFragment newFragment = new ProduitDetailsFragment();
        return newFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_produit_details,container, false);
        TextView title=v.findViewById(R.id.tvTitleD);
        //title.setText(s);
        return v;
    }

}

package com.example.recyclertp.ui.produits;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.recyclertp.R;

public class ProduitsFragment extends Fragment {

    private ProduitsViewModel produitsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        produitsViewModel =
                ViewModelProviders.of(this).get(ProduitsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_produits, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        produitsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
package com.example.recyclertp.ui.fournisseurs;

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

public class FournisseursFragment extends Fragment {

    private FournisseursViewModel fournisseursViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        fournisseursViewModel =
                ViewModelProviders.of(this).get(FournisseursViewModel.class);
        View root = inflater.inflate(R.layout.fragment_fournisseurs, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        fournisseursViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
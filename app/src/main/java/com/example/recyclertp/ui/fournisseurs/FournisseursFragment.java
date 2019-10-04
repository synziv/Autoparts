package com.example.recyclertp.ui.fournisseurs;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.recyclertp.MyRecyclerViewAdapter;
import com.example.recyclertp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FournisseursFragment extends Fragment implements MyRecyclerViewAdapter.ItemClickListener{
    //variable pour le recycler
    private MyRecyclerViewAdapter adapter;

    private FournisseursViewModel fournisseursViewModel;
    private TextView textView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        fournisseursViewModel =
                ViewModelProviders.of(this).get(FournisseursViewModel.class);
        View root = inflater.inflate(R.layout.fragment_fournisseurs, container, false);
        textView= root.findViewById(R.id.text_home);
        fournisseursViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });

        //https://stackoverflow.com/questions/17037340/converting-jsonarray-to-arraylist
        requeteRetourArrayList("suppliers");





        return root;
    }
    //methode qui faire la requete pour get le liste de suppliers ou products selon le param
    public void requeteRetourArrayList(final String type)
    {

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url ="https://api.myjson.com/bins/wewjb";


        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray= response.getJSONArray(type);
                            //ladapater prendre slm un array list donc il faut transposer
                            ArrayList<JSONObject> data = new ArrayList<JSONObject>();
                            //boucle pour mettre la donnes dans un array list
                            for(int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject produit = jsonArray.getJSONObject(i);
                                String name = produit.getString("name");
                                data.add(produit);
                            }
                            //test poour set up le recyclerview
                            RecyclerView recyclerView = getActivity().findViewById(R.id.rv_Suppliers);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            adapter = new MyRecyclerViewAdapter(getActivity()
                                    , data);
                            //adapter.setClickListener(this);
                            recyclerView.setAdapter(adapter);

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        error.printStackTrace();
                    }
                });
        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);

    }


    @Override
    public void onItemClick(View view, int position) {
        //Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}
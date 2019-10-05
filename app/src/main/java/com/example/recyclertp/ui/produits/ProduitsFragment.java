package com.example.recyclertp.ui.produits;

import android.os.Bundle;
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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.recyclertp.R;
import com.example.recyclertp.RecyclerViewAdapterProducts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProduitsFragment extends Fragment /*implements RecyclerViewAdapterProducts.ItemClickListener*/{

    private ProduitsViewModel produitsViewModel;
    private RecyclerViewAdapterProducts adapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        produitsViewModel =
                ViewModelProviders.of(this).get(ProduitsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_produits, container, false);
        /*final TextView textView = root.findViewById(R.id.text_gallery);
        //produitsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        //https://stackoverflow.com/questions/17037340/converting-jsonarray-to-arraylist
        requeteRetourArrayList("products");

        return root;
    }
    //methode qui faire la requete pour get le liste de produits ou products selon le param
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
                                data.add(produit);
                            }
                            //test poour set up le recyclerview
                            RecyclerView recyclerView = getActivity().findViewById(R.id.rv_Products);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            adapter = new RecyclerViewAdapterProducts(getActivity()
                                    , data);
                            adapter.setOnClickListener(new RecyclerViewAdapterProducts.ItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    int x =position;
                                    //creation de la vue pour les details du produit
                                    View detailsView = getActivity().findViewById(R.id.nav_produit_details);
                                    TextView titleD =detailsView.findViewById(R.id.tvTitleD);
                                    titleD.setText("aaaa");

                                    Toast.makeText(getActivity(), Integer.toString(position), Toast.LENGTH_LONG).show();
                                }
                            });
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


    /*Override
    public void onItemClick(View view, int position) {
        //Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(), position, Toast.LENGTH_LONG).show();
    }*/
}
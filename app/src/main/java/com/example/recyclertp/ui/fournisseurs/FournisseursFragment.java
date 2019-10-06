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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.recyclertp.RecyclerViewAdapterProducts;
import com.example.recyclertp.RecyclerViewAdapterSuppliers;
import com.example.recyclertp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FournisseursFragment extends Fragment implements RecyclerViewAdapterSuppliers.ItemClickListener{
    //variable pour le recycler
    private RecyclerViewAdapterSuppliers adapter;

    private FournisseursViewModel fournisseursViewModel;
    private TextView textView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        fournisseursViewModel =
                ViewModelProviders.of(this).get(FournisseursViewModel.class);
        View root = inflater.inflate(R.layout.fragment_fournisseurs, container, false);
        /*textView= root.findViewById(R.id.text_home);
        fournisseursViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });*/

        //https://stackoverflow.com/questions/17037340/converting-jsonarray-to-arraylist
        requeteRetourArrayList("suppliers");

        return root;
    }
    //methode qui faire la requete pour get le liste de suppliers ou products selon le param
    public void requeteRetourArrayList(final String type)
    {

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url ="https://api.myjson.com/bins/wewjb";
        final ArrayList<Fournisseur> data = new ArrayList<Fournisseur>();

        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray= response.getJSONArray(type);
                            //ladapater prendre slm un array list donc il faut transposer

                            //boucle pour mettre la donnes dans un array list
                            for(int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject fournisseurJson = jsonArray.getJSONObject(i);
                                Fournisseur fournisseur = new Fournisseur(
                                        fournisseurJson.getInt("id"),
                                        fournisseurJson.getString("name"),
                                        fournisseurJson.getString("address_line_1"),
                                        fournisseurJson.getString("address_line_2"),
                                        fournisseurJson.getString("address_city"),
                                        fournisseurJson.getString("address_province"),
                                        fournisseurJson.getString("address_postal_code"),
                                        fournisseurJson.getString("telephone"),
                                        fournisseurJson.getString("contact"));
                                data.add(fournisseur);
                            }
                            //creer le recyclerview
                            RecyclerView recyclerView = getActivity().findViewById(R.id.rv_Suppliers);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            adapter = new RecyclerViewAdapterSuppliers(getActivity()
                                    , data);

                            //set up le click listener
                            adapter.setOnClickListener(new RecyclerViewAdapterSuppliers.ItemClickListener() {
                                //affiche les detials du produits en clicquant dessus
                                @Override
                                public void onItemClick(View view, int position) {
                                    Bundle bundle=new Bundle();
                                    bundle.putParcelable("fournisseur", data.get(position));
                                    Navigation.findNavController(view).navigate(R.id.nav_fournisseurs_details, bundle);
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


    @Override
    public void onItemClick(View view, int position) {
        //Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}
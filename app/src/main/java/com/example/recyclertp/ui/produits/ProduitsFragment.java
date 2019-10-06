package com.example.recyclertp.ui.produits;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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
import com.example.recyclertp.ui.ProduitDetailsFragment;

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

        requeteRetourArrayList("products");





        return root;
    }
    //methode qui faire la requete pour get le liste de produits ou products selon le param
    public void requeteRetourArrayList(final String type)
    {

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url ="https://api.myjson.com/bins/wewjb";

        //jsonarray pour le return
        final ArrayList<Produit> data = new ArrayList<Produit>();
        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            //get le array de tous les produits
                            final JSONArray jsonArray= response.getJSONArray(type);

                            //boucle pour mettre la donnes dans un array list
                            for(int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject produitJson = jsonArray.getJSONObject(i);
                                //transfert a produitJSon a produit
                                Produit p = new Produit(
                                        produitJson.getInt("id"),
                                        produitJson.getString("title"),
                                        produitJson.getString("modelNo"),
                                        produitJson.getString("code"),
                                        produitJson.getDouble("unitPrice"),
                                        produitJson.getInt("inventory"),
                                        produitJson.getInt("supplierId"));
                                data.add(p);
                            }

                            //Creation du recyclerView******************************************
                            RecyclerView recyclerView = getActivity().findViewById(R.id.rv_Products);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            //peuple le recycler avec ladapter
                            adapter = new RecyclerViewAdapterProducts(getActivity()
                                    , data);
                            adapter.setOnClickListener(new RecyclerViewAdapterProducts.ItemClickListener() {
                                //affiche les detials du produits en clicquant dessus
                                @Override
                                public void onItemClick(View view, int position) {
                                    ProduitDetailsFragment p = new ProduitDetailsFragment();
                                    Bundle bundle=new Bundle();
                                    bundle.putParcelable("produit", data.get(position));
                                    Navigation.findNavController(view).navigate(R.id.nav_produit_details, bundle);
                                }
                            });
                            recyclerView.setAdapter(adapter);
                            //************************************************************************

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

        data.size();
    }


    /*Override
    public void onItemClick(View view, int position) {
        //Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(), position, Toast.LENGTH_LONG).show();
    }*/
}
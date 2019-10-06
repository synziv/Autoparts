package com.example.recyclertp.ui.produits;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProduitsViewModel extends ViewModel {
    public ArrayList<JSONObject> data;


    /*public ArrayList<JSONObject> getData(final String type, Context context){
        requeteRetourArrayList(type, context, new VolleyCallBack() {
            @Override
            public void onSuccessResponse(ArrayList<JSONObject> response) {
                data=response;
            }
        });
        data.size();
        return data;
    }*/



}

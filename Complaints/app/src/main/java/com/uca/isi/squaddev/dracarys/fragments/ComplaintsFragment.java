package com.uca.isi.squaddev.dracarys.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.tumblr.remember.Remember;
import com.uca.isi.squaddev.dracarys.R;
import com.uca.isi.squaddev.dracarys.activities.ComplaintAddActivity;
import com.uca.isi.squaddev.dracarys.adapters.ComplaintsAdapter;
import com.uca.isi.squaddev.dracarys.api.Api;
import com.uca.isi.squaddev.dracarys.models.Complaint;
/**
 * Created by ericb on 28/10/2017.
 */

public class ComplaintsFragment extends Fragment {

    private RecyclerView recyclerView;


    public ComplaintsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_complaints, container, false);

        // Call init method
        init(view);

        return view;
    }

    /**
     * This method instance variables
     * @param view
     */
    private void init(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        Call<List<Complaint>> call = Api.instance().getComplaints(Remember.getString("access_token", ""));
        call.enqueue(new Callback<List<Complaint>>() {
            @Override
            public void onResponse(Call<List<Complaint>> call, Response<List<Complaint>> response) {
                if(response.body() != null) {
                    ComplaintsAdapter complaintsAdapter = new ComplaintsAdapter(response.body());
                    recyclerView.setAdapter(complaintsAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Complaint>> call, Throwable t) {
                Log.e("xD", t.getMessage());

            }
        });



    }

}
package com.uca.isi.squaddev.dracarys.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.tumblr.remember.Remember;
import com.uca.isi.squaddev.dracarys.R;
import com.uca.isi.squaddev.dracarys.api.Api;
import com.uca.isi.squaddev.dracarys.models.Complaint;
/**
 * Created by ericb on 28/10/2017.
 */

public class ComplaintAddActivity extends AppCompatActivity {

    private EditText title;
    private EditText description;
    private Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_add);

        initViews();
        initActions();

    }

    /**
     * To init views on variables
     */
    private void initViews() {
        title = (EditText) findViewById(R.id.title);
        description = (EditText) findViewById(R.id.description);
        create = (Button) findViewById(R.id.create);
    }

    /**
     * Logic button action
     */
    private void initActions() {
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                create();
            }
        });
    }

    private void create() {

        if (title.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Ingrese un título", Toast.LENGTH_LONG).show();
        } else if(description.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Ingrese una descripción", Toast.LENGTH_LONG).show();
        } else {
            // this instance new complaint with data xD
            Complaint complaint = new Complaint();
            complaint.setTitle(title.getText().toString());
            complaint.setDescription(description.getText().toString());
            complaint.setCategoryId(1);
            complaint.setUserId(1);
            complaint.setEnabled(true);

            // this make http request to create an complaint
            Call<Complaint> call = Api.instance().createComplaint(Remember.getString("access_token", ""), complaint);
            call.enqueue(new Callback<Complaint>() {
                @Override
                public void onResponse(Call<Complaint> call, Response<Complaint> response) {
                    if (response.body() != null) {
                        Complaint complaintResult  = response.body();
                        assert complaintResult != null;
                        Log.i("title", complaintResult.getTitle());
                        Log.i("description", complaintResult.getDescription());
                    }
                }

                @Override
                public void onFailure(Call<Complaint> call, Throwable t) {
                    Log.e("Error al crear", t.getMessage());

                }
            });


        }
    }

}
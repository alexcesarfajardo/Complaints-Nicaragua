package com.uca.isi.squaddev.dracarys.api;

/**
 * Created by ericb on 28/10/2017.
 */
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import com.uca.isi.squaddev.dracarys.models.Category;
import com.uca.isi.squaddev.dracarys.models.Complaint;
import com.uca.isi.squaddev.dracarys.models.AccessToken;
import com.uca.isi.squaddev.dracarys.models.User;

public interface ApiInterface {

    String ACCESS_TOKEN = "10JHg2HD9AjO9g0sZ7VZOWePyfQU2FUBWHFAfHTqAnKuCTnT8O9WZaotbFvQgzJc";

    @Headers("Authorization: " + ACCESS_TOKEN)
    @GET("Categories")
    Call<List<Category>> getCategories();

    @Headers("Authorization: " + ACCESS_TOKEN)
    @GET("Complaints")
    Call<List<Complaint>> getComplaints();

    @Headers("Authorization: " + ACCESS_TOKEN)
    @POST("Complaints")
    Call<Complaint> createComplaint(@Body Complaint complaint);

    @POST("Users/login")
    Call<AccessToken> login(@Body User user);

    @POST("Users")
    Call<User> signUp(@Body User user);

    @GET("Complaints/{id}?filter={\"include\": \"pictures\"}")
    Call<Complaint> complaint(@Path("id") int complaintId);
}

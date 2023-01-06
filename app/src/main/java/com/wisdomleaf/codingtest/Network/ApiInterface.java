package com.wisdomleaf.codingtest.Network;

import com.google.gson.JsonObject;
import com.wisdomleaf.codingtest.Model.ImagesData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

   /* @POST("Login/Checklogin")
    Call<LoginResponse> loadLogin(@Body JsonObject v);
	*/
	@GET("list?page=2&limit=20")
	Call<List<ImagesData>> getData();
	
}

package com.wisdomleaf.codingtest.Network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
	
    private static final String BASE_URL = "https://picsum.photos/v2/";
	
    private static ApiInterface REST_CLIENT;

    static {
        setupRestClient();
    }

    public static ApiInterface get() {
        return REST_CLIENT;
    }

    private static void setupRestClient() {

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
	    Retrofit retrofit = new Retrofit.Builder()
		    .baseUrl(BASE_URL)
		    .client(getHttpClient())
		    .addConverterFactory(GsonConverterFactory.create(gson))
		    .build();
	  
        REST_CLIENT = retrofit.create(ApiInterface.class);
    }
	
	
	private static OkHttpClient getHttpClient() {
		
		OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
			.connectTimeout(30,TimeUnit.SECONDS)
			.writeTimeout(30,TimeUnit.SECONDS)
			.readTimeout(60,TimeUnit.SECONDS);
		
			HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
			logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
			
			httpClient.addInterceptor(logInterceptor);
		
		
		return httpClient.build();
	}
	
	
	
}



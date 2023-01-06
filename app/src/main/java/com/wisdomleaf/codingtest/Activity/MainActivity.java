package com.wisdomleaf.codingtest.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wisdomleaf.codingtest.Adapter.ImagesAdapter;
import com.wisdomleaf.codingtest.Model.ImagesData;
import com.wisdomleaf.codingtest.Network.ApiClient;
import com.wisdomleaf.codingtest.Network.ApiInterface;
import com.wisdomleaf.codingtest.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
	private RecyclerView rv_images;
	List<ImagesData> imagesList = new ArrayList<ImagesData>();
	ApiInterface apiInterface;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		apiInterface = ApiClient.get();
		
		init();
	}
	
	/**
	 Description : Initialize Views
	 */
	private void init()
	{
		rv_images = findViewById(R.id.rv_images);
		getData();
	}
	
	/**
	 Description : Get Images data from Server
	 */
	private void getData()
	{
		final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
		progressDialog.setIndeterminate(true);
		progressDialog.setMessage("Please Wait ...");
		progressDialog.show();
		
		Call<List<ImagesData>> call = apiInterface.getData();
		call.enqueue(new Callback<List<ImagesData>>()
		{
			@Override
			public void onResponse(Call<List<ImagesData>> call,Response<List<ImagesData>> response)
			{
				
				imagesList.clear();
				try
				{
					
					if(response.isSuccessful())
					{
						imagesList = response.body();
						if(imagesList != null && imagesList.size() > 0)
						{
							setUpList(imagesList);
						}
						
					}
					else
					{
						Toast.makeText(MainActivity.this, getString(R.string.no_data), Toast.LENGTH_SHORT).show();
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				progressDialog.dismiss();
			}
			
			@Override
			public void onFailure(Call<List<ImagesData>> call,Throwable t)
			{
				progressDialog.dismiss();
			}
		});
	}
	
	/**
	 Description : Set Images data to Adapter
	 @param imagesList
	 */
	private void setUpList(List<ImagesData> imagesList)
	{
		ImagesAdapter mAdapter = new ImagesAdapter(MainActivity.this,(ArrayList<ImagesData>)imagesList);
		RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
		rv_images.setLayoutManager(mLayoutManager);
		rv_images.setItemAnimator(new DefaultItemAnimator());
		rv_images.setAdapter(mAdapter);
		mAdapter.notifyDataSetChanged();
	}
}
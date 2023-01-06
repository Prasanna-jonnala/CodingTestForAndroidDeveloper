package com.wisdomleaf.codingtest.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wisdomleaf.codingtest.Model.ImagesData;
import com.wisdomleaf.codingtest.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.MyViewHolder>
{
	private static final String TAG = ImagesAdapter.class.getSimpleName();
	private Context context;
	private List<ImagesData> mfiltered_list;
	private long mLastClickTime = 0;
	
	
	public ImagesAdapter(Context mActivity,ArrayList<ImagesData> resultList)
	{
		context = mActivity;
		mfiltered_list = resultList;
	}
	
	@NonNull
	@Override
	public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType)
	{
		View view = LayoutInflater.from(context).inflate(R.layout.item_list,parent,false);
		
		return new MyViewHolder(view);
	}
	
	@Override
	public void onBindViewHolder(@NonNull MyViewHolder holder,int position)
	{
		ImagesData ImagesData = mfiltered_list.get(position);
		
		String url = ImagesData.getDownloadUrl();
		String desp = context.getString(R.string.about_writer);
		holder.txt_title.setText(ImagesData.getAuthor());
		holder.txt_description.setText(desp);
		
		if((url != null))
		loadImage(url,holder.iv_img);
		
		holder.cl_img.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
					return;
				}
				mLastClickTime = SystemClock.elapsedRealtime();
				
				displayDescription();
				
			}
		});
	}
	
	/**
	 Description : It display the Description about the image in Dialog
	 */
	private void displayDescription()
	{
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
		alertDialog.setTitle(context.getString(R.string.desp));
		alertDialog.setMessage(context.getString(R.string.about_writer));
		alertDialog.setPositiveButton("Ok",new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialogInterface,int i)
			{
				dialogInterface.dismiss();
			}
		});
		AlertDialog dialog = alertDialog.create();
		dialog.show();
	}
	
	/**
	 Description : Load Image from URL by using Glide library
	 @param url
	 @param iv_img
	 */
	private void loadImage(String url,AppCompatImageView iv_img)
	{
		Glide.with(context)
		     .load(url)
		     .into(iv_img);
	}
	
	@Override
	public int getItemCount()
	{
		int size = 0;
		if(mfiltered_list != null && mfiltered_list.size() > 0)
		{
			size = mfiltered_list.size();
		}
		return size;
		
	}
	
	
	
	public class MyViewHolder extends RecyclerView.ViewHolder
	{
		public ConstraintLayout constraintLayout;
		TextView txt_title, txt_description;
		AppCompatImageView iv_img;
		ConstraintLayout cl_img;
		public MyViewHolder(View itemView)
		{
			super(itemView);
			
			txt_title = itemView.findViewById(R.id.txt_title);
			txt_description = itemView.findViewById(R.id.txt_description);
			iv_img = itemView.findViewById(R.id.iv_img);
			cl_img = itemView.findViewById(R.id.cl_img);
		}
	}
}






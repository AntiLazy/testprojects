package com.android.activity;


import java.util.ArrayList;

import com.example.mycamtest.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class PicturePages extends Activity{

	private ViewPager picturePages;
	private ArrayList<String> pictureList;
	private ArrayList<View> pictureViews;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pictures_viewpage);
		this.picturePages = (ViewPager)findViewById(R.id.viewpager);
		
		Intent intent = getIntent();
//	    this.picturePath = intent.getStringExtra("picturePath");
//	    this.position = intent.getIntExtra("position",0);
	    this.pictureList = (ArrayList<String>)intent.getStringArrayListExtra("pictures");
	    int position = intent.getIntExtra("position",0);
	    Log.d("zejia.ye", "item = "+position+"  picture.size = "+pictureList.size()+" pictureList[2] = "+pictureList.get(2));
	    
	    initViews();
	    
	    PicturePageAdapter adapter = new PicturePageAdapter(this);
	    this.picturePages.setAdapter(adapter);
	    this.picturePages.setCurrentItem(position);
		
	}
	/**
	 * 初始化显示的imageview
	 */
	public void initViews() {
		this.pictureViews = new ArrayList<View>();
		for(String picturePath:pictureList) {
			View view = LayoutInflater.from(this).inflate(R.layout.activity_picture, null);
			ImageView imageView = (ImageView)view.findViewById(R.id.pictureImageView);
			ImageButton imageButton = (ImageButton)findViewById(R.id.imageButton1);
			imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
			this.pictureViews.add(view);
		}
	}
	public class PicturePageAdapter extends PagerAdapter {
		
		Context context;
		
		public PicturePageAdapter(Context context) {
			this.context = context;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			container.addView(pictureViews.get(position));
			return pictureViews.get(position);
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return pictureList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView(pictureViews.get(position));
		}
		
	}
	
}

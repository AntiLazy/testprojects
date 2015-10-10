package com.example.test;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ImageView icon = new ImageView(this);
		icon.setImageResource(R.drawable.cankao);
		
//		ImageView icon2 = new ImageView(this);
//		icon2.setImageResource(R.drawable.shutter);
		FloatingActionButton actionButton = new FloatingActionButton.Builder(this).setContentView(icon).build();
		
//		FloatingActionButton actionButton2 = new FloatingActionButton.Builder(this).setContentView(icon2).build();
		
		SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
		
		ImageView itemIcon = new ImageView(this);
		itemIcon.setImageResource(R.drawable.blue);
		SubActionButton button1 = itemBuilder.setContentView(itemIcon).build();
		
		ImageView itemIcon2 = new ImageView(this);
		itemIcon2.setImageResource(R.drawable.tree);
		SubActionButton button2 = itemBuilder.setContentView(itemIcon2).build();
		
		ImageView itemIcon3 = new ImageView(this);
		itemIcon3.setImageResource(R.drawable.girl);
		SubActionButton button3 = itemBuilder.setContentView(itemIcon3).build();
		
		ImageView itemIcon4 = new ImageView(this);
		itemIcon4.setImageResource(R.drawable.yun);
		SubActionButton button4 = itemBuilder.setContentView(itemIcon4).build();
		
		ImageView itemIcon5 = new ImageView(this);
		itemIcon5.setImageResource(R.drawable.dog);
		SubActionButton button5 = itemBuilder.setContentView(itemIcon5).build();
	
		ImageView itemIcon6 = new ImageView(this);
		itemIcon6.setImageResource(R.drawable.ocean);
		SubActionButton button6 = itemBuilder.setContentView(itemIcon6).build();
		
		FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
																			.addSubActionView(button1)
																			.addSubActionView(button2)
																			.addSubActionView(button3)
																			.addSubActionView(button4)
																			.addSubActionView(button5)
																			.addSubActionView(button6)
																			.attachTo(actionButton)
																			.build();
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "Button1 is clicked!", 1).show();
				
			}
		});
//		FloatingActionMenu actionMenu2 = new FloatingActionMenu.Builder(this)
//		.addSubActionView(button4)
//		.addSubActionView(button5)
//		.addSubActionView(button6)
//		.attachTo(actionButton2)
//		.build();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

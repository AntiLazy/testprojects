package com.example.testpicstyle;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	HorizontalScrollView scrollView ;
	private static int COUNT = 4;
	Bitmap bitmap;
	ImageView imageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 imageView = (ImageView)this.findViewById(R.id.imageView1);
		 bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
		imageView.setImageBitmap(oldRemeber(bitmap, 2));
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.HORIZONTAL);
		Bitmap b =BitmapFactory.decodeResource(getResources(), R.drawable.comp);
		b= Bitmap.createScaledBitmap(b, 90, 160, false);
		for(int i = 0; i < COUNT;i++ ){
			 final int styleIndex = i;
			ImageView img = new ImageView(this);
			img.setImageBitmap(oldRemeber(b, i));
			img.setBackgroundColor(Color.RED);
//			img.setImageResource(R.drawable.dog);
			img.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					imageView.setImageBitmap(oldRemeber(bitmap, styleIndex));
				}
			});
			layout.addView(img);
		}
		scrollView = (HorizontalScrollView)this.findViewById(R.id.horizontalScrollView1);
		scrollView.addView(layout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	private Bitmap oldRemeber(Bitmap bmp,int style)
	{
		// 速度测试
		long start = System.currentTimeMillis();
		int width = bmp.getWidth();
		int height = bmp.getHeight();
		Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
		int pixColor = 0;
		int pixR = 0;
		int pixG = 0;
		int pixB = 0;
		int newR = 0;
		int newG = 0;
		int newB = 0;
		int[] pixels = new int[width * height];
		bmp.getPixels(pixels, 0, width, 0, 0, width, height);
		switch (style) {
		//老旧
		case 0:
			for (int i = 0; i < height; i++)
			{
				for (int k = 0; k < width; k++)
				{
					pixColor = pixels[width * i + k];
					pixR = Color.red(pixColor);
					pixG = Color.green(pixColor);
					pixB = Color.blue(pixColor);
					newR = (int) (0.393 * pixR + 0.769 * pixG + 0.189 * pixB);
					newG = (int) (0.349 * pixR + 0.686 * pixG + 0.168 * pixB);
					newB = (int) (0.272 * pixR + 0.534 * pixG + 0.131 * pixB);
					int newColor = Color.argb(255, newR > 255 ? 255 : newR, newG > 255 ? 255 : newG, newB > 255 ? 255 : newB);
					pixels[width * i + k] = newColor;
				}
			}
			break;
			//阴影
		case 1:
			for (int i = 0; i < height; i++)
			{
				for (int k = 0; k < width; k++)
				{
					pixColor = pixels[width * i + k];
					pixR = Color.red(pixColor);
					pixG = Color.green(pixColor);
					pixB = Color.blue(pixColor);
					newR = 255 - pixR;
					newG = 255 - pixG;
					newB = 255 - pixB;
					int newColor = Color.argb(255, newR > 255 ? 255 : newR, newG > 255 ? 255 : newG, newB > 255 ? 255 : newB);
					pixels[width * i + k] = newColor;
				}
			}
			break;
			//蓝色
		case 2:
			for (int i = 0; i < height; i++)
			{
				for (int k = 0; k < width; k++)
				{
					pixColor = pixels[width * i + k];
					pixR = Color.red(pixColor);
					pixG = Color.green(pixColor);
					pixB = Color.blue(pixColor);
					newR = getIcePix(pixR,pixG,pixB);
					newG = getIcePix(pixG,pixR,pixB);
					newB = getIcePix(pixB,pixR,pixG);
					int newColor = Color.argb(255, newR > 255 ? 255 : newR, newG > 255 ? 255 : newG, newB > 255 ? 255 : newB);
					pixels[width * i + k] = newColor;
				}
			}
			break;
		case 3:
			for (int i = 0; i < height; i++)
			{
				for (int k = 0; k < width; k++)
				{
					pixColor = pixels[width * i + k];
					pixR = Color.red(pixColor);
					pixG = Color.green(pixColor);
					pixB = Color.blue(pixColor);
					
					newR = pixG - pixB +pixG +pixR;
					if(newR<0) newR = -newR;
					newR = newR*pixR/256;
					if(newR>255) newR = 255;
					
					newG = pixB - pixG +pixB + pixR;
					if(newG<0) newG = -newG;
					newG = newG*pixR/256;
					if(newG>255) newG = 255;
					
					newB = pixB - pixG +pixB +pixR;
					if(newB<0) newB = -newB;
					newB = newB*pixG/256;
					if(newB>255) newB = 255;
					newB = getIcePix(pixB,pixR,pixG);
					int newColor = Color.argb(255, newR > 255 ? 255 : newR, newG > 255 ? 255 : newG, newB > 255 ? 255 : newB);
					pixels[width * i + k] = newColor;
				}
			}
			break;
		default:
			break;
		}
		
		
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		long end = System.currentTimeMillis();
		Log.d("may", "used time="+(end - start));
		return bitmap;
	}
	public int getIcePix(int s,int x1,int x2) {
		int pix = s - x1 - x2;
		pix = (pix * 3)>>1;
		if(pix < 0) pix = -pix;
		if(pix > 255) pix = 255;
		return pix;
	}
	/**
	 * 获取幻影pix
	 * @param s 主颜色pix
	 * @param x1 
	 * @param x2
	 * @return 
	 */
	public int getPhantom(int s,int x1,int x2) {
		int pix = Math.abs(s + x1 +x1 -x2)*s/256;
		return pix;
	}
}

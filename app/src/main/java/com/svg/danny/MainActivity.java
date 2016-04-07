package com.svg.danny;

import java.util.Random;

import com.svg.util.SvgToBitmap;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

@SuppressLint("NewApi")
public class MainActivity extends ActionBarActivity {
	private ImageView imageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imageView = (ImageView) findViewById(R.id.image);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void transSVG(View v){
		int [] resIds = new int[]{R.raw.shape_5,R
				.raw.shape_circle_2,R.raw.shape_flower,R.raw.shape_heart,R.raw.shape_star,R.raw.shape_star_2};
		int resId = resIds[new Random().nextInt(resIds.length)];
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test);
		Bitmap shapeBitmap = SvgToBitmap.getSvgShapeBitmap(this, bitmap, resId);
		imageView.setImageBitmap(shapeBitmap);
		bitmap.recycle();
	}
}

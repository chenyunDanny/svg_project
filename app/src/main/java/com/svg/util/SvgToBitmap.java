package com.svg.util;

import com.svgandroid.SVG;
import com.svgandroid.SVGParser;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;

public class SvgToBitmap {
	/**
	 * ����svg��ȡsvg��״ͼ
	 * @param context
	 * @param width
	 * @param height
	 * @param svgRawResourceId
	 * @return
	 */
	 public static Bitmap getBitmap(Context context, int width, int height, int svgRawResourceId) {
	        Bitmap bitmap = Bitmap.createBitmap(width, height,
	                Bitmap.Config.ARGB_8888);
	        Canvas canvas = new Canvas(bitmap);
	        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	        paint.setColor(Color.BLACK);

	        if (svgRawResourceId > 0) {
	            SVG svg = SVGParser.getSVGFromInputStream(
	                    context.getResources().openRawResource(svgRawResourceId), width, height);
	            canvas.drawPicture(svg.getPicture());
	        } else {
	            canvas.drawRect(new RectF(0.0f, 0.0f, width, height), paint);
	        }

	        return bitmap;
	    }
	 
	 
	 /**
		 * ����svg��״ͼid��ԭͼ�ϳ���״ͼ
		 * @param context
		 * @param fg
		 * @param rawId
		 * @return
		 */
		public static Bitmap getSvgShapeBitmap(Context context,Bitmap fg,int rawId){
			//��ȡͼƬ���������
			int size = Math.min(fg.getWidth(), fg.getHeight());
			//��ȡ�������ε�x��y����
			int x = (fg.getWidth()-size)/2;
			int y = (fg.getHeight()-size)/2;
			//��svgͼƬ���ųɸ������� 
			Bitmap bg = getBitmap(context, size, size, rawId);
			Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
			Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
			Canvas canvas = new Canvas(bitmap);
			canvas.drawBitmap(bg, new Matrix(), paint);//������svgͼ
			paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
			canvas.drawBitmap(Bitmap.createBitmap(fg, x, y, size, size), new Matrix(), paint);
			return bitmap;
		}
		
}

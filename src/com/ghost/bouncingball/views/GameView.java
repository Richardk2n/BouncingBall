package com.ghost.bouncingball.views;

import com.ghost.bouncingball.load.Preloader;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class GameView extends SurfaceView{

	private int width, height;
	private SurfaceHolder holder;
	
	public GameView(Context context) {
		super(context);
		Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		width = size.x;
		height = size.y;
		holder = getHolder();
		holder.addCallback(new Callback() {
			
			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				draw();
			}
			
			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void draw(){
		Canvas canvas = holder.lockCanvas(null);
		canvas.drawBitmap(Bitmap.createScaledBitmap(Preloader.background, width, height, false), 0, 0, null);
		holder.unlockCanvasAndPost(canvas);
	}

}

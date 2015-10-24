package com.ghost.bouncingball.views;

import java.util.Timer;
import java.util.TimerTask;

import com.ghost.bouncingball.Menue;
import com.ghost.bouncingball.handler.PillarHandler;
import com.ghost.bouncingball.handler.SQLite;
import com.ghost.bouncingball.load.Preloader;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class GameView extends SurfaceView {

	private int width, height;
	private SurfaceHolder holder;
	private PillarHandler handler;
	private Timer timer;
	private Context context;

	public GameView(Context context) {
		super(context);
		this.context = context;
		Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		width = size.x;
		height = size.y;
		handler = new PillarHandler(context);
		holder = getHolder();
		holder.addCallback(new Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				timer.cancel();
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				tick();
				timer.scheduleAtFixedRate(new TimerTask() {

					@Override
					public void run() {
						tick();
					}
				}, 500, 100);
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
				// I don't care
			}
		});
		timer = new Timer(true);
	}

	private void tick() {
		Canvas canvas = holder.lockCanvas(null);
		canvas.drawBitmap(Bitmap.createScaledBitmap(Preloader.background, width, height, false), 0, 0, null);
		boolean lost = handler.tick(canvas);
		canvas.drawBitmap(Bitmap.createScaledBitmap(Preloader.exit, width / 10, height / 10, false), width - width / 10, 0, null);
		Paint paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setTextSize((int) (height * 0.05));
		canvas.drawText(String.valueOf(handler.getScore()), 0, (int) (height * 0.05), paint);
		holder.unlockCanvasAndPost(canvas);
		if (lost) {
			timer.cancel();
			SQLite sql = new SQLite(context);
			int highscore = Integer.parseInt(sql.getMyHighscore());
			String text = new StringBuilder("Game over, your score is: ").append(handler.getScore()).toString();
			if (handler.getScore() > highscore) {
				text = new StringBuilder(text).append("\nNew Highscore!").toString();
				sql.updateMyHighscore(String.valueOf(handler.getScore()), sql.getMyHighscore());
			}
			final String output = text;
			((Activity) context).runOnUiThread(new Runnable() {

				@Override
				public void run() {
					AlertDialog.Builder builder = new AlertDialog.Builder(context);
					builder.setMessage(output).setTitle("Game over").setNeutralButton("OK", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							context.startActivity(new Intent(context, Menue.class));
						}
					}).create().show();
				}
			});
		}
	}

	@Override
	public boolean performClick() {
		handler.hop();
		return super.performClick();
	}

}

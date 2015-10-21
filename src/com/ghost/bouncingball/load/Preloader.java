package com.ghost.bouncingball.load;

import com.ghost.bouncingball.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Preloader {

	public static Bitmap background, ball, exit, pillar;

	public static void load(Context context) {
		background = BitmapFactory.decodeResource(context.getResources(), R.drawable.background);
		ball = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball);
		exit = BitmapFactory.decodeResource(context.getResources(), R.drawable.exit);
		pillar = BitmapFactory.decodeResource(context.getResources(), R.drawable.pillar);
	}
}

package com.ghost.bouncingball.load;

import com.ghost.bouncingball.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Preloader {

	public static Bitmap background;

	public static void load(Context context) {
		background = BitmapFactory.decodeResource(context.getResources(), R.drawable.background);
	}
}

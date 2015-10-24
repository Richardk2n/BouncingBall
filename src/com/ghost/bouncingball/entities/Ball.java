package com.ghost.bouncingball.entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

public class Ball extends Entity {

	public Ball(Point anchorBottimLef, Point anchorTopRight, int velocityX, int velocityY, Bitmap img, int width, int height) {
		super(anchorBottimLef, anchorTopRight, velocityX, velocityY, img, width, height);
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(Bitmap.createScaledBitmap(img, anchorTopRight.x - anchorBottomLeft.x, anchorBottomLeft.y - anchorTopRight.y, false), anchorBottomLeft.x,
				anchorTopRight.y, null);
	}

}

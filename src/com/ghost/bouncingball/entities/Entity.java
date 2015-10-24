package com.ghost.bouncingball.entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

public abstract class Entity {

	protected Point anchorBottomLeft, anchorTopRight;
	protected int velocityX, velocityY, screenWidth, screenHeight;
	protected Bitmap img;
	
	public Entity(Point anchorBottimLef, Point anchorTopRight, int velocityX, int velocityY, Bitmap img, int width, int height){
		this.anchorBottomLeft = anchorBottimLef;
		this.anchorTopRight = anchorTopRight;
		this.velocityX = velocityX;
		this.velocityY = velocityY;
		this.img = img;
		screenWidth = width;
		screenHeight = height;
	}
	
	public void accelerateX(int a){
		velocityX += a;
	}
	
	public void accelerateY(int a){
		velocityY += a;
	}
	
	public boolean performMove(){
		anchorBottomLeft.set(anchorBottomLeft.x+velocityX, anchorBottomLeft.y+velocityY);
		anchorTopRight.set(anchorTopRight.x+velocityX, anchorTopRight.y+velocityY);
		return !(anchorBottomLeft.x>screenWidth || anchorBottomLeft.y<0 ||anchorTopRight.x<0||anchorTopRight.y>screenHeight);
	}
	
	public Point getAnchorBottomLeft(){
		return anchorBottomLeft;
	}
	
	public Point getAnchorTopRight(){
		return anchorTopRight;
	}
	
	public abstract void draw(Canvas canvas);
}

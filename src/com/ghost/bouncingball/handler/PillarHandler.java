package com.ghost.bouncingball.handler;

import java.util.ArrayList;

import com.ghost.bouncingball.entities.Ball;
import com.ghost.bouncingball.entities.Pillar;
import com.ghost.bouncingball.load.Preloader;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.Display;

public class PillarHandler {

	private ArrayList<Pillar> pillars = new ArrayList<Pillar>();
	private Ball ball;
	private int score = 0, width, height, ticksSinceLastPillar;

	public PillarHandler(Context context) {
		Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		width = size.x;
		height = size.y;
		ball = new Ball(new Point((int) (0.32 * width), (int) (0.51 * height)), new Point((int) (0.34 * width), (int) (0.49 * height)), 0, 0, Preloader.ball, width,
				height);
	}

	public void hop() {
		ball.accelerateY(-10);
	}

	public boolean tick(Canvas canvas) {
		boolean lost = false;
		ArrayList<Pillar> toDelete = new ArrayList<Pillar>();
		for (Pillar pillar : pillars) {
			if (!pillar.performMove()) {
				toDelete.add(pillar);
			}
		}
		for (Pillar pillar : toDelete) {
			pillars.remove(pillar);
		}
		if (!ball.performMove() || checkColission()) {
			lost = true;
		} else {
			score++;
			addNewPillars();
		}
		ball.draw(canvas);
		for (Pillar pillar : pillars) {
			pillar.draw(canvas);
		}
		ball.accelerateY(2);
		return lost;
	}

	private void addNewPillars() {
		int last = ticksSinceLastPillar + score / 1000;
		if (last > 50) {
			int random = (int) (Math.random() * (100 / ticksSinceLastPillar));
			if (random < 1) {
				int gapTop = (int) (Math.random() * height * 0.7);
				pillars.add(new Pillar(new Point(width - 1, gapTop), new Point((int) (width * 1.05), 0), -10, 0, Preloader.pillar, width, height));
				pillars.add(new Pillar(new Point(width - 1, height), new Point((int) (width * 1.05), gapTop + (int) (0.1 * height)), -10, 0, Preloader.pillar, width,
						height));
				ticksSinceLastPillar = 0;
			} else {
				ticksSinceLastPillar++;
			}
		} else {
			ticksSinceLastPillar++;
		}

	}

	private boolean checkColission() {
		boolean collide = false;
		for(Pillar pillar: pillars){
			collide = isBallInside(pillar);
			if(collide){
				return collide;
			}
		}
		return collide;
	}

	private boolean isInside(Point point, Point bottomLeft, Point topRight) {
		return point.x >= bottomLeft.x && point.x <= topRight.x && point.y <= bottomLeft.y && point.y >= topRight.y;
	}

	private boolean isOneInside(Point objectPointBottomLeft, Point objectPointTopRight, Point bottomLeft, Point topRight) {
		return isInside(objectPointTopRight, bottomLeft, topRight) || isInside(objectPointBottomLeft, bottomLeft, topRight)
				|| isInside(new Point(objectPointBottomLeft.x, objectPointTopRight.y), bottomLeft, topRight)
				|| isInside(new Point(objectPointTopRight.x, objectPointBottomLeft.y), bottomLeft, topRight);
	}
	
	private boolean isBallInside(Pillar pillar){
		return isOneInside(ball.getAnchorBottomLeft(), ball.getAnchorTopRight(), pillar.getAnchorBottomLeft(), pillar.getAnchorTopRight());
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}

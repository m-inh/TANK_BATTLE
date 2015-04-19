package com.t3h.bullet;

import java.awt.Graphics2D;
import java.awt.Image;

public class Bullet {
	private CommonsBullet commons;
	private int x, y;
	private Image img;
	private int type;
	private int strong;
	private int speed;
	private int orient;
	private int size;
	
	public Bullet(int x, int y, int type, int strong, int speed, int orient) {
		commons = new CommonsBullet();
		this.x = x;
		this.y = y;
		this.type = type;
		this.orient = orient;
		this.speed = speed;
		this.strong = strong;
		this.img = CommonsBullet.BULLET;
		this.size = CommonsBullet.SIZE_BULLET;
	}
	
	public void drawBullet(Graphics2D g2d){
		g2d.drawImage(img, x, y, size, size, null);
	}
	
	public void move(){
		switch (orient) {
		case CommonsBullet.UP_ORIENT:
			y-= CommonsBullet.BULLET_SPEED;
			break;
		case CommonsBullet.DOWN_ORIENT:
			y+= CommonsBullet.BULLET_SPEED;
			break;
		case CommonsBullet.LEFT_ORIENT:
			x-= CommonsBullet.BULLET_SPEED;
			break;
		case CommonsBullet.RIGHT_ORIENT:
			x+= CommonsBullet.BULLET_SPEED;
			break;
		default:
			break;
		}
	}
	
	public void setStrong(int strong) {
		this.strong = strong;
		switch (strong) {
		case 1:
			size = (int)(CommonsBullet.SIZE_BULLET * Math.pow(1.2, strong));
			break;
		case 2:
			size = (int)(CommonsBullet.SIZE_BULLET * Math.pow(1.1, strong));
			break;
		case 3:
			size = (int)(CommonsBullet.SIZE_BULLET * Math.pow(1.1, strong));
			break;
		case 4:
			size = (int)(CommonsBullet.SIZE_BULLET * Math.pow(1.1, strong));
			break;
		case 5:
			size = (int)(CommonsBullet.SIZE_BULLET * Math.pow(1.1, strong));
			break;
		default:
			break;
		}
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getX() {
		return x+size/2;
	}
	
	public int getY() {
		return y+size/2;
	}
	
	public int getType() {
		return type;
	}
	
	public int getStrong() {
		return strong;
	}
}

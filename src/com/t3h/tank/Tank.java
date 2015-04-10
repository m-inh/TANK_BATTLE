package com.t3h.tank;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import com.t3h.boom.BoomManager;
import com.t3h.bullet.BulletManager;

public abstract class Tank {
	private int x, y;
	protected Commons commons;
	private int orient;
	private int speed;
	private int health;
	
	protected Image leftImg;
	protected Image rightImg;
	protected Image UpImg;
	protected Image downImg;
	
	
	protected BulletManager bulletMgr;
	protected BoomManager boomMgr;
	
	public Tank(int x, int y, int orient, int speed) {
		commons = new Commons();
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.orient = orient;
		this.health = 25;
	}
	
	abstract public void setImage();
	
	public void drawTank(Graphics2D g2d){
		switch (orient) {
		case 1:
			g2d.drawImage(UpImg, x, y, null);
			break;
		case 2:
			g2d.drawImage(downImg, x, y, null);
			break;
		case 3:
			g2d.drawImage(leftImg, x, y, null);
			break;
		case 4:
			g2d.drawImage(rightImg, x, y, null);
			break;
		default:
			break;
		}
	}
	
	public void move(int orient){
		switch (orient) {
		case 1:
			this.y--;
			break;
		case 2:
			this.y++;
			break;
		case 3:
			this.x--;
			break;
		case 4:
			this.x++;
			break;
		default:
			break;
		}
		
	}
	
	protected Image getImage(String path){
		return new ImageIcon(getClass().getResource(path)).getImage();
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
}

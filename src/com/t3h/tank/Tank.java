package com.t3h.tank;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.t3h.boom.BoomManager;
import com.t3h.bullet.BulletManager;
import com.t3h.graphics.Map;
import com.t3h.graphics.Sound;

public abstract class Tank {
	public static Sound sound = new Sound();//--------------------------------------------------
	protected int x, y;
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
	protected Map map;
	
	private boolean allowMoveUp = true;
	private boolean allowMoveDown = true;
	private boolean allowMoveLeft = true;
	private boolean allowMoveRight = true;

	
	public Tank(int x, int y, int orient, int speed) {
		commons = new Commons();
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.orient = orient;
		this.health = 25;
	}
	
	public void setMap(Map map){
		this.map = map;
	}
	
	abstract public void setImage();
	
	public void drawTank(Graphics2D g2d){
		switch (orient) {
		case Commons.UP:
			g2d.drawImage(UpImg, x, y, null);
			break;
		case Commons.DOWN:
			g2d.drawImage(downImg, x, y, null);
			break;
		case Commons.LEFT:
			g2d.drawImage(leftImg, x, y, null);
			break;
		case Commons.RIGHT:
			g2d.drawImage(rightImg, x, y, null);
			break;
		default:
			JOptionPane.showMessageDialog(null, "Orient not found!");
			break;
		}
	}
	
	public void move(int new_orient){
		if (checkMove(new_orient)){
			switch (new_orient) {
			case Commons.UP:
				this.y-=2;
				break;
			case Commons.DOWN:
				this.y+=2;
				break;
			case Commons.LEFT:
				this.x-=2;
				break;
			case Commons.RIGHT:
				this.x+=2;
				break;
			default:
				break;
			}
		}
		this.orient = new_orient;
	}
	
	private boolean checkMove(int new_orient){
		int type = 0;
		switch (new_orient) {
		case Commons.UP:
			if (!allowMoveUp) {
				return false;
			}
			for (int i = 0; i < 32; i+=com.t3h.graphics.Commons.SIZE_COMPONENT) {
				type = map.getType(x+i, y-2);	
				if (type != com.t3h.graphics.Commons.NONE && type!=com.t3h.graphics.Commons.TREE){
					return false;
				}
			}
			break;
		case Commons.DOWN:
			if (!allowMoveDown) {//--------------------------------
				return false;
			}
			for (int i = 0; i < 32; i+=com.t3h.graphics.Commons.SIZE_COMPONENT) {
				type = map.getType(x + i, y + 2 + 32);
				if (type != com.t3h.graphics.Commons.NONE && type!=com.t3h.graphics.Commons.TREE){
					return false;
				}
			}
			break;
		case Commons.LEFT:
			if (!allowMoveLeft) {//--------------------------------
				return false;
			}
			for (int i = 0; i < 32; i+=com.t3h.graphics.Commons.SIZE_COMPONENT) {
				type = map.getType(x - 2, y + i);
				if (type != com.t3h.graphics.Commons.NONE && type!=com.t3h.graphics.Commons.TREE){
					return false;
				}
			}
			break;
		case Commons.RIGHT:
			if (!allowMoveRight) {//--------------------------------
				return false;
			}
			for (int i = 0; i < 32; i+=com.t3h.graphics.Commons.SIZE_COMPONENT) {
				type = map.getType(x + 2 + 32, y + i);
				if (type != com.t3h.graphics.Commons.NONE && type!=com.t3h.graphics.Commons.TREE){
					return false;
				}
			}
			break;
		default:
			break;
		}
		return true;
	}
	
	public static final int space = 3;
	public void checkUp(Tank tank){//----------------------------------------------
		if (this.y-space<=tank.getY()+32 && (y+32>tank.getY() )&& this.y+space>=tank.getY()+32 &&  tank.getX()+space>=this.x-32 && tank.getX()-space<=this.x+32){
			allowMoveUp = false;
		}
	}
	public void checkDown(Tank tank){//----------------------------------------------
		if (this.y+space+32>=tank.getY() &&(y<tank.getY()+32 )&& this.y-space+32<=tank.getY() &&  tank.getX()+space>=this.x-32 && tank.getX()-space<=this.x+32){
			allowMoveDown = false;
		}
	}
	public void checkLeft(Tank tank){//----------------------------------------------
		if (this.x-space<=tank.getX()+32 && this.x+space>=tank.getX()+32 &&  tank.getY()+space>=this.y-32 && tank.getY()-space<=this.y+32) allowMoveLeft = false;
	}
	public void checkRight(Tank tank){//----------------------------------------------
		if (this.x+space+32>=tank.getX() && this.x-space+32<=tank.getX() &&  tank.getY()+space>=this.y-32 && tank.getY()-space<=this.y+32) allowMoveRight = false;
	}
	public void resetImpact(){
		allowMoveUp = allowMoveLeft = allowMoveDown = allowMoveRight = true;
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
	
	public void setOrient(int orient) {
		this.orient = orient;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public int getHealth() {
		return health;
	}
	public int getOrient() {
		return orient;
	}
}

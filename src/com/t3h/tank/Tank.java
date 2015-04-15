package com.t3h.tank;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.t3h.boom.BoomManager;
import com.t3h.bullet.BulletManager;
import com.t3h.graphics.Map;

public abstract class Tank {
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
	
	protected boolean allowMoveUp = true;
	protected boolean allowMoveDown = true;
	protected boolean allowMoveLeft = true;
	protected boolean allowMoveRight = true;
	
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
			JOptionPane.showMessageDialog(null, "Orient not found!");
			break;
		}
	}
	
	public void move(int new_orient){
		if (checkMove(new_orient)){
			switch (new_orient) {
			case 1:
				this.y-=2;
				break;
			case 2:
				this.y+=2;
				break;
			case 3:
				this.x-=2;
				break;
			case 4:
				this.x+=2;
				break;
			default:
				break;
			}
		}
		this.orient = new_orient;
	}
	
	private boolean checkMove(int new_orient){
		int type1 = 0;
		int type2 = 0;
		switch (new_orient) {
		case 1:
			if (!allowMoveUp) {//--------------------------------
				return false;
			}
			type1 = map.getType(x, y-2);
			type2 = map.getType(x+32, y-2);
//			for (int i = 0; i < 32; i+=sizeComponent) {
//				type2 = map.getType(x+i, y-2);	
//				if (type2 != 0){
//					return false;
//				}
//			}
			break;
		case 2:
			if (!allowMoveDown) {//--------------------------------
				return false;
			}
			type1 = map.getType(x, y + 2 + 32);
			type2 = map.getType(x + 32, y + 2 + 32);
//			for (int i = 0; i < 32; i++) {
//				type2 = map.getType(x + i, y + 2 + 32);
//				if (type2 != 0){
//					return false;
//				}
//			}
			break;
		case 3:
			if (!allowMoveLeft) {//--------------------------------
				return false;
			}
			type1 = map.getType(x - 2, y);
			type2 = map.getType(x - 2, y + 32);
//			for (int i = 0; i < 32; i++) {
//				type2 = map.getType(x - 2, y + i);
//				if (type2 != 0){
//					return false;
//				}
//			}
			break;
		case 4:
			if (!allowMoveRight) {//--------------------------------
				return false;
			}
			type1 = map.getType(x + 2 + 32, y);
			type2 = map.getType(x + 2 + 32, y + 32);
//			for (int i = 0; i < 32; i++) {
//				type2 = map.getType(x + 2 + 32, y + i);
//				if (type2 != 0){
//					return false;
//				}
//			}
			break;
		default:
			break;
		}
		if (type1 == 0 && type2 == 0){
//		if (type2 == 0){
//			System.out.println(x + " " + y);
			return true;
		}			
		return false;
	}
	
	public static final int space = 5;
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

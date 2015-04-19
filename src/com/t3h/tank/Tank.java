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
	protected CommonsTank commons;
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
		commons = new CommonsTank();
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.orient = orient;
		this.health = CommonsTank.HEALTH_TANK;
	}
	
	public void setMap(Map map){
		this.map = map;
	}
	
	abstract public void setImage();
	
	public void drawTank(Graphics2D g2d){
		switch (orient) {
			case CommonsTank.UP:{
				g2d.drawImage(UpImg, x, y, CommonsTank.SIZE, CommonsTank.SIZE, null);
				break;
			}
			case CommonsTank.DOWN:{
				g2d.drawImage(downImg, x, y, CommonsTank.SIZE, CommonsTank.SIZE, null);
				break;
			}
			case CommonsTank.LEFT:{
				g2d.drawImage(leftImg, x, y, CommonsTank.SIZE, CommonsTank.SIZE, null);
				break;
			}
			case CommonsTank.RIGHT:{
				g2d.drawImage(rightImg, x, y, CommonsTank.SIZE, CommonsTank.SIZE, null);
				break;
			}
			default:{
				JOptionPane.showMessageDialog(null, "Orient not found!");
				break;
			}
		}
		drawHealth(g2d, x, y-5);
	}
	abstract protected void drawHealth(Graphics2D g2d, int x, int y);
	
	public void move(int new_orient){
		if (checkMove(new_orient)){
			switch (new_orient) {
			case CommonsTank.UP:
				this.y-=2;
				break;
			case CommonsTank.DOWN:
				this.y+=2;
				break;
			case CommonsTank.LEFT:
				this.x-=2;
				break;
			case CommonsTank.RIGHT:
				this.x+=2;
				break;
			default:
				break;
			}
		}
		this.orient = new_orient;
	}
	
	protected boolean checkMove(int new_orient){
		int type = 0;
		switch (new_orient) {
			case CommonsTank.UP:
				if (!allowMoveUp) {
					return false;
				}
				for (int i = 0; i < CommonsTank.SIZE; i++) {
					type = map.getType(x+i, y-2);	
					if (type != com.t3h.graphics.Commons.NONE && type!=com.t3h.graphics.Commons.TREE){
						return false;
					}
				}
				break;
			case CommonsTank.DOWN:
				if (!allowMoveDown) {
					return false;
				}
				for (int i = 0; i < CommonsTank.SIZE; i++) {
					type = map.getType(x + i, y + 2 + CommonsTank.SIZE);
					if (type != com.t3h.graphics.Commons.NONE && type!=com.t3h.graphics.Commons.TREE){
						return false;
					}
				}
				break;
			case CommonsTank.LEFT:
				if (!allowMoveLeft) {
					return false;
				}
				for (int i = 0; i < CommonsTank.SIZE; i++) {
					type = map.getType(x - 2, y + i);
					if (type != com.t3h.graphics.Commons.NONE && type!=com.t3h.graphics.Commons.TREE){
						return false;
					}
				}
				break;
			case CommonsTank.RIGHT:
				if (!allowMoveRight) {
					return false;
				}
				for (int i = 0; i < CommonsTank.SIZE; i++) {
					type = map.getType(x + 2 + CommonsTank.SIZE, y + i);
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
		if (this.y-space<=tank.getY()+CommonsTank.SIZE && (y+CommonsTank.SIZE>tank.getY() )&& this.y+space>=tank.getY()+CommonsTank.SIZE &&  tank.getX()+space>=this.x-CommonsTank.SIZE && tank.getX()-space<=this.x+CommonsTank.SIZE){
			allowMoveUp = false;
		}
	}
	public void checkDown(Tank tank){//----------------------------------------------
		if (this.y+space+CommonsTank.SIZE>=tank.getY() &&(y<tank.getY()+CommonsTank.SIZE )&& this.y-space+CommonsTank.SIZE<=tank.getY() &&  tank.getX()+space>=this.x-CommonsTank.SIZE && tank.getX()-space<=this.x+CommonsTank.SIZE){
			allowMoveDown = false;
		}
	}
	public void checkLeft(Tank tank){//----------------------------------------------
		if (this.x-space<=tank.getX()+CommonsTank.SIZE && this.x+space>=tank.getX()+CommonsTank.SIZE &&  tank.getY()+space>=this.y-CommonsTank.SIZE && tank.getY()-space<=this.y+CommonsTank.SIZE) allowMoveLeft = false;
	}
	public void checkRight(Tank tank){//----------------------------------------------
		if (this.x+space+CommonsTank.SIZE>=tank.getX() && this.x-space+CommonsTank.SIZE<=tank.getX() &&  tank.getY()+space>=this.y-CommonsTank.SIZE && tank.getY()-space<=this.y+CommonsTank.SIZE) allowMoveRight = false;
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

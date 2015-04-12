package com.t3h.bullet;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class BulletManager {
	private ArrayList<Bullet> bulletMgr;
	
	public BulletManager() {
		bulletMgr = new ArrayList<>();
	}
	
	public void addBullet(Bullet bullet){
		bulletMgr.add(bullet);
	}
	
	public void moveAllBullet(){
		for (int i = 0; i < bulletMgr.size(); i++) {
			bulletMgr.get(i).move();
		}
	}
	
	public void drawAllBullet(Graphics2D g2d){
		for (int i = 0; i < bulletMgr.size(); i++) {
			bulletMgr.get(i).move();
			bulletMgr.get(i).drawBullet(g2d);
			
			if (checkBullet(bulletMgr.get(i))){
				removeBullet(i);
			}
		}
	}
	
	private boolean checkBullet(Bullet bullet){
		if (bullet.getX() < 0 || bullet.getX() > 700 || bullet.getY() < 0 || bullet.getY() > 700 ){
			return true;
		}
		return false;
	}
	
	public void removeBullet(int i){
		bulletMgr.remove(i);
	}
	
	public Bullet getBullet(int i){
		return bulletMgr.get(i);
	}
	
	public int getSize(){
		return bulletMgr.size();
	}
}



















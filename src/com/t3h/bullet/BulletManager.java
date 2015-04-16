package com.t3h.bullet;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.t3h.boom.Boom;
import com.t3h.boom.BoomManager;
import com.t3h.boom.Commons_Boom;
import com.t3h.graphics.Commons;
import com.t3h.graphics.Map;
import com.t3h.graphics.Sound;

public class BulletManager {
	private ArrayList<Bullet> bulletMgr;
	public static Sound sound = new Sound();//--------------------------------------------------
	private Map map;
	private BoomManager boomMgr;
	
	public BulletManager() {
		bulletMgr = new ArrayList<>();
	}
	
	public void setMap(Map map){
		this.map = map;
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
			bulletMgr.get(i).drawBullet(g2d);
			
			if (checkBullet(bulletMgr.get(i))){
				removeBullet(i);
			}
		}
	}
	
	// kiem tra dan da bi no chua
	private boolean checkBullet(Bullet bullet){
		if (bullet.getX()<0 || bullet.getX()>690){
			System.out.println("X");
			return true;
		}
		if (bullet.getY()<0 || bullet.getY()>690){
			System.out.println("Y");
			return true;
		}
		switch (map.getType(bullet.getX(), bullet.getY())) {
			case 0:
			case 5://--------------------------------------------------
				return false;
			case 1:{
				map.setMatrix(bullet.getX(), bullet.getY(), 3);
				break;
			}
			case 2:{
				break;
			}
			case 3:{
				map.setMatrix(bullet.getX(), bullet.getY(), 0);
				break;
			}
			default:{
				map.setMatrix(bullet.getX(), bullet.getY(), 0);
				break;
			}
		}
		return true;
	}
	
	public void setBoomMgr(BoomManager boomMgr) {
		this.boomMgr = boomMgr;
	}
	
	public void removeBullet(int i){
		Boom boom = new Boom(bulletMgr.get(i).getX(), bulletMgr.get(i).getY(), Commons_Boom.EXPLOSION_BULLET_TYPE);
		boomMgr.addBoom(boom);
		bulletMgr.remove(i);
		sound.playExplosion();//--------------------------------------------------
	}
	
	public Bullet getBullet(int i){
		return bulletMgr.get(i);
	}
	
	public int getSize(){
		return bulletMgr.size();
	}
}

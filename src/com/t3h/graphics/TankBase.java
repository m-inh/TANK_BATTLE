package com.t3h.graphics;

import java.awt.Graphics2D;
import java.awt.Image;

import com.t3h.boom.Boom;
import com.t3h.boom.BoomManager;
import com.t3h.boom.CommonsBoom;
import com.t3h.bullet.BulletManager;

public class TankBase {
	private Image base;
	private Image baseDead;
	private boolean alive;
	public Commons commons;
	public Sound sound = new Sound();;
	
	private BoomManager boomMgr;
	
	private int x;
	private int y;
	
	public TankBase(int x, int y, BoomManager boomMgr) {
		setImage();
		this.alive = true;
		this.x = x;
		this.y = y;
		this.boomMgr = boomMgr;
	}
	
	public void drawBase(Graphics2D g2d){
		if (alive){
			g2d.drawImage(base, x, y, (commons.sizeMap-4)*2, (commons.sizeMap-4)*2, null);
		} else{
			g2d.drawImage(baseDead, x, y, (commons.sizeMap-4)*2, (commons.sizeMap-4)*2, null);
		}
	}
	
	public boolean isDead(BulletManager bulletMgr){
		int bulletX = 0;
		int bulletY = 0;
		for (int i = 0; i < bulletMgr.getSize(); i++) {
			if (i>=bulletMgr.getSize()) continue;
			bulletX = bulletMgr.getBullet(i).getX();
			bulletY = bulletMgr.getBullet(i).getY();
			if (bulletX >= this.x && bulletX <= this.x+commons.sizeMap*2 && bulletY >= this.y && bulletY <= this.y+commons.sizeMap*2){
//				Boom boom = new Boom(bulletX, bulletY, CommonsBoom.EXPLOSION_TANK_TYPE);
//				boomMgr.addBoom(boom);
				sound.playExplosionTank();
				alive = false;
				return true;
			}
		}
//		alive = true;
		return false;
	}
	
	private void setImage(){
		base = commons.base;
		baseDead = commons.baseDead;
	}
}

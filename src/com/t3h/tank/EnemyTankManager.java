package com.t3h.tank;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.t3h.bullet.BulletManager;
import com.t3h.graphics.Map;

public class EnemyTankManager {
	private ArrayList<EnemyTank> enemyTankMgr;
	private Map map;
	
	public EnemyTankManager() {
		enemyTankMgr = new ArrayList<>();
	}
	
	public void setMap(Map map){
		this.map = map;
	}
	
	public void addEnemyTank(EnemyTank eTank){
		eTank.setMap(this.map);
		enemyTankMgr.add(eTank);
	}
	
	public void AutoControlAllTank(int count, BulletManager bulletMgr){
		for (int i = 0; i < enemyTankMgr.size(); i++) {
			enemyTankMgr.get(i).auto(count, bulletMgr);
		}
	}
	
	public void drawAllEnemyTank(Graphics2D g2d){
		for (int i = 0; i < enemyTankMgr.size(); i++) {
			enemyTankMgr.get(i).drawTank(g2d);
		}
	}
	
	public void checkAllEnemyTank(BulletManager bulletMgr){
		int bulletX = 0;
		int bulletY = 0;
		int tankX = 0;
		int tankY = 0;
		int bulletType = 0;
		for (int i = 0; i < bulletMgr.getSize(); i++) {
			bulletX = bulletMgr.getBullet(i).getX();
			bulletY = bulletMgr.getBullet(i).getY();
			for (int j = 0; j < enemyTankMgr.size(); j++) {
				tankX = enemyTankMgr.get(j).getX();
				tankY = enemyTankMgr.get(j).getY();
				bulletType = bulletMgr.getBullet(i).getType();
				if (bulletX >= tankX && bulletX <= tankX+32 && bulletY >= tankY && bulletY <= tankY+32 && bulletType == Commons.BULLET_TYPE_PLAYER){
					// neu vi tri cua dan == vi tri cua tank -> remove tank + remove bullet + boom
					enemyTankMgr.remove(j);
					bulletMgr.removeBullet(i);
					i--;
					if (i < 0) {break;}
					j--;
				}
			}
			if (i < 0) {break;}
		}
	}
	
	public int getSize(){
		return enemyTankMgr.size();
	}
}

















package com.t3h.tank;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.t3h.bullet.BulletManager;

public class EnemyTankManager {
	ArrayList<EnemyTank> enemyTankMgr;
	
	
	public EnemyTankManager() {
		enemyTankMgr = new ArrayList<>();
	}
	
	public void addEnemyTank(EnemyTank eTank){
		enemyTankMgr.add(eTank);
	}
	
	public void AutoControlAllTank(int count){
		for (int i = 0; i < enemyTankMgr.size(); i++) {
			enemyTankMgr.get(i).auto(count);
		}
	}
	
	public void drawAllEnemyTank(Graphics2D g2d){
		for (int i = 0; i < enemyTankMgr.size(); i++) {
			enemyTankMgr.get(i).drawTank(g2d);
		}
	}
	
	public void checkAllEnemyTank(BulletManager bulletMgr){
		int bulletMgrSize = bulletMgr.getSize();
		int bulletX = 0;
		int bulletY = 0;
		int tankX = 0;
		int tankY = 0;
		for (int i = 0; i < bulletMgrSize; i++) {
			bulletX = bulletMgr.getBullet(i).getX();
			bulletY = bulletMgr.getBullet(i).getY();
			for (int j = 0; j < enemyTankMgr.size(); j++) {
				tankX = enemyTankMgr.get(j).getX();
				tankY = enemyTankMgr.get(j).getY();
				if (bulletX >= tankX && bulletX <= tankX+32 && bulletY >= tankY && bulletY <= tankY+32){
					// neu vi tri cua dan == vi tri cua tank -> remove tank + remove bullet + boom
					enemyTankMgr.remove(j);
					bulletMgr.removeBullet(i);
					bulletMgrSize = bulletMgr.getSize();
					i--;
					if (i < 0) {break;}
					j--;
				}
				
			}
		}
	}
	
	public int getSize(){
		return enemyTankMgr.size();
	}
}

















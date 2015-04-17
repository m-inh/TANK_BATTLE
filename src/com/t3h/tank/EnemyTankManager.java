package com.t3h.tank;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.t3h.boom.Boom;
import com.t3h.boom.BoomManager;
import com.t3h.boom.Commons_Boom;
import com.t3h.bullet.BulletManager;
import com.t3h.graphics.Map;

public class EnemyTankManager {
	private ArrayList<EnemyTank> enemyTankMgr;
	private int tankDestroy;
	private int totalTank;
	private Map map;
	private BoomManager boomMgr;
	
	public EnemyTankManager() {
		enemyTankMgr = new ArrayList<>();
		tankDestroy = 0;
		totalTank = 0;
	}
	
	public void setMap(Map map){
		this.map = map;
	}
	
	public void addEnemyTank(EnemyTank eTank){
		eTank.setMap(this.map);
		enemyTankMgr.add(eTank);
		totalTank++;
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
			if (i>=bulletMgr.getSize()) continue;
			bulletX = bulletMgr.getBullet(i).getX();
			bulletY = bulletMgr.getBullet(i).getY();
			for (int j = 0; j < enemyTankMgr.size(); j++) {
				tankX = enemyTankMgr.get(j).getX();
				tankY = enemyTankMgr.get(j).getY();
				bulletType = bulletMgr.getBullet(i).getType();
				if (bulletX >= tankX && bulletX <= tankX+32 && bulletY >= tankY && bulletY <= tankY+32 && bulletType == Commons.BULLET_TYPE_PLAYER){
					// neu vi tri cua dan == vi tri cua tank -> remove tank + remove bullet + boom
					Boom boom = new Boom(tankX + 16, tankY + 16, Commons_Boom.EXPLOSION_TANK_TYPE);
					boomMgr.addBoom(boom);
					enemyTankMgr.remove(j);
					EnemyTank.sound.playExplosionTank();//--------------------------------------------------
					tankDestroy++;
					bulletMgr.removeBullet(i);
					i--;
					if (i < 0) {break;}
					j--;
				}
			}
			if (i < 0) {break;}
		}
	}
	
	public void checkImpact(Tank tank){
		for (int i = 0; i < enemyTankMgr.size(); i++) {
			enemyTankMgr.get(i).checkUp(tank);
			enemyTankMgr.get(i).checkDown(tank);
			enemyTankMgr.get(i).checkLeft(tank);
			enemyTankMgr.get(i).checkRight(tank);
			for (int j = 0; j < enemyTankMgr.size(); j++) {
				if (i!=j){	
					enemyTankMgr.get(i).checkUp(enemyTankMgr.get(j));
					enemyTankMgr.get(i).checkDown(enemyTankMgr.get(j));
					enemyTankMgr.get(i).checkLeft(enemyTankMgr.get(j));
					enemyTankMgr.get(i).checkRight(enemyTankMgr.get(j));
				}
			}
		}
	}
	public void resetImpact(){
		for (int i = 0; i < enemyTankMgr.size(); i++) {
			enemyTankMgr.get(i).resetImpact();
		}
	}
	public EnemyTank getEnemyTank(int index){
		return enemyTankMgr.get(index);
	}
	
	public void setBoomMgr(BoomManager boomMgr) {
		this.boomMgr = boomMgr;
	}
	
	public int getSize(){
		return enemyTankMgr.size();
	}
	public int getTankDestroy() {
		return tankDestroy;
	}
	public int getTotalTank() {
		return totalTank;
	}
}

















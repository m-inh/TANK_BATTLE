package com.t3h.tank;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.t3h.boom.Boom;
import com.t3h.boom.BoomManager;
import com.t3h.boom.CommonsBoom;
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
	
	public void AutoControlAllTank(int count, BulletManager bulletMgr, Tank tank){
		for (int i = 0; i < enemyTankMgr.size(); i++) {
			if (enemyTankMgr.get(i).autoCatch(tank)) {
				// Táº¡o má»™t playerTank áº£o Ä‘á»ƒ Ä‘á»“ng Ä‘á»™i cáº¡nh Ä‘Ã³ phÃ¡t hiá»‡n Ä‘á»ƒ tá»›i yá»ƒm trá»£
				Tank vitualPlayerTank = new Tank(enemyTankMgr.get(i).getX(), enemyTankMgr.get(i).getY(), 1, 1) {
					@Override
					public void setImage() {
					}					
					@Override
					protected void drawHealth(Graphics2D g2d, int x, int y) {
					}
				};
				// TÃ¬m táº¥t cáº£ nhá»¯ng Ä‘á»“ng Ä‘á»™i xung quanh, náº¿u gáº§n Ä‘Ã³ vÃ  phÃ¡t hiá»‡n ra tank áº£o thÃ¬ tá»›i yá»ƒm trá»£
				for (int j = 0; j < enemyTankMgr.size(); j++) {
					if (i!=j) {
						 enemyTankMgr.get(j).autoCatch(vitualPlayerTank);
					}
				}
			}
			else {		// Náº¿u khÃ´ng phÃ¡t hiá»‡n xe playerTank
				 enemyTankMgr.get(i).autoMove(count);		// Tá»± Ä‘á»™ng di chuyá»ƒn ngáº«u nhiÃªn
			}
			enemyTankMgr.get(i).autoFire(bulletMgr);	// Tá»± Ä‘á»™ng báº¯n
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
				if (bulletX >= tankX && bulletX <= tankX+CommonsTank.SIZE && bulletY >= tankY && bulletY <= tankY+CommonsTank.SIZE && bulletType == CommonsTank.BULLET_TYPE_PLAYER){
					// neu vi tri cua dan == vi tri cua tank -> remove tank + remove bullet + boom
					Boom boom = new Boom(tankX + CommonsTank.SIZE/2, tankY + CommonsTank.SIZE/2, CommonsBoom.EXPLOSION_TANK_TYPE);
					boomMgr.addBoom(boom);
					enemyTankMgr.get(j).setHealth(enemyTankMgr.get(j).getHealth()-1);	// Máº¥t mÃ¡u
					if (enemyTankMgr.get(j).getHealth()<=0)	{
						enemyTankMgr.remove(j);		// Náº¿u háº¿t mÃ¡u thÃ¬ ná»• Tank
						tankDestroy++;
					}
					EnemyTank.sound.playExplosionTank();
					bulletMgr.removeBullet(i);
					i--;
					if (i < 0) {break;}
					j--;
				}
			}
			if (i < 0) {break;}
		}
	}
	
	// Kiá»ƒm tra va cháº¡m vá»›i cÃ¡c Tank
	public void checkImpact(Tank tank){
		for (int i = 0; i < enemyTankMgr.size(); i++) {
			// Va cháº¡m vá»›i playerTank
			enemyTankMgr.get(i).checkUp(tank);
			enemyTankMgr.get(i).checkDown(tank);
			enemyTankMgr.get(i).checkLeft(tank);
			enemyTankMgr.get(i).checkRight(tank);
			for (int j = 0; j < enemyTankMgr.size(); j++) {
				if (i!=j){	
					// Va cháº¡m vá»›i tá»«ng enemyTank khÃ¡c
					enemyTankMgr.get(i).checkUp(enemyTankMgr.get(j));
					enemyTankMgr.get(i).checkDown(enemyTankMgr.get(j));
					enemyTankMgr.get(i).checkLeft(enemyTankMgr.get(j));
					enemyTankMgr.get(i).checkRight(enemyTankMgr.get(j));
				}
			}
		}
	}
	// Khi Ä‘Ã£ kiá»ƒm tra xong thÃ¬ reset, táº¥t cáº£ cÃ¡c hÆ°á»›ng Ä‘á»�u cÃ³ thá»ƒ Ä‘i Ä‘Æ°á»£c
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
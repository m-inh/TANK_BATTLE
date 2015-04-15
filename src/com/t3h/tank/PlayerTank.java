package com.t3h.tank;

import com.t3h.bullet.BulletManager;

public class PlayerTank extends Tank{

	public PlayerTank(int x, int y, int orient, int speed) {
		super(x, y, orient, speed);
		setHealth(commons.HEALTH_PLAYER_TANK);
		setImage();
	}

	@Override
	public void setImage() {
		UpImg = commons.UP_PLAYER_TANK;
		downImg = commons.DOWN_PLAYER_TANK;
		leftImg = commons.LEFT_PLAYER_TANK;
		rightImg = commons.RIGHT_PLAYER_TANK;
	}
	
	public boolean checkPlayerTank(BulletManager bulletMgr){
		int bulletEnemyX = 0;
		int bulletEnemyY = 0;
		int typeTankEnemy = 0;
		for (int i = 0; i < bulletMgr.getSize(); i++) {
			bulletEnemyX = bulletMgr.getBullet(i).getX();
			bulletEnemyY = bulletMgr.getBullet(i).getY();
			typeTankEnemy = bulletMgr.getBullet(i).getType();
			if ((getX() <= bulletEnemyX && getX()+32 >= bulletEnemyX) && (getY() <= bulletEnemyY && getY()+32 >= bulletEnemyY) && typeTankEnemy==Commons.BULLET_TYPE_ENEMY) {
				bulletMgr.removeBullet(i);
				return true;
			}
		}
		return false;
	}

}
      
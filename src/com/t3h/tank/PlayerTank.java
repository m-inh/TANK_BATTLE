package com.t3h.tank;

import java.awt.event.KeyEvent;

import com.t3h.bullet.BulletManager;

public class PlayerTank extends Tank{
	private boolean upPressed 		= false;
	private boolean downPressed 	= false;
	private boolean leftPressed 	= false;
	private boolean rightPressed 	= false;

	public PlayerTank(int x, int y, int orient, int speed) {
		super(x, y, orient, speed);
		setHealth(CommonsTank.HEALTH_PLAYER_TANK);
		setImage();
	}

	@Override
	public void setImage() {
		UpImg 		= CommonsTank.UP_PLAYER_TANK;
		downImg		= CommonsTank.DOWN_PLAYER_TANK;
		leftImg		= CommonsTank.LEFT_PLAYER_TANK;
		rightImg	= CommonsTank.RIGHT_PLAYER_TANK;
	}
	
	public boolean checkPlayerTank(BulletManager bulletMgr){
		int bulletEnemyX = 0;
		int bulletEnemyY = 0;
		int typeTankEnemy = 0;
		for (int i = 0; i < bulletMgr.getSize(); i++) {
			bulletEnemyX = bulletMgr.getBullet(i).getX();
			bulletEnemyY = bulletMgr.getBullet(i).getY();
			typeTankEnemy = bulletMgr.getBullet(i).getType();
			if ((getX() <= bulletEnemyX && getX()+32 >= bulletEnemyX) && (getY() <= bulletEnemyY && getY()+32 >= bulletEnemyY) && typeTankEnemy==CommonsTank.BULLET_TYPE_ENEMY) {
				bulletMgr.removeBullet(i);
				return true;
			}
		}
		return false;
	}
	
	public void checkImpact(EnemyTankManager enemyTankMgr){//---------------------------------------
		for (int i = 0; i < enemyTankMgr.getSize(); i++) {
			this.checkUp(enemyTankMgr.getEnemyTank(i));
			this.checkDown(enemyTankMgr.getEnemyTank(i));
			this.checkLeft(enemyTankMgr.getEnemyTank(i));
			this.checkRight(enemyTankMgr.getEnemyTank(i));
		}
	}
	
	public void releaseKey(KeyEvent e){
		switch(e.getKeyCode()){
			case KeyEvent.VK_RIGHT:{
				rightPressed = false;		break;
			}
			case KeyEvent.VK_LEFT:{
				leftPressed = false;		break;
			}
			case KeyEvent.VK_UP:{
				upPressed = false;			break;
			}
			case KeyEvent.VK_DOWN:{
				downPressed = false;		break;
			}
		}
	}
	
	public void pressKey(KeyEvent e){
		switch(e.getKeyCode()){
		case KeyEvent.VK_RIGHT:{
			rightPressed = true;		break;
		}
		case KeyEvent.VK_LEFT:{
			leftPressed = true;			break;
		}
		case KeyEvent.VK_UP:{
			upPressed = true;			break;
		}
		case KeyEvent.VK_DOWN:{
			downPressed = true;			break;
		}
		}
	}
	
	public void control(){
		if (upPressed){
			move(CommonsTank.UP);
		}
		if (downPressed){
			move(CommonsTank.DOWN);
		}
		if (leftPressed){
			move(CommonsTank.LEFT);
		}
		if (rightPressed){
			move(CommonsTank.RIGHT);
		}
	}

}
      
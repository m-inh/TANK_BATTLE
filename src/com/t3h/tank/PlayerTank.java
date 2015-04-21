package com.t3h.tank;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import com.t3h.boom.Boom;
import com.t3h.boom.BoomManager;
import com.t3h.boom.CommonsBoom;
import com.t3h.bullet.BulletManager;

public class PlayerTank extends Tank{
	
	private boolean upPressed 		= false;	//	Cac bien boolean nay dung de kiem tra xem
	private boolean downPressed 	= false;	// cac nut di chuyen co duoc nhan hay khong
	private boolean leftPressed 	= false;	// true: no se di chuyen lien tuc theo huong do
	private boolean rightPressed 	= false;	// false: no se dung lai
	
	private boolean lockKey;	// Vo hieu hoa keyEvent

	public PlayerTank(int x, int y, int orient, int speed) {
		super(x, y, orient, speed);
		setHealth(CommonsTank.HEALTH_PLAYER_TANK);
		setImage();
		lockKey = false;
	}

	@Override	// Lấy ảnh Tank
	public void setImage() {
		upImg 		= CommonsTank.UP_PLAYER_TANK;
		downImg		= CommonsTank.DOWN_PLAYER_TANK;
		leftImg		= CommonsTank.LEFT_PLAYER_TANK;
		rightImg	= CommonsTank.RIGHT_PLAYER_TANK;
	}
	@Override
	protected void drawHealth(Graphics2D g2d, int x, int y) {
		Image health;
		if (getHealth() <= 0) return;		// Het mau: Khong ve mau
		if (CommonsTank.HEALTH_PLAYER_TANK/getHealth() >= 2){
			health = CommonsTank.HEALTH_LOSE;
		}
		else health = CommonsTank.HEALTH_FULL;
		g2d.drawImage(health, x, y, CommonsTank.SIZE*getHealth()/CommonsTank.HEALTH_PLAYER_TANK, 4, null);
	}
	
	// Kiem tra xem co trung dan hay khong
	public boolean checkPlayerTank(BulletManager bulletMgr){
		int bulletEnemyX = 0;
		int bulletEnemyY = 0;
		int typeTankEnemy = 0;
		for (int i = 0; i < bulletMgr.getSize(); i++) {
			bulletEnemyX = bulletMgr.getBullet(i).getX();
			bulletEnemyY = bulletMgr.getBullet(i).getY();
			typeTankEnemy = bulletMgr.getBullet(i).getType();
			if ((getX() <= bulletEnemyX && getX()+CommonsTank.SIZE >= bulletEnemyX) && (getY() <= bulletEnemyY && getY()+CommonsTank.SIZE >= bulletEnemyY) && typeTankEnemy==CommonsTank.BULLET_TYPE_ENEMY) {
				bulletMgr.removeBullet(i);
				Tank.sound.playExplosionTank();
				Boom boom = new Boom(x + CommonsTank.SIZE/2, y + CommonsTank.SIZE/2, CommonsBoom.EXPLOSION_TANK_TYPE);
				boomMgr.addBoom(boom);
				setHealth(getHealth()-1);
				if (getHealth()<=0) return true;
			}
		}
		return false;
	}
	
	// Kiem tra va cham voi cac xe tang dich
	public void checkImpact(EnemyTankManager enemyTankMgr){
		for (int i = 0; i < enemyTankMgr.getSize(); i++) {
			checkUp(enemyTankMgr.getEnemyTank(i));
			checkDown(enemyTankMgr.getEnemyTank(i));
			checkLeft(enemyTankMgr.getEnemyTank(i));
			checkRight(enemyTankMgr.getEnemyTank(i));
		}
	}
	
	// Khi nhan nut thi Tank se di chuyen lien tuc theo huong da nhan
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
	
	// Khi nha nut ra thi dung di chuyen
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
		
	// Cho Tank chay theo huong ma upPressed, downPressed, leftPressed, rightPressed cho phep
	public void control(){
		if (lockKey) return;
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
	
	// Khoa KeyEvent
	public void lockKey(){
		lockKey = true;
	}
	// Mo khoa KeyEvent
	public void unlockKey(){
		lockKey = false;
	}
	public void setBoomMgr(BoomManager boomMgr) {
		this.boomMgr = boomMgr;
	}
} 
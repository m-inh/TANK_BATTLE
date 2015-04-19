package com.t3h.tank;

import java.awt.Graphics2D;
import java.util.Random;

import com.t3h.bullet.Bullet;
import com.t3h.bullet.BulletManager;

public class EnemyTank extends Tank{
	private Random random;

	public EnemyTank(int x, int y, int orient, int speed) {
		super(x, y, orient, speed);
		setHealth(CommonsTank.HEALTH_ENEMY_TANK);
		setImage();
		
		random = new Random();
	}

	@Override
	public void setImage() {
		UpImg 		= CommonsTank.UP_ENEMY_TANK;
		downImg		= CommonsTank.DOWN_ENEMY_TANK;
		leftImg		= CommonsTank.LEFT_ENEMY_TANK;
		rightImg	= CommonsTank.RIGHT_ENEMY_TANK;
	}
	@Override
	protected void drawHealth(Graphics2D g2d, int x, int y) {
		if (getHealth() == 2){
			g2d.drawImage(CommonsTank.HEALTH_FULL, x, y, CommonsTank.SIZE, 4, null);
		}
		else if (getHealth() == 1){
			g2d.drawImage(CommonsTank.HEALTH_LOSE, x, y, CommonsTank.SIZE/2, 4, null);
		}
	}
		
	public void autoMove(int count){
		if (count % 50 == 0){
			int newOrient = Math.abs(random.nextInt())%4 + 1;
			if (!checkMove(newOrient)) newOrient = (newOrient+1)%4 + 1;
			if (!checkMove(newOrient)) newOrient = (newOrient+1)%4 + 1;
			setOrient(newOrient);
		}
		move(getOrient());
	}
	
	public void autoFire(BulletManager bulletMgr){
		if (random.nextInt(100) > 95){
			Bullet bullet = new Bullet(this.x+13, this.y+13, 2, 1, 1, getOrient());
			bulletMgr.addBullet(bullet);
		}
	}
	
	public void checkPlayer(PlayerTank playerTank){
		
	}

	public boolean autoCatch(Tank tank){
		int quarter = CommonsTank.SIZE/4;
		int oldOrient = getOrient();
		if (tank.getX()>=x-quarter && tank.getX()<=x+quarter){
			if (tank.getY()<y && tank.getY()>y-CommonsTank.RADA){
				setOrient(CommonsTank.UP);
			}
			else if (tank.getY()>y && tank.getY()<y+CommonsTank.RADA){
				setOrient(CommonsTank.DOWN);
			}
		}
		else if (tank.getY()>=y-quarter && tank.getY()<=y+quarter){
			if (tank.getX()<x && tank.getX()>x-CommonsTank.RADA){
				setOrient(CommonsTank.LEFT);
			}
			else if (tank.getX()>x && tank.getX()<x+CommonsTank.RADA){
				setOrient(CommonsTank.RIGHT);
			}
		}
		if (oldOrient!=getOrient()){
			move(getOrient());
			return true;
		}
		return false;
	}
}

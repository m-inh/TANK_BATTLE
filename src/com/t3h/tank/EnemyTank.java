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
		upImg 		= CommonsTank.UP_ENEMY_TANK;
		downImg		= CommonsTank.DOWN_ENEMY_TANK;
		leftImg		= CommonsTank.LEFT_ENEMY_TANK;
		rightImg	= CommonsTank.RIGHT_ENEMY_TANK;
	}
	@Override
	protected void drawHealth(Graphics2D g2d, int x, int y) {
		if (getHealth() == 2){		// Full mau
			g2d.drawImage(CommonsTank.HEALTH_FULL, x, y, CommonsTank.SIZE, 4, null);
		}
		else if (getHealth() == 1){	// Mat mau
			g2d.drawImage(CommonsTank.HEALTH_LOSE, x, y, CommonsTank.SIZE/2, 4, null);
		}
	}
		
	public void autoMove(int count){
		if (count % 50 == 0){
			int newOrient = Math.abs(random.nextInt())%4 + 1;	// Khoi tao ngau nhien huong cho Tank
			if (!checkMove(newOrient)) newOrient = (newOrient+1)%4 + 1;	// Neu huong moi tao ma gap chuong ngai vat thi chuyen sang huong khac
			if (!checkMove(newOrient)) newOrient = (newOrient+1)%4 + 1;	// Neu huong moi tao ma gap chuong ngai vat thi chuyen sang huong khac
			setOrient(newOrient);
		}
		move(getOrient());
	}
	
	public void autoFire(BulletManager bulletMgr){
		if (random.nextInt(100) > 95){		// Ban ngau nhien: xac suat 5%
			Bullet bullet = new Bullet(this.x+13, this.y+13, 2, 1, 1, getOrient());
			bulletMgr.addBullet(bullet);
		}
	}

	// Tu dong kiem tra va duoi theo playerTank
	public boolean autoCatch(Tank tank){
		int quarter = CommonsTank.SIZE/4;
		boolean sos = false;	// Tin hieu bao dong
		if (tank.getX()>=x-quarter && tank.getX()<=x+quarter){
			if (tank.getY()<y && tank.getY()>y-CommonsTank.RADA){		// Kiem phia tren
				setOrient(CommonsTank.UP);		sos = true;					// Chuyen huong + bao dong
			}
			else if (tank.getY()>y && tank.getY()<y+CommonsTank.RADA){	// Kiem tra phia duoi
				setOrient(CommonsTank.DOWN);	sos = true;					// Chuyen huong + bao dong
			}
		}
		else if (tank.getY()>=y-quarter && tank.getY()<=y+quarter){
			if (tank.getX()<x && tank.getX()>x-CommonsTank.RADA){		// Kiem tra ben trai
				setOrient(CommonsTank.LEFT);	sos = true;					// Chuyen huong + bao dong
			}
			else if (tank.getX()>x && tank.getX()<x+CommonsTank.RADA){	// Kiem tra ben phai
				setOrient(CommonsTank.RIGHT);	sos = true;					// Chuyen huong + bao dong
			}
		}
		if (sos){	// Khi co bao dong: di chuyen theo huong vua chuyen
			move(getOrient());
			return true;	// Phat hien ra playerTank
		}
		return false;
	}
}

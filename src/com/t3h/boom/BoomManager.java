package com.t3h.boom;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class BoomManager {
	ArrayList<Boom> boomMgr;
	
	public BoomManager() {
		boomMgr = new ArrayList<>();
	}
	
	public void addBoom(Boom boom){
		boomMgr.add(boom);
	}
	
	public void exploredAllBoom(int count, Graphics2D g2d){
		for (int i = 0; i < boomMgr.size(); i++) {
			boomMgr.get(i).explored(count, g2d);
			if (boomMgr.get(i).time >= 15){
				boomMgr.remove(i);
			}
		}
	}
	
}

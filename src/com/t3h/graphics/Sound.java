package com.t3h.graphics;
import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	public static AudioClip ENTER_GAME;
	public static AudioClip SHOOT;
	public static AudioClip MOVE;
	public static AudioClip EXPLOSION;
	public static AudioClip EXPLOSION_TANK;
	public static AudioClip END_GAME;
	
	public Sound() {
		setSound(true);
	}
	public void setSound(boolean power) {
		// checkSound=flag;
		if (power == true) {
			ENTER_GAME = Applet.newAudioClip(Sound.class
					.getResource("/RESOURCE/sound/enter_game.wav"));
			SHOOT = Applet.newAudioClip(Sound.class
					.getResource("/RESOURCE/sound/shoot.wav"));
			MOVE = Applet.newAudioClip(Sound.class
					.getResource("/RESOURCE/sound/move.wav"));
			EXPLOSION = Applet.newAudioClip(Sound.class
					.getResource("/RESOURCE/sound/explosion.wav"));
			EXPLOSION_TANK = Applet.newAudioClip(Sound.class
					.getResource("/RESOURCE/sound/explosion_tank.wav"));
			END_GAME = Applet.newAudioClip(Sound.class
					.getResource("/RESOURCE/sound/endGame.wav")); 
	
		} else {
			ENTER_GAME = null;
			SHOOT = null;
			MOVE = null;
			EXPLOSION = null;
			EXPLOSION_TANK = null;
			END_GAME = null;
		}
	}

	public void playEnterGame(){
		if (ENTER_GAME!=null){
			ENTER_GAME.play();
		}
	}
	public void playShoot() {
		if (SHOOT != null) {
			SHOOT.play();
		}
	}

	public void playMove() {
		if (MOVE != null) {
			MOVE.play();
		}
	}

	public void playExplosion() {
		if (EXPLOSION != null) {
			EXPLOSION.play();
		}
	}

	public void playExplosionTank() {
		if (EXPLOSION_TANK != null) {
			EXPLOSION_TANK.play();
		}
	}
	
	public void playEndGame(){
		if (END_GAME!=null){
			END_GAME.play();
		}
	}
}

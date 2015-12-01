package org.example.game;

import org.example.game.prototypes.PrototypeCollisionDetection;

import com.badlogic.gdx.ApplicationAdapter;

public class GameChooser {
	public static ApplicationAdapter getGame(){
//		return new GrafosGame();
//		return new PrototypeSeekBehavior();
//		return new PrototypePathFollowingBehavior();
//		return new PrototypePlayer();	
//		return new PrototypeBox2DSteerable();
		return new PrototypeCollisionDetection();
	}
}

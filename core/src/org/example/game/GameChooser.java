package org.example.game;


import com.badlogic.gdx.ApplicationAdapter;
import org.example.game.prototypes.PrototypeMapBuilder;

public class GameChooser {
	public static ApplicationAdapter getGame(){
//		return new GrafosGame();
//		return new PrototypeSeekBehavior();
//		return new PrototypePathFollowingBehavior();
//		return new PrototypePlayer();	
//		return new PrototypeBox2DSteerable();
//		return new PrototypeCollisionDetection();
//		return new PrototypeProximityDetector();
//		return new PrototypeSteeringBehavior();
//		return new PrototypeDrawBox2DSteerable();
//		return new PrototypeFindNode();
                return new PrototypeMapBuilder();
	}
}

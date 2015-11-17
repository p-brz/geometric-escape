package org.example.game;

import org.example.game.prototypes.PrototypeSeekBehavior;

import com.badlogic.gdx.ApplicationAdapter;

public class GameChooser {
	public static ApplicationAdapter getGame(){
//		return new GrafosGame();
		return new PrototypeSeekBehavior();
	}
}

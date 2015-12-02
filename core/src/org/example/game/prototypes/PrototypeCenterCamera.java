package org.example.game.prototypes;

import org.example.game.drawable.TrivialDimensionConverter;
import org.example.game.player.TrackerCamera;

public class PrototypeCenterCamera extends PrototypePlayer{
	TrackerCamera tracker;
	
	public void create() {
		super.create();
		tracker = new TrackerCamera(super.cam, super.character, new TrivialDimensionConverter());
	};
	
	@Override
	protected void update() {
		super.update();
		tracker.update();
	}
}

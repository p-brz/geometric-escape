package org.example.game.player;

import org.example.game.drawable.DimensionConverter;
import org.example.game.steering.Steerable;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;

public class TrackerCamera {
	private Steerable steerable;
	private Camera camera;
	private DimensionConverter dimConverter;
	
	public TrackerCamera(Camera camera, Steerable steerable, DimensionConverter dimConverter) {
		this.camera = camera;
		this.steerable = steerable;
		this.dimConverter = dimConverter;
	}
	
	public void update(){
		if(steerable != null && camera != null){
			Vector2 pos = steerable.getPosition();
			camera.position.x = dimConverter.convertToScreen(pos.x);
			camera.position.y = dimConverter.convertToScreen(pos.y);
			System.out.println(camera.position);
			camera.update();
		}
	}
	
}

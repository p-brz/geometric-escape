package org.example.game.player;

import org.example.game.drawable.DimensionConverter;
import org.example.game.steering.Steerable;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;

public class TrackerCamera {
	private Steerable steerable;
	private Camera camera;
	private DimensionConverter dimConverter;
	
	private float maxDistanceFromSteerable;
	private float camVelocity;
	private Vector2 tempVector;
	
	public TrackerCamera(Camera camera, Steerable steerable, DimensionConverter dimConverter) {
		this.camera = camera;
		this.steerable = steerable;
		this.dimConverter = dimConverter;
		maxDistanceFromSteerable = 25f;
		camVelocity = 4f;
		tempVector = new Vector2();
	}
	
	public void update(){
		if(steerable != null && camera != null){
			Vector2 pos = steerable.getPosition();
			
			tempVector.set(dimConverter.convertToScreen(pos.x), dimConverter.convertToScreen(pos.y));
			
			float maxDistance2 = maxDistanceFromSteerable * maxDistanceFromSteerable;
			if(tempVector.dst2(camera.position.x, camera.position.y) > maxDistance2){
				tempVector.sub(camera.position.x, camera.position.y);
				tempVector.nor().scl(camVelocity);
				
				camera.position.x += tempVector.x;
				camera.position.y += tempVector.y;
				camera.update();
			}
		}
	}
	
}

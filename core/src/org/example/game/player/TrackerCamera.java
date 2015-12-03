package org.example.game.player;

import org.example.game.drawable.DimensionConverter;
import org.example.game.steering.Steerable;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;

public class TrackerCamera {
	private Steerable steerable;
	private Camera camera;
	private DimensionConverter dimConverter;
	
	private int distanceToStartMove, maxDistance;
	private int camVelocity;
	private Vector2 tempVector;
	
	public TrackerCamera(Camera camera, Steerable steerable, DimensionConverter dimConverter) {
		this.camera = camera;
		this.steerable = steerable;
		this.dimConverter = dimConverter;
		distanceToStartMove = 25;
		maxDistance = 2 * distanceToStartMove;
		camVelocity = 4;
		tempVector = new Vector2();
	}
	
	public int getCamVelocity() {
		return camVelocity;
	}
	public void setCamVelocity(int camVelocity) {
		this.camVelocity = camVelocity;
	}


	public void update(){
		if(steerable != null && camera != null){
			Vector2 pos = steerable.getPosition();
			
			tempVector.set(dimConverter.convertToScreen(pos.x), dimConverter.convertToScreen(pos.y));
			
			float distFromCam = tempVector.dst(camera.position.x, camera.position.y);
			
			if(distFromCam > distanceToStartMove){
				
				float velocity = camVelocity;
				if(distFromCam > maxDistance){
					velocity = Math.max(distFromCam - maxDistance, camVelocity);
				}
				
				tempVector.sub(camera.position.x, camera.position.y);
				tempVector.nor().scl(velocity);
				
				/*Convertendo para inteiro para atenuar (eliminar?) efeitos de "texture bleeding"
				 * Obs.: talvez isto leve a problemas dependendo do viewport da câmera*/
				camera.position.x += (int)tempVector.x;
				camera.position.y += (int)tempVector.y;
				
				camera.update();
			}
		}
	}

	private boolean needUpdate(Vector2 targetPoint) {
		int maxDistance2 = distanceToStartMove * distanceToStartMove;
		return dist2FromCam(targetPoint) > maxDistance2;
	}

	private float dist2FromCam(Vector2 targetPoint) {
		return targetPoint.dst2(camera.position.x, camera.position.y);
	}

	/*Obtém valor correspondente a pixel nas coordenadas da camera. 
	 * Obtido em: http://www.badlogicgames.com/forum/viewtopic.php?f=11&t=11078&p=49842*/
//	public static int getCameraPixel(Camera pCamera) {
//		int width = Gdx.graphics.getWidth();
//		int height = Gdx.graphics.getHeight();
//		float CameraPixel;
//
//		float targetRatio = (float) width / (float) height;
//		float sourceRatio = pCamera.viewportWidth / pCamera.viewportHeight;
//
//		if (targetRatio > sourceRatio)
//			CameraPixel = height / pCamera.viewportHeight;
//		else
//			CameraPixel = width / pCamera.viewportWidth;
//
//		return (int) CameraPixel;
//	}
}

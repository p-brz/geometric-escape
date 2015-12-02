package org.example.game.drawable;

import com.badlogic.gdx.math.Vector2;

/** DimensionConverter que não realiza conversão nenhuma*/
public class TrivialDimensionConverter implements DimensionConverter{

	@Override
	public float convertToWorld(float screenDimension) {
		return screenDimension;
	}

	@Override
	public float convertToScreen(float worldDimension) {
		return worldDimension;
	}

	@Override
	public Vector2 convertToWorld(Vector2 screenVector) {
		screenVector.x = convertToWorld(screenVector.x);
		screenVector.y = convertToWorld(screenVector.y);
		
		return screenVector;
	}

	@Override
	public Vector2 convertToScreen(Vector2 worldVector) {
		worldVector.x = convertToScreen(worldVector.x);
		worldVector.y = convertToScreen(worldVector.y);
		
		return worldVector;
	}

}

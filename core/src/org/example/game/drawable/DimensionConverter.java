package org.example.game.drawable;

import com.badlogic.gdx.math.Vector2;

public interface DimensionConverter {
	public float convertToWorld(float screenDimension);
	public float convertToScreen(float worldDimension);
	public Vector2 convertToWorld(Vector2 screenVector);
	public Vector2 convertToScreen(Vector2 worldVector);
}

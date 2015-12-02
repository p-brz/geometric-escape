package org.example.game.drawable;

public class BasicDimensionConverter extends TrivialDimensionConverter{
	/** 1 em "dimensões de mundo" equivale a esta quantidade de pixels.*/
	private float pixelsPerWorldUnit; 
	
	public BasicDimensionConverter(float pixelsPerWorldUnit) {
		this.pixelsPerWorldUnit = pixelsPerWorldUnit;
	}
	
	public float getPixelsPerWorldUnit() {
		return pixelsPerWorldUnit;
	}
	public void setPixelsPerWorldUnit(float pixelsPerWorldUnit) {
		this.pixelsPerWorldUnit = pixelsPerWorldUnit;
	}

	public float convertToScreen(float worldDimension) {
		return pixelsPerWorldUnit * worldDimension;
	};
	@Override
	public float convertToWorld(float screenDimension) {
		return screenDimension * (1f/pixelsPerWorldUnit);
	}
}

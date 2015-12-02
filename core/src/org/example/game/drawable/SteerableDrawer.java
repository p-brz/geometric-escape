package org.example.game.drawable;

import org.example.game.steering.Steerable;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class SteerableDrawer{
	private Sprite sprite;
	private Steerable steerable;
	private DimensionConverter dimensionConverter;
	
	public SteerableDrawer() {
		this(new Sprite(), null);
	}
	public SteerableDrawer(TextureRegion region, Steerable steerable) {
		this(new Sprite(region), steerable, null);
		setSize(region.getRegionWidth(), region.getRegionHeight());
	}
	public SteerableDrawer(TextureRegion region, Steerable steerable, DimensionConverter dimConverter) {
		this(new Sprite(region), steerable, dimConverter);
		setSize(region.getRegionWidth(), region.getRegionHeight());
	}
	
	protected SteerableDrawer(Sprite sprite, Steerable steerable, DimensionConverter dimConverter){
		this.sprite = sprite;
		this.steerable = steerable;

		if(dimConverter != null){
			this.dimensionConverter = dimConverter;
		}
		else{
			dimensionConverter = new TrivialDimensionConverter();
		}
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public void setSize(float width, float height) {
		this.sprite.setSize(toScreen(width), toScreen(height));
	}
	
	public Steerable getSteerable() {
		return steerable;
	}
	public void setSteerable(Steerable steerable) {
		this.steerable = steerable;
	}
	
	public void draw(Batch batch){
		if(getSteerable() != null){
			Vector2 pos = getSteerable().getPosition();
			sprite.setCenter(toScreen(pos.x), toScreen(pos.y));
		}
		this.sprite.draw(batch);
	}

	private float toScreen(float worldValue) {
		return getDimensionConverter().convertToScreen(worldValue);
	}

    /**
     * @return the dimensionConverter
     */
    public DimensionConverter getDimensionConverter() {
        return dimensionConverter;
    }

    /**
     * @param dimensionConverter the dimensionConverter to set
     */
    public void setDimensionConverter(DimensionConverter dimensionConverter) {
        this.dimensionConverter = dimensionConverter;
    }
        
        
}
package org.example.game.physics;

import com.badlogic.gdx.physics.box2d.Shape;

public interface PhysicalBody {
	Shape buildShape();
	Material getMaterial();
}

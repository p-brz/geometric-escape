package org.example.game.physics.bodies;

import com.badlogic.gdx.physics.box2d.Shape;

/** Um container que encapsula as informações necessárias para a criação de um Body*/
public interface PhysicalBody {
	Shape buildShape();
	Material getMaterial();
}

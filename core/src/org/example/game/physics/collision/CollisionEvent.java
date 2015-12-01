package org.example.game.physics.collision;

import com.badlogic.gdx.physics.box2d.Contact;

public class CollisionEvent {
	public static enum CollisionType{BeginContact, EndContact};
	
	private Collider colliderA, colliderB;

	private CollisionType collisionType;
	
	private boolean touching;
	
	public CollisionEvent(Contact contact, CollisionType collType){
		this(new Collider(contact.getFixtureA())
		   , new Collider(contact.getFixtureB())
		   , collType
		   , contact.isTouching());
	}
	
	public CollisionEvent(Collider colliderA, Collider colliderB, CollisionType collisionType, boolean touching) {
		super();
		this.colliderA = colliderA;
		this.colliderB = colliderB;
		this.collisionType = collisionType;
		this.touching = touching;
	}

	public boolean isTouching(){
		return touching;
	}
	
	public Collider getColliderA() {
		return colliderA;
	}

	public Collider getColliderB() {
		return colliderB;
	}

	public CollisionType getCollisionType() {
		return collisionType;
	}

	public boolean hasData(Object data) {
		if(data == null){
			return false;
		}
		return data.equals(getColliderA().getUserData())
				|| data.equals(getColliderB().getUserData());
	}
	
}

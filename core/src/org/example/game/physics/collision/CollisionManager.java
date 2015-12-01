package org.example.game.physics.collision;

import java.util.Collection;
import java.util.LinkedList;

import org.example.game.physics.collision.CollisionEvent.CollisionType;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class CollisionManager implements ContactListener {
	private Collection<CollisionListener> collListeners;
	
	public CollisionManager() {
		collListeners = new LinkedList<CollisionListener>();
	}

	public void addListener(CollisionListener listener){
		this.collListeners.add(listener);
	}
	public void removeListener(CollisionListener listener){
		this.collListeners.remove(listener);
	}

	@Override
	public void beginContact(Contact contact) {
		notifyListeners(new CollisionEvent(contact, CollisionType.BeginContact));
	}
	@Override
	public void endContact(Contact contact) {
		notifyListeners(new CollisionEvent(contact, CollisionType.EndContact));
	}
	
	@Override
	public void preSolve(Contact contact, Manifold oldManifold) 
	{}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) 
	{}
	
	private void notifyListeners(CollisionEvent evt) {
		for(CollisionListener listener : this.collListeners){
			listener.onCollision(evt);
		}
	}
}
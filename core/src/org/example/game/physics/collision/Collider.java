package org.example.game.physics.collision;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;

/** Uma classe para armazenar os dados de um participante de uma colisão*/
public class Collider {
	private Fixture fixture;

	public Collider(Fixture fixture) {
		super();
		this.fixture = fixture;
	}

	/** Retorna o Body que sofreu a colisão*/
	public Body getBody(){
		return fixture.getBody();
	}

	/** Retorna a fixture que sofreu a colisão*/
	public Fixture getFixture(){
		return fixture;
	}

	/** Retorna o objeto associado ao body que sofreu esta colisão*/
	public Object getUserData(){
		return getBody().getUserData();
	}
}

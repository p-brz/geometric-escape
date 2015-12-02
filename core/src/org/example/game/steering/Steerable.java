package org.example.game.steering;

import com.badlogic.gdx.math.Vector2;

/** Representa uma entidade que pode ser controlada por um SteeringBehaviour. */
public interface Steerable 
{
	public Vector2 getPosition();

	/** Retorna um vetor com a velocidade atual do objeto*/
	public Vector2 getVelocity();

	/** Representa a magnitude da velocidade "desejada" para utilizar sobre este objeto.
	 * 	Pode diferir da módulo da velocidade atual dele. Permite ao Steerable definir qual velocidade deseja estar.*/
	public float getVelocityMag();

	/** Variação de velocidade (valor positivo) que pode ser aplicada ao {@code Steerable} 
	 * para modificar sua velocidade atual. Corresponde a aceleração por frame (?). */
	public float getDeltaVelocity();

	public void applyForce(Vector2 force);
	
	public void update(float deltaT);
}
